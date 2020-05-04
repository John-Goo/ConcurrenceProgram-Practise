/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

/**
* @ClassName: ThreadSynObjectDemo
* @version 1.0 
* @Desc: 演示synchronized锁对象demo
* @author John Goo
* @date 2020年4月21日上午9:57:51
* @history v1.0
*
*/
public class ThreadSynObjectDemo {

	public static void main(String[] args) {
		Person2 p = new Person2();
		Person2 p1 = new Person2();
		/**
		 *  锁对象：同步代码块之间
		 */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 锁变量p
				synchronized (p) {
					for(int i=0;i<5;i++) {
						System.out.println("{ t1 }--->"+i);
						try {
							Thread.sleep(1000);
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
				// 锁变量p
				synchronized (p) {
					for(int i=0;i<5;i++) {
						System.out.println("{ t2 }--->"+i);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				p.f3();
			}
		});
		
		
		
		/**
		 *   锁对象——验证同步代码块之间形成同步效果
		 */
		t1.start();
		t2.start();
		/**
		 * 同步代码块与带锁的方法形成同步效果
		 */
		t3.start();
		
		

	}

}

class Person2{
	
	// synchronized(this=p)
	synchronized public void f3() {
		for(int i=0;i<5;i++) {
			System.out.println("{ f3() }--->"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized public void f4() {
		for(int i=0;i<5;i++) {
			System.out.println("{ f4() }--->"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
