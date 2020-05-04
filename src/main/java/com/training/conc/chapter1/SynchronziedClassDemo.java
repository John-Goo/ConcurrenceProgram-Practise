package com.training.conc.chapter1;

public class SynchronziedClassDemo {

	public static void main(String[] args) {
		
		Student student = new Student();

		Student student2 = new Student();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				student.show2();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				student2.show2();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Student.show1();
			}
		}).start();
		
		
	}

}


class Student {
	
	
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
