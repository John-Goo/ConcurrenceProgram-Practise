/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

/**
* @ClassName: ThreadSyncCodeblockDemo
* @version 1.0 
* @Desc: 演示synchronized锁代码块
* @author John Goo
* @date 2020年4月15日上午11:05:18
* @history v1.0
*
*/
public class ThreadSyncCodeblockDemo {

	public static void main(String[] args) {
		
		Person p = new Person();
		 
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				p.f1();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				p.f1();
			}
		});
		// 开启线程
		t1.start();
		t2.start();
		
		
	}

}

class Person{
	
	private int count = 0;
	
	private Object obj = new Object();
	
	public void f1() {
		
		String name = String.valueOf(Thread.currentThread().getId());
		System.out.println(" 当前线程进入排队："+Thread.currentThread().getId()+",name="+name);
		/**
		 * (1)synchronized 锁 this ，可以实现同步互斥效果
		 * (2)synchronized 锁 Person.class ，也可以实现同步互斥效果
		 * (3)synchronized 锁 new Object ，也可以实现同步互斥效果
		 * (4)synchronized 锁 动态变量 ，也可以实现同步互斥效果
		 */
		synchronized (name) {
			for(int i=0;i<10;i++) {
				System.out.println(Thread.currentThread().getId()+": f1()---->"+i);
				count++;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void f2() {
			
	}
	
}
