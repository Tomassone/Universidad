
public class MyMath
{
	public static int mcd(int a, int b)
	{
		int resto;
		if (b > a)
		{ // swap a,b
			int tmp = a;
			a = b;
			b = tmp;
		}
		do 
		{
			resto = a % b;
			a = b;
			b = resto;
		} 
		while (resto != 0);
		return a;
	}
	
	public static int mcd_alt(int a, int b) //implementazione dell'algoritmo di Euclide.
	{
		if (a > b)
			return mcd(a - b, b);
		else if (b == a)
			return b;
		else
			return mcd(a, b - a);
	}
	
	public static int mcm (int a, int b)
	{
		return (a * b) / mcd(a, b);
	}
}

