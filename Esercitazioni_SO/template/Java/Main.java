package pkg;

import java.util.Random;

public class Main {

	static private int N = 5;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Monitor mnt = new Monitor();
		Random rnd = new Random();
		Elemento1[] elenco1 = new Elemento1[N];
		Elemento2[] elenco2 = new Elemento2[N];
		Elemento3[] elenco3 = new Elemento3[N];
		
		for (int i = 0; i < N; i++)
		{
			elenco1[i] = new Elemento1(rnd, mnt);
			elenco2[i] = new Elemento2(rnd, mnt);
			elenco3[i] = new Elemento3(rnd, mnt);
		}
		
		for (int i = 0; i < N; i++)
		{
			elenco1[i].start();
			elenco2[i].start();
			elenco3[i].start();
		}
		
		for (int i = 0; i < N; i++)
		{
			elenco1[i].join();
			elenco2[i].join();
			elenco3[i].join();
		}
		
		System.out.println("Lavoro fatto!");
	}

}
