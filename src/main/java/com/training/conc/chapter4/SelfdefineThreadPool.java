package com.training.conc.chapter4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SelfdefineThreadPool {

	public static void main(String[] args) throws InterruptedException {
		// 自定义线程池
		/**
	       1）任务队列长度+maximunPoolSize <=提交任务数时，开启线程数为maximunPoolSize ；
           2）任务队列>提交任务数或任务队列无界时，开启线程数为corePoolSize ；
		 */
		ThreadPoolExecutor pool  = new ThreadPoolExecutor(5,10,10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(7));
		for(int i=0;i<17;i++) {
			pool.execute(()->{
				System.out.println("线程ID:"+Thread.currentThread().getId()+" 正在执行.");
				try {
					Thread.sleep(2*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			
		}
		
		new Thread(new ThreadPoolMeta(pool)).start();
		System.out.println("======= park 处于休眠状态 =======");
		Thread.sleep(10*60*1000);
		// 关闭线程池
		pool.shutdown();
		
	}
	
}

	
	
	class ThreadPoolMeta implements Runnable {

		private final ThreadPoolExecutor pool;

		public ThreadPoolMeta(ThreadPoolExecutor pool) {
			super();
			this.pool = pool;
		}

		@Override
		public void run() {
			while (true) {
				System.out.println("队列中任务数：" + pool.getQueue().size());
				System.out.println("PoolSize:" + pool.getPoolSize());
				System.out.println("活跃数：" + pool.getActiveCount());
				System.out.println("CorePoolSize：" + pool.getCorePoolSize());
				System.out.println("MaxPoolSize：" + pool.getMaximumPoolSize());
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

}
	
