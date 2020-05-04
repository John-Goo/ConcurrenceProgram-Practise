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
* @ClassName: ThreadPoolExceptionDemo
* @version 1.0 
* @Desc: 演示线程池异常处理机制
* @author John Goo
* @date 2020年4月28日下午9:14:12
* @history v1.0
*
*/
public class ThreadPoolExceptionDemo {

	public static void main(String[] args) {
		// 创建线程池
		ExecutorService es  = Executors.newSingleThreadExecutor();
		for(int i=0;i<20;i++) {
			// 创建任务并提交
			es.execute(new MyTask7(i));
		}
		
		// 关闭线程池
		es.shutdown();

	}

}

class MyTask7 implements Runnable{
	
	private Integer seqNo;

	public MyTask7(Integer seqNo) {
		super();
		this.seqNo = seqNo;
	}



	@Override
	public void run() {
		try {
			Thread.sleep(1*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程ID:"+Thread.currentThread().getId()+",正在执行. 业务序号："+seqNo);
		if(seqNo==5) {
			throw new RuntimeException("线程ID:"+Thread.currentThread().getId()+" 发生了异常！");
		}
	}
	
}
