
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include <stdio.h>
#include "modulo.h"

//es 1

/*#define DIM 10

int main()
{
	int dim_logica, V[DIM], k;
	dim_logica = readVett(V, DIM);
	printf("Digita il valore da cercare nel vettore: ");
	scanf("%d", &k);
	check_val(V, dim_logica, k);
	return 0;
}*/

//es 2

/*#define DIM 5

int main()
{
	int n, index, check;
	int V[DIM] = { 1, 2, 3, 4, 5 };
	printf("Digita un valore da cercare nell'array considerato: ");
	scanf("%d", &n);
	check = readVett(V, DIM, n, &index);
	if (check == NOT_FOUND)
		printf("L'elemento cercato non e' presente nell'array considerato.\n");
	else 
		printf("L'elemento cercato e' presente nella posizione %d nell'array considerato.\n", index);
	return 0;
}*/

//da fare 3, 4

//es 5

/*#define DIM1 5
#define DIM2 5
#define DIM3 5

int main()
{
	int dim_l1, dim_l2, dim_l3;
	int V[DIM1], W[DIM2], Z[DIM3];
	printf("Primo vettore: \n");
	dim_l1 = readVett(V, DIM1);
	printf("Secondo vettore: \n");
	dim_l2 = readVett(W, DIM2);
	printf("Terzo vettore: \n");
	dim_l3 = incrVett(V, dim_l1, W, dim_l2, Z);
	if (dim_l3 == 0)
		printf("I due vettori non hanno valori in comune.\n");
	return 0;
}*/

//es 6

/*#define DIM1 5
#define DIM2 5

int main()
{
	int dim_l1, dim_l2;
	int V[DIM1], W[DIM2];
	printf("Primo vettore: \n");
	dim_l1 = readVett(V, DIM1);
	printf("Secondo vettore: \n");
	dim_l2 = removeRep(V, dim_l1, W, DIM2);
	return 0;
}*/

//es 9

#define DIM1 5
#define DIM2 5
#define DIM3 5

int main()
{
	int dim_l1, dim_l2;
	int V[DIM1], W[DIM2];
	printf("Primo vettore: \n");
	dim_l1 = readVett(V, DIM1);
	printf("Secondo vettore: \n");
	dim_l2 = readVett(W, DIM2);
	confr_vett(V, dim_l1, W, dim_l2);
	return 0;
}