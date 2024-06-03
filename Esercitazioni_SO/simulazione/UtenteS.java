package pkg;

import java.util.Random;

public class UtenteS extends Thread {
	private int ID;
	private Random rnd;
	private Monitor mnt; 
	private int stScelta;
	
	public UtenteS(Random rnd, Monitor mnt)
	{
		this.rnd = rnd;
		this.mnt = mnt;
		this.ID = rnd.nextInt(9000);
		this.stScelta = rnd.nextInt(3);
	}
	
	public void run()
	{
		try {
			Thread.sleep(1000);
			mnt.richiediBiciS(this.stScelta);
			System.out.println("Un singolo è entrato, ID: " + this.ID + ", stazione: " + this.stScelta + ", cassa: " + this.mnt.cassa[this.stScelta]);
			Thread.sleep(1000 * rnd.nextInt(5));
			this.stScelta = rnd.nextInt(3);
			mnt.cediBiciS(this.stScelta);
			System.out.println("Un singolo è uscito, ID: " + this.ID + ", stazione: " + this.stScelta + ", cassa: " + this.mnt.cassa[this.stScelta]);
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
