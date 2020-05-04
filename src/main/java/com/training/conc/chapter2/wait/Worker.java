package com.training.conc.chapter2.wait;

public class Worker extends  Thread {
	
    private Buyer man;
    
    private boolean isFree = true;
	@Override
	public void run() {
		if(!isFree) {
			System.out.println("[Worker]--->收钱");
			System.out.println("[Worker]--->打印车票");
			try {
				Thread.sleep(6*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			synchronized (man) {
//				man.notify();
//			}
			System.out.println("[Worker]--->工作结束");
		}
	

	}
	public Worker(Buyer man) {
		super();
		this.man = man;
	}
	public void receive(double money) {
		isFree = false;
	}
	
	

}
