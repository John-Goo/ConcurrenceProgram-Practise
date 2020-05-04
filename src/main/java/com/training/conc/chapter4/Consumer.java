/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter4;

import java.util.concurrent.BlockingQueue;

/**
 * @ClassName: Consumer
 * @version 1.0
 * @Desc: TODO
 * @author John Goo
 * @date 2020年4月30日
 * @history v1.0
 *
 */
public class Consumer implements Runnable {

	// 队列
	private BlockingQueue queue;


	public Consumer(BlockingQueue queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Object obj =  queue.take();//监听数据，没有数据时产生阻塞
				System.out.println("消费者线程：" + Thread.currentThread().getId() + " 接收到消息：" + obj);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
