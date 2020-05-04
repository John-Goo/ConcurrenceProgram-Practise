/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: ThreadCountdownLatchDemo
 * @version 1.0
 * @Desc: 演示CountdownLatch用法
 * @author John Goo
 * @history v1.0
 *
 */
public class ThreadCountdownLatchDemo {
	public static void main(String[] args) {
		
		// 定义信号量
		CountDownLatch latch  = new CountDownLatch(3);
		/**
		 *  车间生产零件
		 */
		A1Depart  a1 = new A1Depart(latch);
		A2Depart  a2 = new A2Depart(latch);
		A3Depart  a3 = new A3Depart(latch);
		/**
		 * 组装车间
		 */
		BDepart  b = new BDepart(latch);
		
		// 启动线程
		a1.start();
		a2.start();
		a3.start();
		b.start();
		
		
	}
	
	

}

class A1Depart extends Thread {

	private CountDownLatch latch;
	
	

	public A1Depart(CountDownLatch latch) {
		super();
		this.latch = latch;
	}



	@Override
	public void run() {
		System.out.println("[生产自行车厂]---> 车间A1 正在生产...");
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[生产自行车厂]---> 车间A1 生产完成。");
		// 本部分工序做完，状态减1
		latch.countDown();
	}

}

class A2Depart extends Thread {

	private CountDownLatch latch;
	
	public A2Depart(CountDownLatch latch) {
		super();
		this.latch = latch;
	}


	@Override
	public void run() {
		System.out.println("[生产自行车厂]---> 车间A2 正在生产...");
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[生产自行车厂]---> 车间A2生产完成。");
		// 本部分工序做完，状态减1
		latch.countDown();
	}

}

class A3Depart extends Thread {

	private CountDownLatch latch;
	
	public A3Depart(CountDownLatch latch) {
		super();
		this.latch = latch;
	}


	@Override
	public void run() {
		System.out.println("[生产自行车厂]---> 车间A3 正在生产...");
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[生产自行车厂]---> 车间A3 生产完成。");
		// 本部分工序做完，状态减1
		latch.countDown();
	}

}

class BDepart extends Thread {

	private CountDownLatch latch;
	
	public BDepart(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("[生产自行车厂]---> 车间B 正在等待组装...");
		try {
			latch.await();
			System.out.println("[生产自行车厂]---> 车间B 正在组装...");
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[生产自行车厂]---> 车间B 组装完成。");
	
		
	}

}
