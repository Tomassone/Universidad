package frazlib;
import frazione.Frazione;

public class FrazLib
{

	public static Frazione sum(Frazione[] tutte)
	{
		Frazione result;
		result = new Frazione(0, 1); //inizializzo la mia frazione di somma.
		for (Frazione fraz : tutte)
			result = result.sum(fraz); //necessario perchè i metodi della classe non sono distruttivi.
		return result;
	}
		
	public static Frazione mul(Frazione[] tutte)
	{
		Frazione result;
		result = new Frazione(1, 1); //inizializzo la mia frazione di somma.
		for (Frazione fraz : tutte)
			result = result.mul(fraz); //necessario perchè i metodi della classe non sono distruttivi.
		return result;
	}

}
