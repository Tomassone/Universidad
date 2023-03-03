
public class SecondGradeEquation 
{
	public static void SolveEquation (int a, int b, int c)
	{
		float discriminante, base, salto;
		if (a == 0)
		{
			if (b == 0) //zero soluzioni.
				System.out.println("Questa equazione non ha soluzioni.");
			else //una soluzione reale.
			{
				base = - (float) c /((float) b);
				System.out.println("Questa equazione ha una soluzione reale: " + base + ".");
			}
		}
		else
		{
			discriminante = (float) Math.pow(b, 2) - 4 * a * c; 
			System.out.println("Discriminante calcolato: " + discriminante + ".");
			if (discriminante > 0) //due soluzioni reali distinte.
			{
				base = - (float) b /(2 * (float) a);
				salto = (float) Math.sqrt(discriminante) / (2 * (float) a);
				System.out.println("Questa equazione ha due soluzioni reale distinte: " + base + " ± " + salto + ".");
			}
			else if (discriminante == 0) //due soluzioni reali coincidenti.
			{
				base = - (float) b /(2 * (float) a);
				System.out.println("Questa equazione ha due soluzioni reale coincidenti: " + base + ".");
			}
			else //due soluzioni immaginarie.
			{
				base = - (float) b /(2 * (float) a);
				salto = (float) Math.sqrt(Math.abs(discriminante)) / (2 * (float) a);
				System.out.println("Questa equazione ha due soluzioni immaginarie: " + base + " ± " + salto + "i.");
			}
		}
	}
}

