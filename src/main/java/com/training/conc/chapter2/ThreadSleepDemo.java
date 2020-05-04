/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter2;

/**
* @ClassName: ThreadSleepDemo
* @version 1.0 
* @Desc: 演示sleep方法
* @author John Goo
* @date 2020年4月12日下午3:09:29
* @history v1.0
*
*/
public class ThreadSleepDemo {

	
	/**
	 * 
	 * 描述：sleep阻塞当前线程
	 * @author John Goo
	 * @date 2020年4月12日下午3:15:18
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("====== main sleep>> before");
		MyThread thread = new MyThread();
		thread.start();
		System.out.println("====== main sleep>> after");
	}

}

class MyThread extends Thread{

	@Override
	public void run() {
		System.out.println("MyThread====> before");
		try {
			Thread.sleep(6*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MyThread====> after");
	}
	
	
}
