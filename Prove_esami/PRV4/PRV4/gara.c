
#define _CRT_SECURE_NO_DEPRECATE

#include "gara.h"

Atleta* ordinaAtletiPer(Atleta* a, int dimA, char* tipo, int* dim)
{
	int i;
	Atleta* result;
	*dim = 0;
	result = (Atleta*) malloc(sizeof(Atleta) * dimA);
	for (i = 0; i < dimA; i++)
	{
		if (a[i].gare_completate == 3)
		{
			result[*dim] = a[i];
			*dim = *dim + 1;
		}

	}
	//for (i = 0; i < *dim; i++)
	//	stampaAtleta(result[i]);
	//printf("\n\n\n");
	bubbleSort(result, *dim, tipo);
	return result;
}

int ctrl_no_doppioni(list l, element e)
{
	if (empty(l) == 1)
		return 0;
	else
	{
		if (!strcmp(head(l).codiceAtleta, e.codiceAtleta))
			return 1;
		else
			return 0 + ctrl_no_doppioni(tail(l), e);
	}
}

list atletiPremiati(Atleta* a, int dimA)
{
	int i, dim;
	list l = emptylist();
	Atleta* elenco = ordinaAtletiPer(a, dimA, "Nuoto", &dim);
	for (i = 0; i < 3; i++)
	{
		l = cons(elenco[i], l);
	}
	elenco = ordinaAtletiPer(a, dimA, "Bici", &dim);
	for (i = 0; i < 3; i++)
	{
		if (!ctrl_no_doppioni(l, elenco[i]))
			l = cons(elenco[i], l);
	}
	elenco = ordinaAtletiPer(a, dimA, "Corsa", &dim);
	for (i = 0; i < 3; i++)
	{
		if (!ctrl_no_doppioni(l, elenco[i]))
			l = cons(elenco[i], l);
	}
	elenco = ordinaAtletiPer(a, dimA, "Totale", &dim);
	for (i = 0; i < 5; i++)
	{
		if (!ctrl_no_doppioni(l, elenco[i]))
			l = cons(elenco[i], l);
	}
	return l;
}

void premi(Atleta* a, int dimA)
{
	int i, dim;
	list l = reverse(atletiPremiati(a, dimA));
	float vincita = 0;
	Atleta* elenco;
	while (!empty(l))
	{
		elenco = ordinaAtletiPer(a, dimA, "Nuoto", &dim);
		vincita = 0;
		for (i = 0; i < 3; i++)
		{
			//printf("%d\n", !strcmp(elenco[i].codiceAtleta, head(l).codiceAtleta));
			if (!strcmp(elenco[i].codiceAtleta, head(l).codiceAtleta))
			{
				switch (i)
				{
					case 0:
						vincita = vincita + 300;
						break;
					case 1:
						vincita = vincita + 200;
						break;
					case 2:
						vincita = vincita + 100;
						break;
				}
			}
		}
		//stampaAtleta(head(l));
		//printf("soldi guadagnati: %f.\n", vincita);
		elenco = ordinaAtletiPer(a, dimA, "Bici", &dim);
		for (i = 0; i < 3; i++)
		{
			//printf("%d\n", !strcmp(elenco[i].codiceAtleta, head(l).codiceAtleta));
			if (!strcmp(elenco[i].codiceAtleta, head(l).codiceAtleta))
			{
				switch (i)
				{
					case 0:
						vincita = vincita + 300;
						break;
					case 1:
						vincita = vincita + 200;
						break;
					case 2:
						vincita = vincita + 100;
						break;
				}
			}
		}
		//stampaAtleta(head(l));
		//printf("soldi guadagnati: %f.\n", vincita);
		elenco = ordinaAtletiPer(a, dimA, "Corsa", &dim);
		for (i = 0; i < 3; i++)
		{
			//printf("%d\n", !strcmp(elenco[i].codiceAtleta, head(l).codiceAtleta));
			if (!strcmp(elenco[i].codiceAtleta, head(l).codiceAtleta))
			{
				switch (i)
				{
					case 0:
						vincita = vincita + 300;
						break;
					case 1:
						vincita = vincita + 200;
						break;
					case 2:
						vincita = vincita + 100;
						break;
				}
			}
		}
		//stampaAtleta(head(l));
		//printf("soldi guadagnati: %f.\n", vincita);
		elenco = ordinaAtletiPer(a, dimA, "Totale", &dim);
		for (i = 0; i < 5; i++)
		{
			//printf("%d\n", !strcmp(elenco[i].codiceAtleta, head(l).codiceAtleta));
			if (!strcmp(elenco[i].codiceAtleta, head(l).codiceAtleta))
			{
				switch (i)
				{
					case 0:
						vincita = vincita + 500;
						break;
					case 1:
						vincita = vincita + 400;
						break;
					case 2:
						vincita = vincita + 300;
						break;
					case 3:
						vincita = vincita + 200;
						break;
					case 4:
						vincita = vincita + 100;
						break;
				}
			}
		}
		stampaAtleta(head(l));
		printf("soldi guadagnati: %f.\n", vincita);
		l = tail(l);
	}
	freelist(l);
}