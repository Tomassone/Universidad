
#define _CRT_SECURE_NO_DEPRECATE

#include "libri.h"

list leggi(char fileName[], char select, char nome[])
{
	element temp;
	FILE* fp = fopen(fileName, "r");
	list result = emptylist();
	if (fp != NULL)
	{
		while (fscanf(fp, "%s %s %d %f ", temp.nome_cliente, temp.nome_fornitore, &(temp.copie_ordinate), &(temp.prezzo)) == 4)
		{
			readField(temp.titolo, '\n', fp);
			if (select == 'C')
			{
				if (!strcmp(temp.nome_cliente, nome))
					result = cons(temp, result);
			}
			if (select == 'F')
			{
				if (!strcmp(temp.nome_fornitore, nome))
					result = cons(temp, result);
			}
		}
		fclose(fp);
	}
	else
		printf("Errore di lettura!\n");
	return result;
}

void stampaOrdini(list elenco)
{
	element temp;
	if (!empty(elenco))
	{
		temp = head(elenco);
		showlist(tail(elenco));
		printElement(head(elenco));
		return;
	}
	else
	{
		printf("\n\n");
		return;
	}
}

int contaCopieOrdinate(list elenco, char* titolo)
{
	int n = 0;
	while (!empty(elenco))
	{
		//printString(head(elenco).titolo);
		if (!strcmp(head(elenco).titolo, titolo))
			n = n + 1;
		elenco = tail(elenco);
	}
	return n;
}

void aggregaPerTitolo(list elenco)
{
	while (!empty(elenco))
	{
		printf("Titolo: ");
		printString(head(elenco).titolo);
		printf("Numero di copie presenti: %d\n", contaCopieOrdinate(elenco, head(elenco).titolo));
		elenco = tail(elenco);
	}
}

Ordine* prepara(list elenco, int* dim)
{
	int i = 0;
	*dim = length(elenco);
	Ordine* result = (Ordine*) malloc((*dim) * sizeof(Ordine));
	while (!empty(elenco))
	{
		result[i] = head(elenco);
		elenco = tail(elenco);
		i++;
	}
	quickSort(result, *dim);
	return result;
}