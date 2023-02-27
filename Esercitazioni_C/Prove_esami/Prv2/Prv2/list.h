
#ifndef LIST_H

	#define LIST_H

	#include "element.h"

	typedef Ricetta element;

	typedef struct list_element
	{
		element value;
		struct list_element* next;
	} item;

	typedef item* list;

	typedef int boolean;

	/* PRIMITIVE */
	list emptylist();
	boolean empty(list);
	list cons(element, list);
	element head(list);
	list tail(list);

	/* ALTRE OPERAZIONI NON PRIMITIVE */

	void showlist(list l);
	void freelist(list l);
	int member(element el, list l);
	int length(list l);
	list append(list l1, list l2);
	list reverse(list l);
	list copy(list l);
	list delete(element el, list l);

	list leggiRicette(char* nomefile);

	//list insord_p(element el, list l);
	
	void stampaRicette(list ricette);

	void stampaBarista(list ricette, Ingrediente* ing, int dimI);

	float prezzoCocktail(char* nomeCocktail, Ingrediente* ing, int dimI, list ricette);

	float prezzoCocktail_dx(char* nomeCocktail, Ingrediente* ing, int dimI, list ricette);

#endif