/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter2;

/**
* @ClassName: ThreadAliveDemo
* @version 1.0 
* @Desc: 线程isAlive方法演示
* @author John Goo
* @date 2020年4月12日下午3:52:10
* @history v1.0
*
*/
public class ThreadAliveDemo {

	public static void main(String[] args) throws InterruptedException {
		
		MyThread1 thread = new MyThread1();
		// start之前,状态如何
		System.out.println(" main --- start befor :"+thread.isAlive());
		thread.start();
		System.out.println(" main --- start after :"+thread.isAlive());
		Thread.sleep(3*1000);
		System.out.println(" main --- sleep ** after :"+thread.isAlive());
	}

}

class MyThread1 extends Thread{

	@Override
	public void run() {
		System.out.println("thread isAlive:"+this.isAlive());
		
	}
	
}
