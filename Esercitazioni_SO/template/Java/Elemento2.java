package pkg;

import java.util.Random;

public class Elemento2 extends Thread {
	static private int IN = 1;
	static private int OUT = 0;
	private Random rnd;
	private Monitor mnt;
	private int ID;
	
	public Elemento2(Random rnd, Monitor mnt)
	{
		this.rnd = rnd;
		this.mnt = mnt;
		this.ID = rnd.nextInt(1000);
	}
	
	public void run()
	{
		try {
			mnt.entraElemento2(IN);
			System.out.println("Elemento2 in entrata in dir IN" + ", ID: " + this.ID);
			Thread.sleep(1000*rnd.nextInt(10));
			mnt.esceElemento2(IN);
			System.out.println("Elemento2 in uscita in dir IN" + ", ID: " + this.ID);
			Thread.sleep(1000*rnd.nextInt(10));
			mnt.entraElemento2(OUT);
			System.out.println("Elemento2 in entrata in dir OUT" + ", ID: " + this.ID);
			Thread.sleep(1000*rnd.nextInt(10));
			mnt.esceElemento2(OUT);
			System.out.println("Elemento2 in uscita in dir OUT" + ", ID: " + this.ID);
			Thread.sleep(1000*rnd.nextInt(10));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}