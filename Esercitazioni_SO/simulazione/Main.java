package pkg;

import java.util.Random;

public class Main {

	static private int nUtenti = 10;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Random rnd = new Random();
		Monitor mnt = new Monitor();
		UtenteS[] utentis = new UtenteS[nUtenti];
		UtenteC[] utentic = new UtenteC[nUtenti];
		
		for (int i = 0; i < nUtenti; i++)
		{
			utentis[i] = new UtenteS(rnd, mnt);
			utentic[i] = new UtenteC(rnd, mnt);
		}
		
		for (int i = 0; i < nUtenti; i++)
		{
			utentis[i].start();
			utentic[i].start();
		}
		
		for (int i = 0; i < nUtenti; i++)
		{
			utentis[i].join();
			utentic[i].join();
		}

		System.out.println("Finito!");
	}

}
