
#include "modulo.h"
#include "list.h"
#include <stdio.h>
#include "stdlib.h"
#include <string.h>

//es1

/*int main()
{
	int i; 
	element temp = 0, dim = 0, soglia;
	list lista1;
	list list_inf, list_sup;
	FILE *fp = fopen("C:\\Users\\s0001070949\\Desktop\\Progetti\\Liste\\File\\result.txt", "r");
	if (fp != NULL)
	{
		while (fscanf(fp, "%d ", &temp) > 0)
			dim++;
		rewind(fp);
		lista1 = emptylist();
		for (i = 0; i < dim; i++)
		{
			fscanf(fp, "%d", &temp);
			//printf("%d ", temp);
			lista1 = cons(temp, lista1);
		}
		showlist(lista1);
		printf("Digitare un valore di soglia: ");
		scanf("%d", &soglia);
		list_sup = emptylist();
		list_inf = emptylist();
		for (i = 0; i < dim; i++)
		{
			temp = head(lista1);
			if (temp >= soglia)
				list_sup = cons(temp, list_sup);
			else
				list_inf = cons(temp, list_inf);
			lista1 = tail(lista1);
		}
		printf("\nEcco la lista superiore: \n");
		showlist(list_sup);
		printf("Ecco la lista inferiore: \n");
		showlist(list_inf);
		freelist(lista1);
		freelist(list_sup);
		freelist(list_inf);
		fclose(fp);
	}
	else
	{
		printf("Lettura fallita!\n");
		exit(1);
	}
	return 0;
}*/

//es3

/*#define DIM1 3
#define DIM2 6


int main()
{
	int i;
	element temp;
	list lista1;
	list lista2;
	list lista_def;
	lista1 = emptylist();
	lista2 = emptylist();
	for (i = 0; i < DIM1; i++)
	{
		printf("Digita un elemento della prima lista: ");
		scanf("%d", &temp);
		lista1 = cons(temp, lista1);
	}
	for (i = 0; i < DIM2; i++)
	{
		printf("Digita un elemento della seconda lista: ");
		scanf("%d", &temp);
		lista2 = cons(temp, lista2);
	}
	printf("Prima lista: \n");
	showlist(lista1);
	printf("Seconda lista: \n");
	showlist(lista2);
	printf("Lista definitiva: ");
	lista_def = crossSelection(lista1, lista2);
	showlist(lista_def);
	freelist(lista1);
	freelist(lista2);
	freelist(lista_def);
	return 0;
}*/

//es5 

#define DIM 80

int main()
{
	float importo_tot;
	char text_path[DIM], binary_path[DIM], client_name[DIM];
	FILE* fp; 
	list temp;
	char nome_cl[DIM];
	build_binary();
	printf("Digita il percorso del file di testo: ");
	scanf("%s%*c", text_path);
	printf("Digita il nome del cliente: ");
	scanf("%s%*c", client_name);
	fp = fopen(text_path, "r");
	if (fp != NULL)
	{
		while(fscanf(fp, "%s %s\n", nome_cl, binary_path) != EOF)
		{
			importo_tot = 0;
			printf("Nome cliente: %s\n", nome_cl);
			//printf("%s\n", binary_path);
			temp = findBills(binary_path, nome_cl);
			printf("\nElenco spese parziali: \n");
			showlist(temp);
			while(!empty(temp))
			{
				importo_tot = importo_tot + head(temp);
				temp = tail(temp);
			}
			printf("Importo totale: %f\n\n", importo_tot);
		}
		fclose(fp);
		freelist(temp);
	}
	else
	{
		printf("Errore di apertura (1)!");
		exit(1);
	}
	return 0;
}
