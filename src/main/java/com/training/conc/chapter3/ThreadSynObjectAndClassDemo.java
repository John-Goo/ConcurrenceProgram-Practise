/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

/**
* @ClassName: ThreadSynObjectAndClassDemo
* @version 1.0 
* @Desc: 演示synchronized锁类和锁对象的区别
* @author John Goo
* @date 2020年4月20日上午11:22:21
* @history v1.0
*
*/
public class ThreadSynObjectAndClassDemo {

	public static void main(String[] args) {
		// synchronized(变量），区分对象锁变量
		X x = new X();
	   Thread t1 = new Thread(new Runnable() {
		@Override
		public void run() {
			// 代码块锁变量 x
			synchronized (X.class) {
				for(int i=0;i<5;i++) {
					System.out.println("[synchronized (x)]--->"+i);
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
				// 代码块锁变量 
				synchronized (X.class) {
					for(int i=0;i<5;i++) {
						//System.out.println("[synchronized (X.class)]--->"+i);
						System.out.println("[thrad t2]--->"+i);
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
				// synchronized 对象方法
				//x.f1();
				// synchronized静态方法
				// synchronized(x)
				// synchronized(X.class)
				x.f1();
			}
	  });
	   
	   // 开启线程
	   t1.start();
	   t2.start();
	   t3.start();
		
		

	}

}

class X {
	
	// synchronized(this=x)
	synchronized public void f1() {
		for(int i=0;i<5;i++) {
			//System.out.println("[synchronized (X.class)]--->"+i);
			System.out.println("[ f1() ]--->"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void f2() {
		
	}
	
    public static void f3() {
		
	}
	
    // synchronized(X.class)
	synchronized public static void f4() {
		for(int i=0;i<5;i++) {
			//System.out.println("[synchronized (X.class)]--->"+i);
			System.out.println("[ f4() ]--->"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


