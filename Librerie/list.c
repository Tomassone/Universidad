
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
	if (empty(l)) 
		exit(-2);
	else 
		return (l->value);
}

list tail(list l)         /* selettore coda lista */
{
	if (empty(l)) 
		exit(-1);
	else 
		return (l->next);
}

void showlist(list l)
{
	element temp;
	if (!empty(l))
	{
		temp = head(l);
		showlist(tail(l));
		printElement(head(l));
		return;
	}
	else
	{
		printf("\n\n");
		return;
	}
}

int member(element el, list l)
{
	int result = 0;
	while (!empty(l) && !result)
	{
		result = (isEqual(el, head(l)));
		if (!result)
			l = tail(l);
	}
	return result;
}

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

int length(list l)
{
	if (empty(l)) 
		return 0;
	else 
		return 1 + length(tail(l));
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
	if (empty(l)) 
		return l;
	else 
		return cons(head(l), copy(tail(l)));
}

list delete(element el, list l)
{
	if (empty(l)) 
		return emptylist();
	else if (isEqual(el, head(l)))
		return tail(l);
	else 
		return cons(head(l), delete(el, tail(l)));
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