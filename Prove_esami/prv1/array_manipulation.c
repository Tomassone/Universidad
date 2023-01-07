
#include "C:\Users\cosot\Desktop\Programmazione\Sorgenti\NesBoy-Save-Converter\header_files\array_manipulation.h" //inclusione del corrispondente file header.

void print_array(Operazione* array, int dim)
{
	int i;
	for (i = 0; i < dim; i++)
		printf("%d %f %s\n", array[i].id_unico, array[i].importo, array[i].nome_negozio);
	printf("\n");
}

void copy_array(Operazione* destination, Operazione* source, int dim)
{
	int i;
	for (i = 0; i < dim; i++)
		destination[i] = source[i];  
}