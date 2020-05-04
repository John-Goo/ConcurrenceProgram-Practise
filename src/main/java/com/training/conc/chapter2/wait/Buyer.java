package com.training.conc.chapter2.wait;

public class Buyer extends Thread {
	
	private Ticket ticket ;

	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void buy(double money,Worker worker) {
		worker.receive(money);
	}

	@Override
	public void run() {
		// 把钱给工作人员
		System.out.println("[ Buyer]--->钱给工作人员");
//		try {
//			synchronized (this) {
//				this.wait();
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("[ Buyer]--->拿到票，离开");
		
	}

}
