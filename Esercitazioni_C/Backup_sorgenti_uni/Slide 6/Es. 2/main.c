
#include <stdio.h>
#include "modulo.c"

//es 2

/*int main()
{
	int a, ris, i = 0;
	do
	{
		printf("Digita un numero intero: ");
		scanf("%d", &a);
		if (i == 0)
		{
			printf("Digita un numero intero: ");
			scanf("%d", &ris);
		}
		ris = mcm(a, ris);
		printf("Il minimo comune multiplo corrente e': %d.\n", ris);
		i++;
	}
	while (ris <= 100);
	return 0;
}*/

//es 3

int main()
{
	int n, i, j;
	printf("Digita il livello massimo desiderato del triangolo di Tartaglia: ");
	scanf("%d", &n);
	for(i = 0; i < n; i++)
	{
		for(j = 0; j <= i; j++)
			printf("%d ", coeff_bin(i, j));
		printf("\n");
	}
	return 0;
}
