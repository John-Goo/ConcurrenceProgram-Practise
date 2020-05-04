/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter2;

/**
* @ClassName: ThreadStopDemo
* @version 1.0 
* @Desc: 演示线程关闭方法
* @author John Goo
* @date 2020年4月13日上午11:51:03
* @history v1.0
*
*/
public class ThreadStopDemo {

	public static void main(String[] args) throws InterruptedException {
		StopThreadDemo t =new StopThreadDemo();
		t.start();
		Thread.sleep(200);
		//t.stop();
		//t.interrupt();
		// 自定义状态关闭方法
		t.shutdown();
		

	}

}

class StopThreadDemo extends Thread{
	
	private boolean flag = false;
	
	public void shutdown() {
		this.flag = true;
	}

	@Override
	public void run() {
		int count =0;
		while(!flag) {
			count++;
			System.out.println("[StopThreadDemo]--->"+count);
		}
		
	}
	
	
}
