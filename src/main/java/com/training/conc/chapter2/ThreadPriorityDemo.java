/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter2;

/**
* @ClassName: ThreadPriorityDemo
* @version 1.0 
* @Desc: 演示线程优先级
* @author John Goo
* @date 2020年4月12日下午4:01:01
* @history v1.0
*
*/
public class ThreadPriorityDemo {

	public static void main(String[] args) {
		MyTask1 myTask1 = new MyTask1();
		MyTask2 myTask2 = new MyTask2();
		myTask1.setPriority(1);
		myTask2.setPriority(10);
		myTask1.start();
		myTask2.start();
	}

}

class MyTask1 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("[MyTask1]--->"+i);
		}
		
	}
}

class MyTask2 extends Thread{

	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("[MyTask2]--->"+i);
		}
		
	}
}

