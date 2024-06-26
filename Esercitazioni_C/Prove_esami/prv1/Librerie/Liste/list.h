
#include "C:\Users\cosot\Desktop\Programmazione\Sorgenti\Universidad\Prove_esami\prv1\Prova1\Prova1\modulo.h"

#ifndef LIST_H

#define LIST_H

	typedef Negozio element;
	#include <string.h>

	typedef struct list_element
	{
		element value;
		struct list_element* next;
	} item;

	typedef item* list;

	typedef int boolean;

	/* PRIMITIVE  */
	list emptylist(void);
	boolean empty(list);
	list cons(element, list);
	element head(list);
	list tail(list);

	void showlist(list l);
	void freelist(list l);
	int member(element el, list l);

	//list insord_p(element el, list l);

	list leggiNegozi(char* fileName);

	int negozioFisico(char* nomeNegozio, list negozi);

	Operazione* filtra(Operazione* v, int dim, list negozi, int* dimLog);

#endif