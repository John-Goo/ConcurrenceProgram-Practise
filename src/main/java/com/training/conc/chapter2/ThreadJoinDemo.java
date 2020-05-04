/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter2;

/**
* @ClassName: ThreadJoinDemo
* @version 1.0 
* @Desc: 演示join方法
* @author John Goo
* @date 2020年4月12日下午3:34:41
* @history v1.0
*
*/
public class ThreadJoinDemo {

	public static void main(String[] args) {
		Thread2 thread2 = new Thread2();
		Thread1 thread1 = new Thread1(thread2);
		thread2.start();
		thread1.start();
		
	}

}

class Thread1 extends Thread{
	
	private Thread2 thread2;
	
	

	public Thread1(Thread2 thread2) {
		super();
		this.thread2 = thread2;
	}



	@Override
	public void run() {
		try {
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=0;i<10;i++) {
			System.out.println(" Thread1 >>>"+i);
		}
	}
}


class Thread2 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(" Thread2 >>>"+i);
		}
	}
}

