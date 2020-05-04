/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

import java.util.concurrent.Semaphore;

/**
* @ClassName: ThreadSemaphoreDemo
* @version 1.0 
* @Desc: 演示Semaphore使用方法
* @author John Goo
* @date 2020年4月21日上午11:34:12
* @history v1.0
*
*/
public class ThreadSemaphoreDemo {

	public static void main(String[] args) {
		// 定义3个信号量，表示批处理能力
		Semaphore semaphore = new Semaphore(6);
		for(int i=0;i<16;i++) {
			new BookingTicket(semaphore,i,"Ticket-"+i).start();
		}
		
		
	}

}

class BookingTicket extends Thread{
	
	private Semaphore semaphore;
	private Integer seq;
	private String name;
	
	

	public BookingTicket(Semaphore semaphore, Integer seq, String name) {
		super();
		this.semaphore = semaphore;
		this.seq = seq;
		this.setName(name);
	}



	@Override
	public void run() {
		try {
			semaphore.acquire(1);
			System.out.println("本批次人员："+Thread.currentThread().getName()+",业务序号："+seq);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphore.release(1);
		}
	}
	
	
	
}

