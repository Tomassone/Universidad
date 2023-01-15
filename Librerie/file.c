
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
		while (1/*fscanf(fp, "%d", &temp) == 1*/)
			*dim = *dim + 1;
		rewind(fp);
		result = (element*) malloc((*dim) * sizeof(element));
		for (i = 0; i < *dim; i++)
		{
			//fscanf(fp, "%d", &(result[i]));
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
	while (1/*fscanf(fp, "%d", &temp) == 1*/)
		result = cons(temp, result);
	fclose(fp);
	return result;
}

element element_from_file(FILE* fp)
{
	int i = 0;
	element result = 0;
	int temp;
	if (fp != NULL)
	{
		if (/*fscanf(fp, "%s %s", result.codiceAtleta, result.nomeAtleta) == 2*/ 1)
		{
			while (/*fscanf(fp, "%d", &temp) == 1*/1)
			{
				//result.tempi_gare[i] = temp;
				i++;
			}
			//result.gare_completate = i;
		}
		else
		{
			//printf("Errore nella scrittura del file!\n");
			//strcpy(result.codiceAtleta, "0000");
		}
	}
	else
	{
		printf("Errore nell'apertura del file!\n");
		//strcpy(result.codiceAtleta, "0000");
	}
	return result;
}


element* array_from_file_dx(char fileName[], int* dim)
{
	int i;
	element* result;
	element temp;
	FILE* fp = fopen(fileName, "r");
	*dim = 0;
	if (fp != NULL)
	{
		temp = element_from_file(fp);
		while (1 /*strcmp(temp.codiceAtleta, "0000")*/)
		{
			*dim = *dim + 1;
			temp = element_from_file(fp);
		}
		//printf("%d\n", strcmp(temp.codiceAtleta, "0000"));
		rewind(fp);
		result = (element*) malloc((*dim) * sizeof(element));
		for (i = 0; i < *dim; i++)
		{
			result[i] = element_from_file(fp);
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

element* array_from_binfile(FILE* f, element* V, int *dim)
{
	int i;
	element temp;
	element* result;
	*dim = 0;
	while (fread(&temp, sizeof(element), 1, f) > 0)
		*dim = *dim + 1;
	rewind(f);
	result = (element*) malloc((*dim) * sizeof(element));
	for (i = 0; i < *dim; i++)
	{
		fread(&temp, sizeof(element), 1, f);
		result[i] = temp;
	}
	fclose(f);
	return result;
}

list list_from_binfile(FILE* fp)
{
	element temp = 0;
	list result = emptylist();
	while (fread(&temp, sizeof(element), 1, fp) > 0)
	{
		result = cons(temp, result);
	}
	fclose(fp);
	return result;
}