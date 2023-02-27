
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include <stdio.h>
#include "modulo.c"

//es 3

#define DIM 10

/*int main()
{
	int V[DIM], W[DIM];
	int dim_l1, dim_l2;
	printf("Primo vettore: \n");
	dim_l1 = readVett(V, DIM);
	printf("Secondo vettore: \n");
	dim_l2 = readVett(W, DIM);
	confr_vett(V, dim_l1, W, dim_l2);
	return 0;
}*/

//es 4

/*int main()
{
	int pari = 0, dispari = 0;
	int V[DIM], dim_l;
	dim_l = readVett(V, DIM);
	checkPari(V, dim_l, &pari, &dispari);
	printf("Nel vettore considerato ci sono %d elementi pari e %d elementi dispari.\n", pari, dispari);
}*/

//es 7

int main()
{
	int i, V[DIM], dim_l;
	dim_l = readVett(V, DIM);
	for (i = 0; i < dim_l; i++)
		if (checkPres(V, dim_l, V[i]) != UNSUCCESS && i != checkPres(V, dim_l, V[i]))
			printf("%d ", V[i]);
	return 0;
}

//es 8

/*int main()
{
	int V[DIM], W[DIM];
	int dim_l1, dim_l2;
	int i;
	printf("Primo vettore: \n");
	dim_l1 = readVett(V, DIM);
	printf("Secondo vettore: \n");
	dim_l2 = readVett(W, DIM);
	for (i = 0; i < dim_l1; i++)
		if (checkPres(W, dim_l2, V[i]) == UNSUCCESS)
			printf("%d ", V[i]);
	return 0;
}*/


