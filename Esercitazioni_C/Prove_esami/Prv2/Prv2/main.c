
#include "element.h"
#include "list.h"

int main()
{
	list l;
	int i, dim, ctrl = 0;
	Ingrediente* elenco;
	Ingrediente temp;
	Ricetta temp1;
	list l1;
	elenco = leggiIngredienti("..\\File\\ingredienti.txt", &dim);
	stampaIngredienti(elenco, dim);
	l = leggiRicette("..\\File\\ricette.txt");
	stampaRicette(l);
	printf("\nEcco il prezzo di Boulevardier: %f.\n", prezzoCocktail("boulevardier", elenco, dim, l));
	printf("\nCocktail preparabili: \n\n");
	l1 = reverse(l);
	while (!empty(l1))
	{
		temp1 = head(l1);
		for (i = 0; i < temp1.num_ingr; i++)
			if (prezzoCocktail_dx(temp1.nome, elenco, dim, l))
				ctrl = ctrl + 1;
		if (ctrl == temp1.num_ingr)
		{
			printf("%s ", temp1.nome);
			printf("\n");
		}
		ctrl = 0;
		l1 = tail(l1);
	}
	//temp = trova(29, elenco, dim);
	printf("\nCocktail non preparabili: \n\n");
	l1 = reverse(l);
	while (!empty(l1))
	{
		temp1 = head(l1);
		for (i = 0; i < temp1.num_ingr; i++)
			if (prezzoCocktail_dx(temp1.nome, elenco, dim, l))
				ctrl = ctrl + 1;
		if (ctrl != temp1.num_ingr)
		{
			printf("%s ", temp1.nome);
			printf("\n");
		}
		ctrl = 0;
		l1 = tail(l1);
	}
	//printf("%d %s %f %02d/%02d/%02d\n", (temp.id), temp.nome, (temp.prezzo), (temp.data_scadenza.anno), (temp.data_scadenza.mese), (temp.data_scadenza.giorno));
	//stampaBarista(l, elenco, dim);
	free(elenco);
	freelist(l);
	freelist(l1);
	return 0;
}