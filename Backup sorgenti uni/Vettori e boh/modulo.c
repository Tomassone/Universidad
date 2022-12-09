
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include "modulo.h"

// es 9 - slide 7

/*#include <stdio.h>

int calc_max(int max)
{
	int n;
	printf("Digita un valore: ");
	scanf("%d", &n);
	if (n == 0)
		return max;
	else
	{
		if (n > max)
			max = n;
		return calc_max(max);
	}
}

int calc_min(int min)
{
	int n;
	printf("Digita un valore: ");
	scanf("%d", &n);
	if (n == 0)
		return min;
	else
	{
		if (n < min)
			min = n;
		return calc_min(min);
	}
}

void calc_maxmin(int* max, int* min)
{
	int n;
	printf("Digita un valore: ");
	scanf("%d", &n);
	if (n == 0);
	else
	{
		if (n > *max)
			*max = n;
		if (n < *min)
			*min = n;
		calc_maxmin(&(*max), &(*min));
	}
}*/

// es11 - slide 7

/*int secondoGrado(int a, int b, int c, float* x1, float* x2)
{
	if ((b * b) >= (4 * a * c))
	{
		*x1 = ((float) - b - (float) sqrt((double) b * (double) b - 4 * (double) a * (double) c)) / (2 * (float) a);
		*x2 = ((float) - b + (float) sqrt((double)b * (double)b - 4 * (double)a * (double)c)) / (2 * (float)a);
		return VALID;
	}
	else
		return NON_VALID;
}*/

// es 8 - slide 7

