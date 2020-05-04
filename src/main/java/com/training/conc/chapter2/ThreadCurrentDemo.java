/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter2;

/**
* @ClassName: ThreadCurrentDemo
* @version 1.0 
* @Desc: TODO
* @author John Goo
* @date 2020年4月12日下午3:42:40
* @history v1.0
*
*/
public class ThreadCurrentDemo {

	public static void main(String[] args) {
		System.out.println("==main :"+Thread.currentThread().getId());
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("==child :"+Thread.currentThread().getId());
				System.out.println("==child :"+Thread.currentThread().getName());
			}
		});
		t.setName(" ThreadName88");
		t.start();
		
		
		
	}

}
