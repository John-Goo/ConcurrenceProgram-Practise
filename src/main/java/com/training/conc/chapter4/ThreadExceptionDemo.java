/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter4;

/**
* @ClassName: ThreadExceptionDemo
* @version 1.0 
* @Desc: TODO
* @author John Goo
* @date 2020年4月27日上午10:59:35
* @history v1.0
*
*/
public class ThreadExceptionDemo {
	
public static void main(String[] args) {
	while(true) {
		Thread t1 = new Thread(new Task1());
		
		t1.setUncaughtExceptionHandler((t,  e)->{
			System.out.println("==> 线程ID:"+t.getId()+",發生了異常："+e);
			//e.printStackTrace();
			return;
		});
		t1.start();
	}
	
}
	   
	


}

class Task1 implements Runnable{

	@Override
	public void run() {
		String str = null;
		str.toString();
		
	}
	
}
