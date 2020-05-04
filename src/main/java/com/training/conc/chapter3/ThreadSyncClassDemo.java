/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

/**
* @ClassName: ThreadSyncClassDemo
* @version 1.0 
* @Desc: 演示synchronized锁类模板
* @author John Goo
* @date 2020年4月15日上午11:28:52
* @history v1.0
*
*/
public class ThreadSyncClassDemo {

	public static void main(String[] args) {
		// 定义资源
		Boy  boy = new Boy();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (Boy.class) {
					for(int i=0;i<10;i++) {
						System.out.println(Thread.currentThread().getId()+":code block-->"+i);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Boy.f2();
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				Boy.f3();
			}
		});
		
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				boy.f1();
			}
		});
		
		// 启动线程
		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}

class Boy{
	
	// synchronized(this)
	synchronized public void f1() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+":f1()-->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// synchronized(Boy.class)
	synchronized public static void f2() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+":f1()-->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	// 	synchronized(Boy.class)
	synchronized public static void f3() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+":f1()-->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
