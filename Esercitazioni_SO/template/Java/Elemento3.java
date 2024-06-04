package pkg;

import java.util.Random;

public class Elemento3 extends Thread {
	static private int IN = 1;
	static private int OUT = 0;
	private Random rnd;
	private Monitor mnt;
	private int ID;

	public Elemento3(Random rnd, Monitor mnt) {
		this.rnd = rnd;
		this.mnt = mnt;
		this.ID = rnd.nextInt(1000);
	}

	public void run() {
		try {
			mnt.entraElemento3(OUT);
			System.out.println("Elemento3 in entrata in dir OUT" + ", ID: " + this.ID);
			Thread.sleep(1000 * rnd.nextInt(5));
			mnt.esceElemento3(OUT);
			System.out.println("Elemento3 in uscita in dir OUT" + ", ID: " + this.ID);
			Thread.sleep(1000 * rnd.nextInt(5));
			mnt.entraElemento3(IN);
			System.out.println("Elemento3 in entrata in dir IN" + ", ID: " + this.ID);
			Thread.sleep(1000 * rnd.nextInt(5));
			mnt.esceElemento3(IN);
			System.out.println("Elemento3 in uscita in dir IN" + ", ID: " + this.ID);
			Thread.sleep(1000 * rnd.nextInt(5));

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}