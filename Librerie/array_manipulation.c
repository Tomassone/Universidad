
#define _CRT_SECURE_NO_DEPRECATE

#include "array_manipulation.h" //inclusione del corrispondente file header.

void empty_array(element* array, int dim)
{
	int i;
	element	temp;
	for (i = 0; i < dim; i++)
		assignElement(&(array[i]), &(temp));
	printf("\n");
}

void read_array(element* array, int dim)
{
	int i;	
	element	temp;
	for (i = 0; i < dim; i++)
	{
		printf("Digita un elemento dell'array: ");
		temp = getElement();
		assignElement(&(array[i]), &(temp));
	}
	printf("\n");
}

void print_array(element* array, int dim)
{
	int i;
	for (i = 0; i < dim; i++)
		printElement(array[i]);
	printf("\n");
}

void print_array_r(element* array, int dim)
{
	element temp;
	if (dim > 0)
	{
		print_array_r(array, dim - 1);
		temp = array[dim - 1];
		printElement(*(array + dim - 1));
	}
	else
		printf("\n");
}

void copy_array(element* destination, element* source, int dim)
{
	int i;
	for (i = 0; i < dim; i++)
		assignElement(&(destination[i]), &(source[i]));
}

int array_member(element el, element* array, int dim)
{
	int i, result = 0;
	for (i = 0; i < dim && result == 0; i++)
		result = isEqual(el, array[i]);
	return result;
}