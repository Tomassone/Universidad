
#define _CRT_SECURE_NO_DEPRECATE

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "list.h"

/* OPERAZIONI PRIMITIVE */
list emptylist(void)		/* costruttore lista vuota */
{
	return NULL;
}

boolean empty(list l)	/* verifica se lista vuota */
{
	return (l == NULL);
}

list cons(element e, list l)
{
	list t;       /* costruttore che aggiunge in testa alla lista */
	t = (list) malloc(sizeof(item));
	t->value = e;
	t->next = l;
	return(t);
}


element head(list l) /* selettore testa lista */
{
	if (empty(l)) exit(-2);
	else return (l->value);
}

list  tail(list l)         /* selettore coda lista */
{
	if (empty(l)) exit(-1);
	else return (l->next);
}

void showlist(list l)
{
	element temp;
	if (!empty(l))
	{
		temp = head(l);
		showlist(tail(l));
		//PRINT
		return;
	}
	else
	{
		printf("\n\n");
		return;
	}
}

/*
int member(element el, list l) {
	int result = 0;
	while (!empty(l) && !result) {
		result = (el.id == head(l).id);
		if (!result)
			l = tail(l);
	}
	return result;
}
*/

void freelist(list l) 
{
	//if (empty(l))
	//	return;
	//else 
	//{
	//	freelist(tail(l));
	//	free(l);
	//return;
}

int length(list l)
{
	//if (empty(l)) 
	//	return 0;
	//else 
	//	return 1 + length(tail(l));
}

list append(list l1, list l2)
{
	if (empty(l1)) 
		return l2;
	else
		return cons(head(l1), append(tail(l1), l2));
}

list reverse(list l)
{
	if (empty(l))
		return emptylist();
	else 
		return append(reverse(tail(l)), cons(head(l), emptylist()));
}

list copy(list l)
{
	//if (empty(l)) 
	//	return l;
	//else 
	//	return cons(head(l), copy(tail(l)));
}

/*list delete(element el, list l)
{
	if (empty(l)) 
		return emptyList();
	else if (el == head(l)) 
		return tail(l);
	else 
		return cons(head(l), delete(el, tail(l)));
}*/

//
//list insord_p(element el, list l) {
//	list pprec = NULL, patt = l, paux;
//	int trovato = 0;
//
//	while (patt != NULL && !trovato) {
//-		if (el.costo > patt->value.costo)
//			trovato = 1;
//		else {
//			pprec = patt;
//			patt = patt->next;
//		}
//	}
//	paux = (list)malloc(sizeof(item));
//	paux->value = el;
//	paux->next = patt;
//	if (patt == l)
//		return paux;
//	else {
//		pprec->next = paux;
//		return l;
//	}
//}

list leggiRicette(char* nomefile)
{
	int i = 0;
	FILE* fp = fopen(nomefile, "r");
	list l = emptylist();
	int* elenco;
	element temp;
	temp.num_ingr = 0;
	temp.nome[0] = '\0';
	if (fp == NULL)
		printf("Errore di lettura!\n");
	else
	{
		while (fscanf(fp, "%s %d", temp.nome, &(temp.num_ingr)) == 2)
		{
			elenco = (int*) malloc(temp.num_ingr * sizeof(int));
			for (i = 0; i < temp.num_ingr; i++)
				fscanf(fp, "%d", &(elenco[i]));
			temp.elenco_id_ingr = elenco;
			l = cons(temp, l);
		}
		fclose(fp);
	}
	return l;
}

void stampaRicette(list ricette)
{
	int i;
	if (!empty(ricette))
	{
		stampaRicette(tail(ricette));
		printf("%s %d ", head(ricette).nome, head(ricette).num_ingr);
		for (i = 0; i < head(ricette).num_ingr; i++)
			printf("%d ", (head(ricette).elenco_id_ingr[i]));
		printf("\n");
	}
	else
		printf("\n");
}

void stampaBarista(list ricette, Ingrediente* ing, int dimI)
{
	int i;
	element temp;
	Ingrediente corrispondenza;
	list l = reverse(ricette);
	while (!empty(l))
	{
		temp = head(l);
		printf("%s %d ", (temp).nome, (temp).num_ingr);
		for (i = 0; i < (temp).num_ingr; i++)
		{
			corrispondenza = trova(temp.elenco_id_ingr[i], ing, dimI);
			printf("%s ", corrispondenza.nome);
		}
		printf("\n");
		l = tail(l);
	}
}

float prezzoCocktail(char* nomeCocktail, Ingrediente* ing, int dimI, list ricette)
{
	int i, trovato = 0;
	float prezzo = 0;
	Ingrediente temp;
	while (!empty(ricette) && trovato == 0)
	{
		if (!strcmp(head(ricette).nome, nomeCocktail))
		{
			trovato = 1;
			for (i = 0; i < head(ricette).num_ingr; i++)
			{
				temp = trova(head(ricette).elenco_id_ingr[i], ing, dimI);
				prezzo = prezzo + temp.prezzo;
				if (!strcmp(temp.nome, "sconosciuto"))
				{
					printf("Un ingrediente richiesto non era nella lista.\n");
					return 0;
				}
			} 
			if (head(ricette).num_ingr > 3 && head(ricette).num_ingr < 5)
				prezzo = prezzo * 0.8;
			if (head(ricette).num_ingr > 5)
				prezzo = prezzo * 0.6;
			return prezzo;
		}
		ricette = tail(ricette);
	}
	if (trovato == 0)
	{
		printf("Il cocktail richiesto non era nella lista.\n");
		return 0;
	}
}

float prezzoCocktail_dx(char* nomeCocktail, Ingrediente* ing, int dimI, list ricette)
{
	int i, trovato = 0;
	float prezzo = 0;
	Ingrediente temp;
	while (!empty(ricette) && trovato == 0)
	{
		if (!strcmp(head(ricette).nome, nomeCocktail))
		{
			trovato = 1;
			for (i = 0; i < head(ricette).num_ingr; i++)
			{
				temp = trova(head(ricette).elenco_id_ingr[i], ing, dimI);
				prezzo = prezzo + temp.prezzo;
				if (!strcmp(temp.nome, "sconosciuto"))
				{
					//printf("Un ingrediente richiesto non era nella lista.\n");
					return 0;
				}
			}
			if (head(ricette).num_ingr > 3 && head(ricette).num_ingr < 5)
				prezzo = prezzo * 0.8;
			if (head(ricette).num_ingr > 5)
				prezzo = prezzo * 0.6;
			return prezzo;
		}
		ricette = tail(ricette);
	}
	if (trovato == 0)
	{
		//printf("Il cocktail richiesto non era nella lista.\n");
		return 0;
	}
}