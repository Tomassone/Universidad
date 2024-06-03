package pkg;

import java.util.Random;

public class UtenteC extends Thread {
	private int ID;
	private Random rnd;
	private Monitor mnt; 
	private int stScelta;
	
	public UtenteC(Random rnd, Monitor mnt)
	{
		this.rnd = rnd;
		this.mnt = mnt;
		this.ID = rnd.nextInt(9000);
		this.stScelta = rnd.nextInt(3);
	}
	
	public void run()
	{
		try {
			mnt.richiediBiciC(this.stScelta);
			System.out.println("Una coppia è entrata, ID: " + this.ID + ", stazione: " + this.stScelta + ", cassa: " + this.mnt.cassa[this.stScelta]);
			Thread.sleep(1000 * rnd.nextInt(10));
			this.stScelta = rnd.nextInt(3);
			mnt.cediBiciC(this.stScelta);
			System.out.println("Una coppia è uscita, ID: " + this.ID + ", stazione: " + this.stScelta + ", cassa: " + this.mnt.cassa[this.stScelta]);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
