/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter2;

/**
* @ClassName: ThreadDaemondDemo
* @version 1.0 
* @Desc: 演示守护线程
* @author John Goo
* @date 2020年4月13日上午10:22:56
* @history v1.0
*
*/
public class ThreadDaemondDemo {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<3;i++) {
					System.out.println("[ T1 ]--->"+i);
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<5;i++) {
					System.out.println("[ T2 ]--->"+i);
				}
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<100;i++) {
					System.out.println("[ T3 ]--->"+i);
				}
			}
		});
		
		t1.start();
		t2.start();
		
		// t3做为守护线程
		t3.setDaemon(true);
		t3.start();
	}

}
