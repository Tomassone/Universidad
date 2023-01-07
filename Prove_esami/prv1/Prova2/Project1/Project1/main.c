
#define _CRT_SECURE_NO_DEPRECATE
#include "element.h"
#include "list.h"
#include "ordinamento.h"
#include <stdlib.h>

int main()
{
	int dim, dim1, i, j, ctrl = 0;
	Specie* result;
	float* elenco_punt;
	list l;
	Avvistamento* elenco;
	FILE* fp = fopen("..\\File\\avvistamenti.txt", "r");
	result = leggiUccelli("..\\File\\uccelli.txt", &dim);
	//stampaUccelli(result, dim);
	if (fp == NULL)
		printf("Errore di lettura\n");
	else
	{
		l = leggiAvvistamenti(fp);
		//showlist(l);
		//printf("%f", valore_avvistamento(head(l), result, dim));
		elenco = ordinaAvvistamenti(l, result, dim, &dim1);
		printf("\n");
		for (i = 0; i < dim1; i++)
			printf("%s %s %d %d\n", elenco[i].nome, elenco[i].codice, (elenco[i].numero), (elenco[i].distanza));
		//scrivi_migliore("..\\File\\prova.txt", l, result, dim);
		printf("\n");
		elenco_punt = (float*)malloc(dim1 * sizeof(float));
		for (i = 0; i < dim1; i++)
		{
			elenco_punt[i] = 0;
		}
		bubbleSort(elenco_punt, dim1);
		for (i = 0; i < dim1; i++)
		{
			for (j = 0; j < dim1; j++)
			{
				if (!strcmp(elenco[i].nome, elenco[j].nome) && elenco_punt[j] == 1)
					ctrl = 1;
			}
			if (ctrl == 0)
			{
				printf("%s--%f\n", elenco[i].nome, punteggioAmico(elenco[i].nome, l, result, dim));
				elenco_punt[i] = 1;
			}
			ctrl = 0;
		}
		free(result);
		free(elenco);
		free(elenco_punt);
		freelist(l);
		fclose(fp);
	}
	return 0;
}