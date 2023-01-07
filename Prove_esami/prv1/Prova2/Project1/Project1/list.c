
#define _CRT_SECURE_NO_DEPRECATE

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "list.h"

/* OPERAZIONI PRIMITIVE */
list  emptylist(void)		/* costruttore lista vuota */
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
		printf("%s %s %d %d\n", temp.nome, temp.codice, (temp.numero), (temp.distanza));
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
	if (empty(l))
		return;
	else 
	{
		freelist(tail(l));
		free(l);
	}
	return;
}

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

list copy(list l)
{
	if (empty(l)) 
		return l;
	else 
		return cons(head(l), copy(tail(l)));
}

list leggiAvvistamenti(FILE* fp)
{
	element temp;
	list result = emptylist();
	while (fscanf(fp, "%s %s %d %d", temp.nome, temp.codice, &(temp.numero), &(temp.distanza)) == 4)
		result = cons(temp, result);
	return result;
}


Avvistamento* ordinaAvvistamenti(list a, Specie* uccelli, int dim_u, int* dim)
{
	int i, j;
	Avvistamento* result;
	Avvistamento* true_result;
	float* score;
	list temp;
	temp = copy(a);
	*dim = 0;
	while (!empty(temp))
	{
		*dim = *dim + 1;
		temp = tail(temp);
	}
	result = (Avvistamento*) malloc(*dim * sizeof(Avvistamento));
	true_result = (Avvistamento*)malloc(*dim * sizeof(Avvistamento));
	score = (float*)malloc(*dim * sizeof(float));
	for (i = 0; i < *dim; i++)
	{
		result[i] = head(a);
		score[i] = valore_avvistamento(head(a), uccelli, dim_u);
		a = tail(a);
	}
	bubbleSort(score, *dim);
	for (i = 0; i < *dim; i++)
	{
		for (j = 0; j < *dim; j++)
		{
			if (valore_avvistamento(result[j], uccelli, dim_u) == score[i])
				true_result[i] = result[j];
		}
	}
	return true_result;
}

float punteggioAmico(char* amico, list a, Specie* uccelli, int dim_u)
{
	float score = 0;
	while (!empty(a))
	{
		if (!strcmp(head(a).nome, amico))
			score = score + valore_avvistamento(head(a), uccelli, dim_u);
		a = tail(a);
	}
	return score;
}

int scrivi_migliore(char* nomefile, list a, Specie* uccelli, int dim_u)
{
	float max = 0;
	char name_winner[16];
	FILE* fp = fopen(nomefile, "a");
	if (fp == NULL)
	{
		printf("Errore nell'apertura del file.\n");
		return -1;
	}
	else
	{
		while (!empty(a))
		{
			if (max < punteggioAmico(head(a).nome, a, uccelli, dim_u))
			{
				max = punteggioAmico(head(a).nome, a, uccelli, dim_u);
				strcpy(name_winner, head(a).nome);
			}
			a = tail(a);
		}
		fprintf(fp, "\n%s--%f\n", name_winner, max);
		fclose(fp);
		return 0;
	}
}