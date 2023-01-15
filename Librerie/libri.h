
#ifndef LIBRI_H

	#define LIBRI_H

	#include "element.h"
    #include "string_manipulation.h"

	#include "ordinamento.h"
	#include <stdio.h>
	#include <stdlib.h>
	#include <string.h>
	#include "list.h"

	list leggi(char fileName[], char select, char nome[]);

	void stampaOrdini(list elenco);

	int contaCopieOrdinate(list elenco, char* titolo);

	void aggregaPerTitolo(list elenco);

	Ordine* prepara(list elenco, int* dim);

#endif 