/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

/**
* @ClassName: ThreadSyncClassStaticmethodDemo
* @version 1.0 
* @Desc: 演示synchronized静态方法
* @author John Goo
* @date 2020年4月15日上午10:39:13
* @history v1.0
*
*/
public class ThreadSyncClassStaticmethodDemo {
	
	/**
	 * 
	 * 描述：锁静态方法
	 * @author John Goo
	 * @date 2020年4月15日上午10:47:43
	 * @param args
	 */
	public static void main(String[] args) {
		
		// 定义访问的资源
		SourceObject so = new SourceObject();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				SourceObject.f2();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				SourceObject.f2();
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				SourceObject.f3();
			}
		});
		
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				so.f1();
			}
		});
		
		/**
		 * 同一个静态synchronized方法出现同步
		 */
		// 开启线程
		t1.start();
		t2.start();
		
		/**
		 * synchronized static f2 和synchronized static f3之间发生了同步互斥现象 
		 */
		t3.start();
		
		/**
		 * 锁静态方法对非静态对象方法没有任何影响
		 */
		t4.start();
		
		
		

	}

}

class SourceObject{
	
	synchronized public void f1() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+":f1()--->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
   synchronized public static void f2() {
    	for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+":f2()--->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}


	synchronized public static void f3() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+":f3()--->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	
}
