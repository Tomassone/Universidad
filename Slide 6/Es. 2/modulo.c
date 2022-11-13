
#include "modulo.h"

//es 2

/*int MCD(int x, int y)
{
	if (x == 0 && y == 0)
		return 0;
	else if (x != 0 && y == 0)
		return x;
	else if (x == 0 && y != 0)
		return y;
	else
	{
		if (x == y)
			return x;
		else if (x > y)
			return MCD(x - y, y);
		else if (x < y)
			return MCD(x, y - x);
		else
			return 0;
	}
}

int mcm(int a, int b)
{
	int mcm;
	mcm = (a * b) / MCD (a, b);
	return mcm;
}*/

//es 3

int fact(int n)
{
	int i, ris;
	if (n < 0)
		return WRN_VALUE;
	else if (n == 0)
		return 1;
	else
	{
		ris = 1;
		for(i = 2; i <= n; i++)
			ris = ris * i;
		return ris;
	}
}

int coeff_bin(int n, int k)
{
	int ris;
	if (n < k)
		return WRN_VALUE;
	else
	{
		ris = fact(n) / (fact(n - k) * fact(k));
		return ris;
	}
}
