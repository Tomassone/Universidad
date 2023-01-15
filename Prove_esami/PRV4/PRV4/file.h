
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

#endif