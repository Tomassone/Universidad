
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
	t = (list)malloc(sizeof(item));
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

void showlist(list l) {
	element temp;
	if (!empty(l))
	{
		temp = head(l);
		showlist(tail(l));
		//PRINT
		printf("%s %c\n", temp.nome_negozio, temp.tipo);
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

void freelist(list l) {
	if (empty(l))
		return;
	else {
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

list leggiNegozi(char* fileName)
{
	int dim = 0;
	Negozio temp;
	list result;
	FILE* fp = fopen(fileName, "r");
	if (fp != NULL)
	{
		result = emptylist();
		while (fscanf(fp, "%s %c", temp.nome_negozio, &(temp.tipo)) == 2)
			result = cons(temp, result);
		fclose(fp);
		return result;
	}
	else
	{
		printf("Errore di lettura!\n");
		return NULL;
	}
}

int negozioFisico(char* nomeNegozio, list negozi)
{
	int trovato = 0, fisico = 0;
	Negozio temp;
	while (!empty(negozi))
	{
		temp = head(negozi);
		if (!strcmp(temp.nome_negozio, nomeNegozio) && temp.tipo == 'F')
			trovato = 1;
		negozi = tail(negozi);
	}
	return trovato;
}

Operazione* filtra(Operazione* v, int dim, list negozi, int* dimLog)
{
	int i, temp;
	Operazione* result;
	*dimLog = 0;
	result = (Operazione*) malloc(sizeof(Operazione) * dim);
	for (i = 0; i < dim; i++)
	{
		if (negozioFisico(v[i].nome_negozio, negozi) == 1)
		{
			//printf("dentro %s", v[i].nome_negozio);
			result[*dimLog] = v[i];
			*dimLog = *dimLog + 1;
		}
	}
	return result;
}