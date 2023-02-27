
#define _CRT_SECURE_NO_DEPRECATE

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
		while (fscanf(fp, "%d %s %f %d/%d/%d", &(temp.id), temp.nome, &(temp.prezzo), &(temp.data_scadenza.anno), &(temp.data_scadenza.mese), &(temp.data_scadenza.giorno)) == 6)
			*dimI = *dimI + 1;
		rewind(fp);
		result = (Ingrediente*) malloc(*dimI * sizeof(Ingrediente));
		for (i = 0; i < *dimI; i++)
		{
			fscanf(fp, "%d %s %f %d/%d/%d", &(temp.id), temp.nome, &(temp.prezzo), &(temp.data_scadenza.anno), &(temp.data_scadenza.mese), &(temp.data_scadenza.giorno));
			result[i] = temp;
		}
		fclose(fp);
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
		printf("%d %s %f %02d/%02d/%02d\n", (temp.id), temp.nome, (temp.prezzo), (temp.data_scadenza.anno), (temp.data_scadenza.mese), (temp.data_scadenza.giorno));
	}
	else
		printf("\n");
}

Ingrediente trova(int id, Ingrediente* ing, int dimI)
{
	int i;
	Ingrediente not_found;
	for (i = 0; i < dimI; i++)
	{
		if (ing[i].id == id)
			return ing[i];
	}
	strcpy(not_found.nome, "sconosciuto");
	return not_found;
}