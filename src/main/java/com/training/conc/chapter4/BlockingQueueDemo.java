/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
* @ClassName: BlockingQueueDemo
* @version 1.0 
* @Desc: 使用BlockingQueue实现生产者与消费者
* @author John Goo
* @history v1.0
*
*/
public class BlockingQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		// 定义阻塞队列
	    BlockingQueue queue = new LinkedBlockingQueue();
	    /**
	     * 生产者
	     */
	    new Thread(new Producer(queue,"电视机")).start();
	    new Thread(new Producer(queue,"水箱")).start();
	    
	    /**
	     * 消费者
	     */
		new Thread (new Consumer(queue)).start();;
		new Thread (new Consumer(queue)).start();;
		new Thread (new Consumer(queue)).start();;
		

	}

}
