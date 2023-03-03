
public class GetInput
{
	public static void main (String[] args)
	{
		int a, b, c; //coefficienti del polinomio di secondo grado.
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
				SecondGradeEquation.SolveEquation(a, b, c);
			}
			catch (Exception e) //nel caso in cui gli argomenti digitati non fossero numeri.
			{
				System.out.println("Al programma è stato fornito un input non valido!");
			}
		}
	}
}

