package com.training.conc.chapter2.wait;


public class Demo {

	public static void main(String[] args) throws InterruptedException {
		Object obj = new Object();
		Thread.sleep(0);
	
	    Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					synchronized (this) {
						this.wait(2*1000);
					}
					System.out.println(" child end");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	    t.start();
	    System.out.println(" main end");

	}

}
