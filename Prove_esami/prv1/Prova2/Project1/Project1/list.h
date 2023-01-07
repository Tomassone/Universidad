
#ifndef LIST_H

	#define LIST_H

	#include "element.h"
    #include "ordinamento.h"
	#include <stdlib.h>

	typedef Avvistamento element;

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

	list copy(list l);
	
	list leggiAvvistamenti(FILE* fp);

	Avvistamento* ordinaAvvistamenti(list a, Specie* uccelli, int dim_u, int* dim);

	float punteggioAmico(char* amico, list a, Specie* uccelli, int dim_u);

	float punteggioAmico(char* amico, list a, Specie* uccelli, int dim_u);

	int scrivi_migliore(char* nomefile, list a, Specie* uccelli, int dim_u);

#endif