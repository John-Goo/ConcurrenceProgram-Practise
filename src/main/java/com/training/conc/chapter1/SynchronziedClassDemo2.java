package com.training.conc.chapter1;

public class SynchronziedClassDemo2 {

	public static void main(String[] args) {
		
		Student student = new Student();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 注意：可能会产生性能问题
				synchronized (Student.class) {
					for(int i=0;i<10;i++) {
						System.out.println("Thread t1:"+i);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		t1.start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Student.show1();
			}
		}).start();;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				student.show2();
			}
		}).start();;
		
	}

}


class Student1 {
	
	
	synchronized public static void show1() {
		for(int i=0;i<20;i++) {
			System.out.println("Student ----> show():"+i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
    synchronized public void show2() {
		for(int i=0;i<20;i++) {
			System.out.println(Thread.currentThread().getId()+"--->Student show2():"+i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
