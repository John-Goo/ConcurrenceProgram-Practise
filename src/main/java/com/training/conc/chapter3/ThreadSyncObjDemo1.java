/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

/**
* @ClassName: ThreadSyncObjectDemo
* @version 1.0 
* @Desc: 演示synchronized锁对象
* @author John Goo
* @date 2020年4月15日上午11:46:12
* @history v1.0
*
*/
public class ThreadSyncObjDemo1 {

	public static void main(String[] args) {
		Student1 lockObj = new Student1();
		Student1 lockObj2 = new Student1();
		System.out.println("=== &"+lockObj);
		
		// 不同的对象之间不能形成互斥效果
		Thread t1 = new Thread(new  Runnable() {
			public void run() {
				synchronized (lockObj) {
					for(int i=0;i<10;i++) {
						System.out.println(Thread.currentThread().getId()+":{lockObj1} code block-->"+i);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}
		});
		Thread t2 = new Thread(new  Runnable() {
			public void run() {
				// synchronized(this)
				lockObj.f2();
			}
		});
		Thread t3 = new Thread(new  Runnable() {
			public void run() {
				synchronized (lockObj2) {
					for(int i=0;i<10;i++) {
						System.out.println(Thread.currentThread().getId()+":{lockObj2} code block-->"+i);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		});
		// 开启线程
		t1.start();
		t2.start();
		t3.start();
		

	}

}

class Student1{
	
	public static void  f1() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+": f1()-->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// sychronized(lockObj2)
	synchronized public  void  f2() {
		System.out.println(" f2() this对象地址："+this);
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+": f2()-->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public  void  f3() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getId()+": f3()-->"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
