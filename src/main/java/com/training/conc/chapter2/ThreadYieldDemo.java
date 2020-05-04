/******************************************************************************
 * Copyright (C) 2020  ShenZhen X Co.,Ltd
 * All Rights Reserved.
 * 本软件为X开源公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.training.conc.chapter2;

/**
* @ClassName: ThreadYieldDemo
* @version 1.0 
* @Desc: yeild方法演示
* @author John Goo
* @date 2020年4月12日下午3:21:05
* @history v1.0
*
*/
public class ThreadYieldDemo {
	
	private static int _CV = 0;

	/**
	 * 
	 * 描述：yield不能保证一定让出CPU时间片，但从趋势上也可以看出yield让出了一些CPU时间片
	 * @author John Goo
	 * @date 2020年4月12日下午3:29:57
	 * @param args
	 */
	public static void main(String[] args) {
		
		 new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					Thread.currentThread().yield();
					System.out.println(" [Thread1] >>> cv="+_CV);
					_CV++;
				}
				
			}
		}).start();
		 
		 new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0;i<100;i++) {
						//Thread.currentThread().yield();
						System.out.println("[Thread2]>>> cv="+_CV);
						_CV++;
					}
					
				}
			}).start();;
		
		
		

	}

}
