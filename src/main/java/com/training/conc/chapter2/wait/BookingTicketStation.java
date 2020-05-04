package com.training.conc.chapter2.wait;

public class BookingTicketStation {
	
	public static void main(String[] args) {
		Buyer man = new Buyer();
		bookTicket(man);
		
	}

	/**
	 * 
	 * 描述：TODO
	 * @author John Goo
	 * @date 2020年4月13日上午9:58:58
	 * @param man
	 */
	public static void bookTicket(Buyer man) {
		man.start();
		Worker worker  = new Worker(man);
		man.buy(100.80,worker);
		worker.start();
		  
	}
	
	

}
