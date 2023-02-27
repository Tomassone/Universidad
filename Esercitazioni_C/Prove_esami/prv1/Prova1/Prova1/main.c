
#define _CRT_SECURE_NO_DEPRECATE

#include <stdio.h>
#include "modulo.h"
#include "C:\Users\cosot\Desktop\Programmazione\Sorgenti\Universidad\Librerie\Liste\list.h"
#include "C:\Users\cosot\Desktop\Programmazione\Sorgenti\NesBoy-Save-Converter\header_files\array_manipulation.h"
#include "C:\Users\cosot\Desktop\Programmazione\Sorgenti\Universidad\Librerie\Ordinamento\ordinamento.h"

int main()
{
	int i, j, found = 0;
	int dim;
	int dim1 = 0, dim2 = 0;
	char filePath1[300];
	char filePath2[300];
	int* ctrl;
	Operazione* elenco1;
	list elenco2;
	printf("Digita un percorso file: ");
	scanf("%s", filePath1);
	elenco1 = leggiTutteOperazioni(filePath1, &dim);
	stampaOperazioni(elenco1, dim);
	printf("Digita un percorso file: ");
	scanf("%s", filePath2);
	elenco2 = leggiNegozi(filePath2);
	showlist(elenco2);
	printf("\n\n");
	ordina(elenco1, dim);
	stampaOperazioni(elenco1, dim);
	printf("\n");
	elenco1 = filtra(elenco1, dim, elenco2, &dim1);
	stampaOperazioni(elenco1, dim1);
	ctrl = (int*) malloc(sizeof(int) * dim1);
	for (i = 0; i < dim1; i++)
	{
		for (j = 0; j < dim1; j++)
		{
			if (ctrl[j] == elenco1[i].id_unico)
				found = 1;
		}
		if (found != 1)
		{
			printf("Importo totale: %f.\n", spesaCliente(elenco1[i].id_unico, elenco1, dim));
			ctrl[dim2] = elenco1[i].id_unico;
			dim2++;
		}
		found = 0;
	}
	free(elenco1);
	free(ctrl);
	freelist(elenco2);
	return 0;
}