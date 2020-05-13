/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo
 * @version 1.0
 * @Desc: TODO
 * @author John Goo
 * @history v1.0
 *
 */
public class ReentrantLockDemo {

	public static void main(String[] args) {
		// true-公平锁，false-非公平锁
		ReentrantLock lock = new ReentrantLock(true);
		Strategy strategy = new Strategy(lock);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				strategy.f1();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				strategy.f2();
			}
		});
		// 启动线程
		t1.start();
		t2.start();

	}

}

class Strategy {

	// 对象的全局锁lock
	private ReentrantLock lock;

	public Strategy(ReentrantLock lock) {
		super();
		this.lock = lock;
	}

	public void f1() {
		try {
			// synchronized (this) {
			// 锁代码块
			lock.lock();
			for (int i = 0; i < 3; i++) {
				System.out.println("{ f1()}--->" + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 释放锁
			lock.unlock();
		}

	}

	public void f2() {
		// synchronized (this) {
		try {
		lock.lock();
		for (int i = 0; i < 3; i++) {
			System.out.println("{ f2()}--->" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 释放锁
			lock.unlock();
		}
	}
}
