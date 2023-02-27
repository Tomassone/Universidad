
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include <stdio.h>
#include "modulo.h"

int main()
{
	char file_path1[100], file_path2[100];
	list event_list;
	int dim = 1;
	tariffa* elenco;
	int i;
	printf("Digita il percorso del file da aprire (eventi): \n");
	scanf("%s", file_path1);
	event_list = leggiTutti(file_path1);
	printf("\nLista di eventi:\n");
	showlist(event_list);
	printf("Digita il percorso del file da aprire (tariffe): \n");
	scanf("%s", file_path2);
	elenco = leggiTariffe(file_path2, &dim);
	for (i = 0; i < dim; i++)
	{
		printString(elenco[i].stazione_partenza);
		printString(elenco[i].stazione_arrivo);
		printf("%0.2f", elenco[i].costo);
	}
	return 0;
}