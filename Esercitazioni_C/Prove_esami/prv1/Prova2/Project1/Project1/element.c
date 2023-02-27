
#define _CRT_SECURE_NO_DEPRECATE
#include "element.h"

Specie* leggiUccelli(char nomefile[], int* dim)
{
	int i;
	Specie* result;
	Specie temp;
	FILE* fp = fopen(nomefile, "r");
	*dim = 0;
	if (fp != NULL)
	{
		while (fscanf(fp, "%s %s %f %c", temp.codice, temp.nome, &(temp.raro), &(temp.tipo)) == 4)
			*dim = *dim + 1;
		rewind(fp);
		result = (Specie*) malloc((*dim) * sizeof(Specie));
		for (i = 0; i < *dim; i++)
		{
			fscanf(fp, "%s %s %f %c", result[i].codice, result[i].nome, &(result[i].raro), &(result[i].tipo));
		}
		fclose(fp);
	}
	else
	{
		result = NULL;
		printf("Errore nell'apertura del file!\n");
	}
	return result;
}

void stampaUccelli(Specie* uccelli, int dim)
{
	if (dim > 0)
	{
		stampaUccelli(uccelli, dim - 1);
		printf("%s %s %f %c\n", uccelli[dim - 1].codice, uccelli[dim - 1].nome, uccelli[dim - 1].raro, uccelli[dim - 1].tipo);
	}
}

float valore_avvistamento(Avvistamento a, Specie* uccelli, int dim_u)
{
	int found = 0;
	Specie temp;
	float result;
	int i = 0;
	while (found == 0 && i < dim_u)
	{
		if (!strcmp(a.codice, uccelli[i].codice))
		{
			found = 1;
			temp = uccelli[i];
		}
		i++;
	}
	if (found == 0)
		return 0.0F;
	else
	{
		//printf("%f %d %d\n", temp.raro, a.numero, a.distanza);
		result = (temp.raro * temp.raro * a.numero) / a.distanza;
		return result;
	}
}
