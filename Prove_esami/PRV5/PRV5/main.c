
#define _CRT_SECURE_NO_DEPRECATE

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "element.h"
#include "modulo.h"
#include "list.h"
#include "file.h"

int main()
{
	int dim, i;
	int dim1;
	list l = emptylist();
	printf("prova");
	element* elenco = array_from_file("Files\\testo.txt", &dim);
	free(elenco);
	freelist(l);
	return 0;
}