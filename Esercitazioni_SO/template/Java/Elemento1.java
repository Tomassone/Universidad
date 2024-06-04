package pkg;

import java.util.Random;

public class Elemento1 extends Thread {
	static private int IN = 1;
	static private int OUT = 0;
	private Random rnd;
	private Monitor mnt;
	private int ID;

	public Elemento1(Random rnd, Monitor mnt) {
		this.rnd = rnd;
		this.mnt = mnt;
		this.ID = rnd.nextInt(1000);
	}

	public void run() {
		try {
			mnt.entraElemento1(IN, 0);
			System.out.println("Elemento1 in entrata in dir IN" + ", ID: " + this.ID);
			Thread.sleep(1000 * rnd.nextInt(10));
			int tipo = mnt.esceElemento1(IN);
			System.out.println("Elemento1 in uscita in dir IN" + ", ID: " + this.ID);
			Thread.sleep(1000 * rnd.nextInt(10));
			mnt.entraElemento1(OUT, tipo);
			System.out.println("Elemento1 in entrata in dir OUT" + ", ID: " + this.ID);
			Thread.sleep(1000 * rnd.nextInt(10));
			mnt.esceElemento1(OUT);
			System.out.println("Elemento1 in uscita in dir OUT" + ", ID: " + this.ID);
			Thread.sleep(1000 * rnd.nextInt(10));

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
