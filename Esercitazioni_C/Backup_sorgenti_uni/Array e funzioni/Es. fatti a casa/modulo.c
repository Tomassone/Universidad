
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include "modulo.h"
#include <stdio.h>

//es 3

/*int readVett(int V[], int dim)
{
	int i = 0, num, size = 0;
	do
	{
		printf("Digita un numero intero: ");
		scanf("%d", &num);
		if (num != 0 && size < dim)
		{
			V[size] = num;
			size++;
		}
		i++;
	}
	while (i < dim && num != 0);
	return i;
}

void confr_vett(int V[], int dim1, int W[], int dim2)
{
	int i, j;	
	for (i = 0; i < dim1; i++)
	{
		for (j = 0; j < dim2; j++)
			if (V[i] == W[j] && i == j)
				printf("Il numero %d e' presente in entrambi i vettori alla posizione %d.\n", V[i], i);
	}
}*/

//es 4

/*int readVett(int V[], int dim)
{
	int i = 0, num, size = 0;
	do
	{
		printf("Digita un numero intero: ");
		scanf("%d", &num);
		if (num != 0 && size < dim)
		{
			V[size] = num;
			size++;
		}
		i++;
	}
	while (i < dim && num != 0);
	return i;
}

void checkPari(int X[], int dim, int* p, int* d)
{
	int i;
	for (i = 0; i < dim - 1; i++)
	{
		if ((X[i] % 2) == 0)
			*p = *p + 1;
		else
			*d = *d + 1;
	}
}*/


//es 7

int readVett(int V[], int dim)
{
	int i = 0, num, size = 0;
	do
	{
		printf("Digita un numero intero: ");
		scanf("%d", &num);
		if (num != 0 && size < dim)
		{
			V[size] = num;
			size++;
		}
		i++;
	}
	while (i < dim && num != 0);
	return i;
}	

int checkPres(int V[], int dim, int n)
{
	int i;
	for (i = 0; i < dim; i++)
	{
		if (V[i] == n)
			return i;
	}
	return UNSUCCESS;
}

//es 8

/*int readVett(int V[], int dim)
{
	int i = 0, num, size = 0;
	do
	{
		printf("Digita un numero intero: ");
		scanf("%d", &num);
		if (num != 0 && size < dim)
		{
			V[size] = num;
			size++;
		}
		i++;
	}
	while (i < dim && num != 0);
	return i;
}

int checkPres(int V[], int dim, int n)
{
	int i;
	for (i = 0; i < dim; i++)
	{
		if (V[i] == n)
			return i;
	}
	return UNSUCCESS;
}*/
