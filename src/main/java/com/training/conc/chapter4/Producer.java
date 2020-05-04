/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter4;

import java.util.concurrent.BlockingQueue;

/**
 * @ClassName: Producer
 * @version 1.0
 * @Desc: TODO
 * @author John Goo
 * @date 2020年4月30日
 * @history v1.0
 *
 */
public class Producer implements Runnable {

	// 阻塞队列
	private BlockingQueue queue;
	// 名称
	private String name;

	public Producer(BlockingQueue queue) {
		super();
		this.queue = queue;
	}
	

	public Producer(BlockingQueue queue, String name) {
		super();
		this.queue = queue;
		this.name = name;
	}


	@Override
	public void run() {
		for (int i = 1; i < 10; i++) {
			Long threadId = Thread.currentThread().getId();
			String msg = name+threadId + "-" + i;
			try {
				queue.put(msg);//发布数据
				System.out.println("生产者线程：" + threadId + " 发布了消息：" + msg);
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
