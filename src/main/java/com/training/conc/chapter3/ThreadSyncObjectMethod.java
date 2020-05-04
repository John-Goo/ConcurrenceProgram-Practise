package com.training.conc.chapter3;

public class ThreadSyncObjectMethod {

	/**
	 * 演示synchronized锁对象方法
	 *  （1）首先锁住这个对象实例，再执行对象方法
	 *  （2） 影响synchronized 对象方法
	 *  （3）非synchronized对象方法不受影响
	
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Student student = new Student();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				student.f1();
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				student.f1();
				
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// 沒有帶synchronized鎖
				student.f2();
				
			}
		}).start();
		

	   }

}


class Student{
	
	synchronized public void f1() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+": f1()--->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	synchronized public void f2() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+": f2()--->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void f3() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+": f3()--->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}