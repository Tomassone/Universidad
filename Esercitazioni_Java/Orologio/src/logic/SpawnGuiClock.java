package logic;

import sevensegmentdisplay.Display;

public class SpawnGuiClock
{
	public static void main(String[] args)
	{
		Orologio internalClock = new Orologio(0, 0, 0);
		Display displayNumerico = new Display(6); //6 cifre (niente separatori).
		while(true)
		{
			displayNumerico.setValue(internalClock.getSeconds() + internalClock.getMinutes() * 100 + internalClock.getHours() * 10000);
			try 
			{ 
				Thread.sleep(1000); 
			}
			catch(InterruptedException e)
			{
				//codice per gestire l'eccezione.
			}
			internalClock.tic();
		}
	}
}
