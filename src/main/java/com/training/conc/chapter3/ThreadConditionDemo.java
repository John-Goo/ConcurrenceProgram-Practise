package com.training.conc.chapter3;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ThreadConditionDemo
 * @version 1.0
 * @Desc: 演示Condtion锁—等待通知模式
 * @author John Goo
 * @history v1.0
 */
public class ThreadConditionDemo {

	public static void main(String[] args) {
		BookingTicket1 bt = new BookingTicket1();
		
		
		
		/**
		 * 工作人员发布车票
		 */
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(() -> {
			for(int i=0;i<20;i++) {
				// 发布车票
				bt.create(5);
			    try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		es.shutdown();
		
		/**
		 * 购票人员预订车票
		 * 
		 */
		ExecutorService es1 = Executors.newFixedThreadPool(30);
		for (int i = 0; i < 20; i++) {
			es1.execute(()->{
				bt.take();// 车票数小于0,等待状态
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		es1.shutdown();
	}

}

class BookingTicket1 {
	// 票数
	private Integer qty = 0;
	// 重入锁
	ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	/**
	 * 
	 * 描述：工作人员发布车票
	 * @author John Goo
	 * @param ticketQty
	 */
	public void create(int ticketQty) {
		try {
			lock.lock();
			qty += ticketQty;
			System.out.println("线程ID:" + Thread.currentThread().getId() + "存入车票5张，总票数：" + qty);
			// 只能通知1个等待的线程
			//condition.signal();
			// 唤醒所有的等待线程
			condition.signalAll();
			Thread.sleep(3*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	/**
	 * 
	 * 描述：购票人员预订车票
	 * @author John Goo
	 * @date 2020年4月29日
	 */
	public void take() {
		try {
			lock.lock();
			// 车票为0时，需要等待
			if(qty<=0) {
				System.out.println("线程ID:" + Thread.currentThread().getId() + "现在车票为{0}等待中！剩余票数：" + qty);
				condition.await();//当前线程处理waiting状态，释放锁，等待通知唤醒signal
			}
			// 判断票数大于0
			if (qty > 0) {
				qty--;
				System.out.println("线程ID:" + Thread.currentThread().getId() + " 已经买到票，离开！剩余票数：" + qty);
			} else {
				System.out.println("线程ID:" + Thread.currentThread().getId() + " 现在没有票了，离开！剩余票数：" + qty);
			}
			System.out.println("线程ID:" + Thread.currentThread().getId() + "=== 线束 take() =====");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}

	}
}
