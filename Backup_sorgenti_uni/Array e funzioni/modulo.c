
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include "modulo.h"
#include <stdio.h>

//es 1

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

void check_val(int V[], int dim, int n)
{
	int i;
	for (i = 0; i < dim; i++)
	{
		if (V[i] == n)
			printf("A tale indice del vettore e' presente il valore cercato: %d.\n", i);
	}
}*/

//es 2

/*int readVett(int V[], int dim, int n, int* index)
{
	int i;
	for (i = 0; i < dim; i++)
	{
		if (V[i] == n)
		{
			*index = i;
			return FOUND;
		}
	}
	return NOT_FOUND;
}*/

//es 5

/*#include <stdio.h>

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
	} while (i < dim && num != 0);
	return size;
}

int incrVett(int V[], int dim1, int W[], int dim2, int X[])
{
	int i, j, k = 0;
	for (i = 0; i < dim1; i++)
	{
		for (j = 0; j < dim2; j++)
		{
			if (V[i] == W[j])
			{
				X[k] = V[i];
				printf("%d ", X[k]);
				k++;
			}
		}
	}
	return k;
}*/

//es 6

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
	return size;
}

int removeRep(int V[], int dim1, int W[], int dim2)
{
	int i, size = 0, j, n;
	for (i = 0; i < dim1 && size < dim2; i++)
	{
		n = 0;
		if (i == 0)
		{
			W[size] = V[i];
			printf("%d ", W[size]);
			size++;
		}
		else
		{
			for (j = 0; j < size; j++)
				if (V[i] == W[j])
					n++;
			if (n == 0)
			{
				W[size] = V[i];
				printf("%d ", W[size]);
				size++;
			}
		}
	}
	return size;
}*/

//es 9

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
	} while (i < dim && num != 0);
	return size;
}

void confr_vett(int V[], int dim1, int W[], int dim2)
{
	int i = 0, j = 0;
	while (i < dim1 && j < dim2)
	{
		if (V[i] < W[j])
		{
			printf("%d", V[i]);
			i++;
		}
		else
		{
			printf("%d", W[j]);
			j++;
		}
	}
	while(i < dim1)
	{
		printf("%d", V[i]);
		i++;
	}
	while(j < dim2)
	{
		printf("%d", W[j]);
		j++;
	}
}
