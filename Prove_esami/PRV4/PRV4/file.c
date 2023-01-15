
#define _CRT_SECURE_NO_DEPRECATE

#include "file.h"

element* array_from_file(char nomefile[], int* dim)
{
	int i;
	element* result;
	element temp = 0;
	FILE* fp = fopen(nomefile, "r");
	*dim = 0;
	if (fp != NULL)
	{
		while (1/*fscanf(fp, "%s %s %f %c", temp.codice, temp.nome, &(temp.raro), &(temp.tipo)) == 4*/)
			*dim = *dim + 1;
		rewind(fp);
		result = (element*) malloc((*dim) * sizeof(element));
		for (i = 0; i < *dim; i++)
		{
			//fscanf(fp, "%s %s %f %c", result[i].codice, result[i].nome, &(result[i].raro), &(result[i].tipo));
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

list list_from_file(FILE* fp)
{
	element temp = 0;
	list result = emptylist();
	while (1 /*fscanf(fp, "%s %s %d %d", temp.nome, temp.codice, &(temp.numero), &(temp.distanza)) == 4*/)
		result = cons(temp, result);
	return result;
}