
#include "modulo.h"

//es 1

/*void converti_complex(float re, float im, float* modulo, float* argomento)
{
	if (re != 0)
	{
		*modulo = (float)sqrt((double)re * re + (double)im * im);
		*argomento = (float)atan2((double)im, re);
		printf("\nIl modulo della forma polare e' %f e il suo argomento e' %f, avremmo dunque: x = %f * e^%f*i.\n\n", *modulo, *argomento, *modulo, *argomento);
		printf("Dunque, in forma trigonometrica x = %f * (cos(%f) + sin(%f))\n", *modulo, *argomento, *argomento);
	}
	else
		printf("I valori digitati non sono validi.\n");
}*/

//es 3

/*int millToSec(int n)
{
	int m;
	m = n / 1000;
	return m;
}

int secToMin(int n)
{
	int m;
	m = n / 60;
	return m;
}

void millToDef(int* x, int* y, int* z)
{
	*y = millToSec(*x);
	*x = *x % 1000;
	*z = secToMin(*y);
	*y = *y % 60;
}*/

//es 5

/*int calc_tr(int x, int y, int z, float* X, float* Y)
{
	if ((x < 0 || y < 0 || z < 0) || (x > y + z) || (y > x + z) || (z > x + y))
		return NON_VALID_TR;
	else if ((x == 0 || y == 0 || z == 0) || (x == y + z) || (y == x + z) || (z == x + y))
		return DEGEN_TR;
	else
	{
		*Y = x + y + z; //calcolo perim.
		*X = (float) sqrt((double) *Y / 2 * ((double) *Y / 2  - x) * ((double) *Y / 2 - y) * ((double) *Y / 2 - z));
		return SUCCESS;
	}
}*/

//es 6

double f(double x)
{
	x = x * x;
	return x;
}

float rettangoli(int a, int b, int n)
{
	float area = 0;
	float i, i_pr; 
	for (i = a; i <= b; i = i + ((float) b - (float) a) / (float) n)
	{
		if (i != a)
			area = (i - i_pr) * (float) f((double) i_pr) + area;
		i_pr = i;
	}
	return area;
}