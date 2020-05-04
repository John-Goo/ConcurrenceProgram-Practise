/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter1;

/**
* @ClassName: ThreadDemo1
* @version 1.0 
* @Desc: 演示线程创建Runnable接口实现
* @author John Goo
* @history v1.0
*
*/
public class ThreadDemo1 {

	public static void main(String[] args) {
		MyTask1 task1 = new MyTask1();
		Thread t1 = new Thread(task1);
		MyTask1 task2 = new MyTask1();
		Thread t2 = new Thread(task1);
		t1.start();
		t2.start();
		/**
		 * 继承Thread实现方式
		 * 
		 */
		MyTask2 task3 = new MyTask2();
		task3.start();
		task3.yield();
		
		
	}

}

class MyTask1 implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getId()+"====> thread exec.");
			try {
				// 
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

class MyTask2 extends Thread{
	
	
	public String show() {
		return null;
	}

	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getId()+"====> MyTask2 thread exec.");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

