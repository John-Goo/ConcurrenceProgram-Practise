/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
* @ClassName: ThreadPoolExecuteAndSubmitDemo
* @version 1.0 
* @Desc: 演示线程池提交方法submit和execute
* @author John Goo
* @date 2020年4月28日下午6:05:59
* @history v1.0
*
*/
public class ThreadPoolExecuteAndSubmitDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
	   // 创建线程池
	   ExecutorService es = Executors.newFixedThreadPool(3);
	   List<Future> fList = new ArrayList<Future>();
	   for(int i=0;i<10;i++) {
		   // 创建任务并提交
		   Future f = es.submit(new MyTask6(i));
		   fList.add(f);
	   }
	   
	   /**
	    * #打印出结果
	    */
	   for(Future f:fList) {
		   // 线程未执行完，会产生阻塞等待结果
		   System.out.println("result:"+f.get());
	   }
	   
	   // 关闭线程池
	   es.shutdown();

	}

}

class MyTask6 implements Callable<Integer>{
	
	private Integer calVal;
	
	
	public MyTask6(Integer calVal) {
		super();
		this.calVal = calVal;
	}


	@Override
	public Integer call() throws Exception {
		Thread.sleep(1000);
		System.out.println("线程ID:"+Thread.currentThread().getId()+"正在计算，参数："+calVal);
		int result = calVal*100;
		return result;
	}
	
}

