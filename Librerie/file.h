
#ifndef FILE_H

	#define FILE_H
	
	#include <stdio.h>
	#include <stdlib.h>
	#include "element.h"
	#include "list.h"

	//restituisce un array dinamico a partire da i contenuti di un file di testo aperto.
	element* array_from_file(char nomefile[], int* dim);

	//restituisce una lista a partire da i contenuti di un file di testo aperto.
	list list_from_file(FILE* fp);

	//restituisce un elemento letto da un file di testo aperto.
	element element_from_file(FILE* fp);

	//restituisce un array dinamico a partire da i contenuti di un file di testo aperto (usa la element_from_file).
	element* array_from_file_dx(char fileName[], int* dim);

	//restituisce un array dinamico a partire da i contenuti di un file binario aperto.
	element* array_from_binfile(FILE* f, element* V, int* dim);

	//restituisce una lista a partire da i contenuti di un file binario aperto.
	list list_from_binfile(FILE* fp);

#endif