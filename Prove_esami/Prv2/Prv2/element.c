
#include "element.h"

Ingrediente* leggiIngredienti(char* nomefile, int* dimI)
{
	int i;
	FILE* fp = fopen(nomefile, "r");
	Ingrediente temp;
	Ingrediente* result;
	if (fp == NULL)
	{
		printf("Errore nell'apertura del file!\n");
		exit(1);
	}
	else
	{
		*dimI = 0;
		while (fscanf("%d %s %f %d/%d/%d", &(temp.id), temp.nome, &(temp.prezzo), &(temp.data_scadenza.anno), &(temp.data_scadenza.mese), &(temp.data_scadenza.giorno)) == 6)
			*dimI = *dimI + 1;
		rewind(fp);
		result = (Ingrediente*)malloc(*dimI * sizeof(Ingrediente));
		for (i = 0; i < *dimI; i++)
		{
			fscanf("%d %s %f %d/%d/%d", &(temp.id), temp.nome, &(temp.prezzo), &(temp.data_scadenza.anno), &(temp.data_scadenza.mese), &(temp.data_scadenza.giorno));
			result[i] = temp;
		}
		return result;
	}
}

void stampaIngredienti(Ingrediente* i, int dimI)
{
	Ingrediente temp;
	if (dimI > 0)
	{
		stampaIngredienti(i, dimI - 1);
		temp = i[dimI - 1];
		printf("%d %s %f %d/%d/%d", (temp.id), temp.nome, (temp.prezzo), (temp.data_scadenza.anno), (temp.data_scadenza.mese), (temp.data_scadenza.giorno));
	}
	else
		printf("\n");
}