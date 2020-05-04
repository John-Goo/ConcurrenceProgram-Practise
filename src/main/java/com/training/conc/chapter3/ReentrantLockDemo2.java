/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo
 * @version 1.0
 * @Desc: 演示tryLock方法
 * @author John Goo
 * @date 2020年4月21日上午10:31:01
 * @history v1.0
 *
 */
public class ReentrantLockDemo2 {

	public static void main(String[] args) {
		// true-公平锁，false-非公平锁
		ReentrantLock lock = new ReentrantLock(true);
		Strategy1 strategy1 = new Strategy1(lock);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				strategy1.f1();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				strategy1.f2();
			}
		});
		// 启动线程
		t1.start();
		t2.start();

	}

}

class Strategy1 {

	// 对象的全局锁lock
	private ReentrantLock lock;

	public Strategy1(ReentrantLock lock) {
		super();
		this.lock = lock;
	}

	public void f1() {
		try {
			// synchronized (this) {
			if (lock.tryLock(5,TimeUnit.SECONDS)) {
				for (int i = 0; i < 3; i++) {
					System.out.println("{ f1()}--->" + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("==={ f1()}--->未获得锁..");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			lock.unlock();
		}

	}

	public void f2() {
		// synchronized (this) {
		try {
			// synchronized (this) {
			if (lock.tryLock(5,TimeUnit.SECONDS)) {
				for (int i = 0; i < 3; i++) {
					System.out.println("{ f1(s)}--->" + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("==={ f2()}--->未获得锁..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			lock.unlock();
		}
	}
}
