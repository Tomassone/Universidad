
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "list.h"
#include "modulo.h"

void printString(char* string)
{
	int i = 0;
	while (string[i] != '\0')
	{
		printf("%c", string[i]);
		i++;
	}
	printf("\n");
}

/* OPERAZIONI PRIMITIVE */
list  emptylist(void)		/* costruttore lista vuota */
{
	return NULL;
}

boolean  empty(list l)	/* verifica se lista vuota */
{
	return (l == NULL);
}

list  cons(element e, list l)
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
		printf("Id unico: %d\n", temp.id_unico);
		printf("Stazione di partenza: ");
		printString(temp.stazione_partenza);
		printf("Stazione di arrivo: ");
		printString(temp.stazione_arrivo);
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