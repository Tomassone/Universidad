
public class SolveEquation
{
	public static void main (String[] args)
	{
		int a, b, c; //coefficienti del polinomio di secondo grado.
		float discriminante, base, salto;
		if (args.length != 3)
			System.out.println("Non hai digitato abbastanza argomenti!");
		else
		{
			try //provo ad eseguire la conversione da stringa a intero dei coefficienti e i calcoli successivi.
			{
				a = Integer.parseInt(args[0]);
				b = Integer.parseInt(args[1]);
				c = Integer.parseInt(args[2]);
				System.out.println("Hai digitato: " + a + "x^2 + " + b + "x + " + c + ".");
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
			catch (Exception e) //nel caso in cui gli argomenti digitati non fossero numeri.
			{
				System.out.println("Al programma è stato fornito un input non valido!");
			}
		}
	}
}

