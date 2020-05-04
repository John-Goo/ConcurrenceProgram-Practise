/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter4;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @ClassName: NewSingleThreadPoolDemo
* @version 1.0 
* @Desc: 演示单例线程池
* @author John Goo
* @history v1.0
*
*/
public class NewSingleThreadPoolDemo {

	public static void main(String[] args) {
		// 创建线程池
		ExecutorService es = Executors.newSingleThreadExecutor();
		for(int i=1;i<100;i++) {
			// 创建任务
			MyTask2 task = new MyTask2();
			//提交
			es.execute(task);
		}
		// 关闭线程池
		es.shutdown();
		
	}

}

class MyTask2 implements Runnable{

	@Override
	public void run() {

	}


	
}
