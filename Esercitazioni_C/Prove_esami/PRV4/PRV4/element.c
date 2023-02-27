
#define _CRT_SECURE_NO_DEPRECATE

#include "element.h"

void assignElement(element* a, element* b)
{
	*a = *b;
}

Atleta leggiSingoloAtleta(FILE* fp)
{
	int i = 0;
	Atleta result;
	int temp;
	if (fp != NULL)
	{
		if (fscanf(fp, "%s %s", result.codiceAtleta, result.nomeAtleta) == 2)
		{
			while (fscanf(fp, "%d", &temp) == 1)
			{
				result.tempi_gare[i] = temp;
				i++;
			}
			result.gare_completate = i;
		}
		else
		{
			//printf("Errore nella scrittura del file!\n");
			strcpy(result.codiceAtleta, "0000");
		}
	}
	else
	{
		printf("Errore nell'apertura del file!\n");
		strcpy(result.codiceAtleta, "0000");
	}
	return result;
}


Atleta* leggiAtleti(char fileName[], int* dim)
{
	int i;
	element* result;
	element temp;
	FILE* fp = fopen(fileName, "r");
	*dim = 0;
	if (fp != NULL)
	{
		temp = leggiSingoloAtleta(fp);
		while (strcmp(temp.codiceAtleta, "0000"))
		{
			*dim = *dim + 1;
			temp = leggiSingoloAtleta(fp);
		}
		//printf("%d\n", strcmp(temp.codiceAtleta, "0000"));
		rewind(fp);
		result = (element*) malloc((*dim) * sizeof(element));
		for (i = 0; i < *dim; i++)
		{
			result[i] = leggiSingoloAtleta(fp);
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

void stampaAtleta(Atleta a)
{
	int i, tempo_complessivo = -1;
	printf("%s %s %d ", a.codiceAtleta, a.nomeAtleta, a.gare_completate);
	for (i = 0; i < a.gare_completate; i++)
		printf("%d ", a.tempi_gare[i]);
	if (a.gare_completate == 3)
		tempo_complessivo = a.tempi_gare[0] + a.tempi_gare[1] + a.tempi_gare[2];
	printf("tempo complessivo: %d", tempo_complessivo);
	printf("\n");
}

int compare(Atleta a1, Atleta a2, char* tipo)
{
	int i = 0;
	if (!strcmp(tipo, "Totale"))
	{
		if (a1.gare_completate != 3 || a2.gare_completate != 3)
		{
			printf("Comparazione impossibile.\n");
			return 0;
		}
		else
		{
			int tot1 = 0, tot2 = 0;
			for (i = 0; i < 3; i++)
			{
				tot1 = tot1 + a1.tempi_gare[i];
				tot2 = tot2 + a2.tempi_gare[i];
			}
			if (tot1 < tot2)
				return -1;
			else if (tot1 == tot2)
				return 0;
			else return 1;
		}
	}
	else if (!strcmp(tipo, "Nuoto"))
	{
		if (a1.gare_completate == 0 || a2.gare_completate == 0)
		{
			printf("Comparazione impossibile.\n");
			return 0;
		}
		if (a1.tempi_gare[0] < a2.tempi_gare[0])
			return -1;
		else if (a1.tempi_gare[0] == a2.tempi_gare[0])
			return 0;
		else return 1;
	}
	else if (!strcmp(tipo, "Bici"))
	{
		if (a1.gare_completate == 1 || a2.gare_completate == 1)
		{
			printf("Comparazione impossibile.\n");
			return 0;
		}
		if (a1.tempi_gare[1] < a2.tempi_gare[1])
			return -1;
		else if (a1.tempi_gare[1] == a2.tempi_gare[1])
			return 0;
		else return 1;
	}
	else if (!strcmp(tipo, "Corsa"))
	{
		if (a1.gare_completate == 2 || a2.gare_completate == 2)
		{
			printf("Comparazione impossibile.\n");
			return 0;
		}
		if (a1.tempi_gare[2] < a2.tempi_gare[2])
			return -1;
		else if (a1.tempi_gare[2] == a2.tempi_gare[2])
			return 0;
		else return 1;
	}
	else
	{
		printf("Opzione non contemplata\n");
		return 0;
	}
}
