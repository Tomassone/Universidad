
#include <stdio.h>
#include <stdlib.h>
#include "modulo.h"
#include <string.h>

//es1

/*int main()
{
	int even = 0, odd = 0, dim;
	int* V;
	FILE *fp = fopen("C:\\Users\\s0001070949\\Desktop\\Progetti\\Allocazione_dinamica\\file\\valori.dat", "wb");
	if (fp == NULL)
	{
		printf("Non è stato possibile aprire/creare il file.\n");
		exit(1);
	}
	else
	{
		createFile(fp);
		fclose(fp);
		fp = fopen("C:\\Users\\s0001070949\\Desktop\\Progetti\\Allocazione_dinamica\\file\\valori.dat", "rb");
		if (fp == NULL)
		{
			printf("Non è stato possibile aprire/creare il file.\n");
			exit(1);
		}
		else
		{
			dim = readLength(fp, &even, &odd);
			V = (int*) malloc(sizeof(int) * dim);
			rewind(fp);
			makeArray(fp, V, even, odd, dim);
		}
		free(V);
		fclose(fp);
	}
	return 0;
}*/

//es3

/*int main()
{
	int i, media_gg = 0, n = 0, dim_f;
	char cliente_selezionato[DIM];
	FILE *f = fopen("C:\\Users\\s0001070949\\Desktop\\Progetti\\Allocazione_dinamica\\file\\RentedLog.txt", "r");
	Rent* V = NULL;
	V = makeVet(V, f, &dim_f);
	printf("Digita il nome di un cliente: ");
	scanf("%s", cliente_selezionato);
	for (i = 0; i < dim_f; i++)
	{
		if (!strcmp(cliente_selezionato, (V[i]).renter))
		{
			printf("%d %s %d\n", (V[i]).cd_code, (V[i]).renter, (V[i]).days);
			media_gg = media_gg + (V[i]).days;
			n++;
		}
	}
	free(V);
	media_gg = media_gg / n;
	printf("La media dei giorni e': %d", media_gg);
	return 0;
}*/

//es 4

int main()
{
	FILE* venduti = fopen("/Users/Cesare/Documents/GitHub/Universidad/Backup_sorgenti_uni/Allocazione_dinamica/file/venduti.txt", "r");
	FILE* listino = fopen("/Users/Cesare/Documents/GitHub/Universidad/Backup_sorgenti_uni/Allocazione_dinamica/file/listino.txt", "r");
	Item* V;
	int i, n_elem = 0; 
	char tip_user[20];
	if (venduti != NULL && listino != NULL)
	{
		printf("Quale tipologia vuoi controllare?\n");
		scanf("%s", tip_user);
		V = articoli(listino, venduti, tip_user, &n_elem);
		for (i = 0; i < n_elem; i++)
			printf("%s %s %f %d\n", (V[i].tipologia), (V[i].marca), (V[i].prezzo), V[i].n_venduti);
	}
	else
	{
		exit(1);
		printf("C'e' stato un problema nell'apertura del file.\n");
	}
	return 0;
}
