
#define _CRT_SECURE_NO_DEPRECATE

#include "array_manipulation.h" //inclusione del corrispondente file header.

void print_array(int* array, int dim)
{
	int i;
	for (i = 0; i < dim; i++)
		printf("%d", array[i]);
	printf("\n");
}

void copy_array(int* destination, int* source, int dim)
{
	int i;
	for (i = 0; i < dim; i++)
		destination[i] = source[i];  
}