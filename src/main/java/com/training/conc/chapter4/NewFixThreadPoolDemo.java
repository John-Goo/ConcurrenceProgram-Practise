/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @ClassName: NewFixThreadPoolDemo
* @version 1.0 
* @Desc: 演示有界线程池的使用
* @author John Goo
* @date 2020年4月27日上午9:49:51
* @history v1.0
*
*/
public class NewFixThreadPoolDemo {

	public static void main(String[] args) throws InterruptedException {
		//Thread.sleep(6*1000);
		
	
		// 创建线程池
		ExecutorService es = Executors.newFixedThreadPool(3);
		
		
		for(int i=1;i<60;i++) {
			// 创建任务
			MyTask1 task = new MyTask1();
			//提交
			es.execute(task);
			
		}
		
		///Thread.sleep(3*60*1000);
		// 关闭线程池
		es.shutdown();
		
	}

}

class MyTask1 implements Runnable{

	@Override
	public void run() {
		System.out.println("线程ID："+Thread.currentThread().getId()+",正在执行.");
		//String str = null;
	    //str.toString();
	}
	
}
