package libreriastatica;

public class MatriceLib
{
	public static double[][] sommaMatrici(double[][] a, double[][] b)
	{
		double[][] c = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[0].length; j++)
					c[i][j] = a[i][j] + b[i][j];
		return c;
	}
	
	public static double[][] prodottoMatrici(double[][] a, double[][] b)
	{
		double[][] c = new double[a.length][b[0].length];
		double temp = 0;
		for (int i = 0; i < a.length; i++) //esegue il prodotto riga-colonna completo.
		{
			for (int k = 0; k < b[0].length; k++) //esegue il prodotto fra una riga e n colonne.
			{
				for (int j = 0; j < a[0].length; j++) //esegue il prodotto fra una singola riga e una singola colonna.
					temp = temp + a[i][j] * b [j][k];
				c[i][k] = temp;
				temp = 0;
			}
		}
		return c;
	}
			
	public static void stampaMatrice(double[][] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[0].length; j++)
				System.out.print(a[i][j] + " ");
			System.out.print('\n');
		}	
	}
}
