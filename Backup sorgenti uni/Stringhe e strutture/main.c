
#include <stdio.h>
#include "modulo.h"

//es 12

#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)

/*#define DIM 3

void print(int list[], int length)
{
	if (length != 0)
	{
		print(list, length - 1);
		printf("%d", list[length - 1]);
	}
}

void print_inv(int list[], int length)
{
	if (length != 0)
	{
		printf("%d", list[length - 1]);
		print(list, length - 1);
	}
}


int main()
{
	int i, V[DIM];
	for (i = 0; i < DIM; i++)
	{
		printf("Digita un elemento del vettore: ");
		scanf_s("%d", &V[i]);
	}
	print(V, DIM);
	printf("\n");
	print_inv(V, DIM);
	return 0;
}*/

//es 13

/*#define DIM 5

int calc_Sum(int X[], int length)
{
	if (length != 0)
	{
		if ((length - 1) % 2 == 0)
			return calc_Sum(X, length - 1);
		else
			return X[length - 1] + calc_Sum(X, length - 1);
	}
	else
		return 0;
}

int main()
{
	int i, V[DIM], n;
	for (i = 0; i < DIM; i++)
	{
		printf("Digita un elemento del vettore: ");
		scanf_s("%d", &V[i]);
	}
	n = calc_Sum(V, DIM);
	printf("%d", n);
	return 0;
}*/

//es14

/*#define DIM 8

void somme2(int l1[], int length)
{
	int i;
	for (i = 0; i < length - 1; i++)
		if (l1[i] == (l1[i + 1] + l1[i + 2]))
			printf("%d", l1[i]);
}

int main()
{
	int i, V[DIM];
	for (i = 0; i < DIM; i++)
	{
		printf("Digita un elemento del vettore: ");
		scanf_s("%d", &V[i]);
	}
	somme2(V, DIM);
	return 0;
}*/


//es 16

/*#define DIM1 5
#define DIM2 5
#define TYPE int
#define BOOLEAN int
#define RESULT int
#define TRUE 1
#define FALSE 0

BOOLEAN equals(TYPE el1, TYPE el2)
{
	if (el1 = el2)
		return TRUE;
	else
		return FALSE;
}

RESULT compareTo(TYPE v1[], TYPE v2[], int dim1, int dim2)
{
	int i, j;
	int res = TRUE;
	BOOLEAN ctrl[DIM2];
	if (dim1 != dim2)
		res = FALSE;
	for (i = 0; i < dim2; i++)
		ctrl[i] = FALSE;
	for (i = 0; i < dim1 && res == TRUE; i++)
	{
		res = FALSE;
		for (j = 0; j < dim2 && res == FALSE; j++)
		{
			if (v1[i] == v2[j] && ctrl[j] == FALSE)
			{
				res = TRUE;
				ctrl[j] = TRUE;
			}
		}
	}
	return res;
}

int main()
{
	int i, V[DIM1], W[DIM2];
	printf("Primo vettore: \n");
	for (i = 0; i < DIM1; i++)
	{
		printf("Digita un elemento del vettore: ");
		scanf_s("%d", &V[i]);
	}
	printf("Secondo vettore: \n");
	for (i = 0; i < DIM2; i++)
	{
		printf("Digita un elemento del vettore: ");
		scanf_s("%d", &W[i]);
	}
	if (compareTo(V, W, DIM1, DIM2) == TRUE)
		printf("I due vettori sono uguali.\n");
	else
		printf("I due vettori non sono uguali.\n");
	return 0;
}*/

//es 1 (stringhe)

#define DIM1 15
#define DIM2 25
#define DIM3 40

int conc(char A[], char B[], char C[], int dim3)
{
	int i = 0, j = 0, h;
	while (A[i] != '\0')
	{
		C[i] = A[i];
		i++;
	}
	C[i] = '\0';
	while (B[j] != '\0')
		j++;
	for (h = 0; h < j && (h + i) ; h++)
		C[h + i] = B[h];
	C[h + i] = '\0';
	printf("%s\n", C);
	return (h + i) - 1;
}

int main()
{
	int dim_l3;
	char U[DIM1], V[DIM2], W[DIM3];
	printf("Digita una stringa: ");
	scanf_s("%s", U, DIM1);
	printf("Digita una stringa: ");
	scanf_s("%s", V, DIM2);
	dim_l3 = conc(U, V, W, DIM3);
	printf("Il numero di caratteri copiati nella stringa finale e' %d.\n", dim_l3);
	return 0;
}