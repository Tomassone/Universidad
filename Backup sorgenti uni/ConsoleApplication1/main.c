
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#define N_VAL 10
#include <stdio.h> //direttiva al compilatore che richiede l'inclusione di una libreria (linker)
#include "min_max.h"

int main()
{
	int i;
	float n, max, min;
	do
	{
		for (i = 0; i < N_VAL; i++)
		{
			printf("Digita un numero: ");
			scanf("%f", &n);
			if (i == 0)
				max = min = n;
			min = calc_min(n, min);
			max = calc_max(n, max);
		}
		printf("Finito ciclo, max = %f, min = %f\n", max, min);
	} 
	while (max - min <= 10);
	return 0;
}