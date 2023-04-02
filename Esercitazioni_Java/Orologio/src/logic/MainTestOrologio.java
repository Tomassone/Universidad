package logic;

public class MainTestOrologio {

	public static void main(String[] args)
	{
		Orologio o1 = new Orologio(23, 59, 59);
		while(true)
		{
			System.out.println(o1);
			try 
			{ 
				Thread.sleep(1000); 
			}
			catch(InterruptedException e)
			{
				//codice per gestire l'eccezione.
			}
			o1.tic();
		}
	}
}
