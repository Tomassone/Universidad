
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include <stdio.h>
#include "modulo.h"


// es 9 - slide 7

/*int main()
{
	int n, max, min;
	printf("Prima sequenza:\n");
	printf("Digita un valore: ");
	scanf("%d", &n);
	max = calc_max(n);
	printf("Il massimo e' %d.\n\n", max);
	printf("Seconda sequenza:\n");
	printf("Digita un valore: ");
	scanf("%d", &n);
	min = calc_min(n);
	printf("Il minimo e' %d.\n\n", min);
	printf("Terza sequenza:\n");
	calc_maxmin(&max, &min);
	printf("Il massimo e' %d e il minimo e' %d.\n\n", max, min);
	return 0;
}*/

// es11 - slide 7

/*int main()
{
	int a, b, c, ctrl;
	float x1, x2;
	printf("Digita il coeff. intero a: ");
	scanf("%d", &a);
	printf("Digita il coeff. intero b: ");
	scanf("%d", &b);
	printf("Digita l'intero c: ");
	scanf("%d", &c);
	ctrl = secondoGrado(a, b, c, &x1, &x2);
	if (ctrl == VALID)
		printf("Le radici del polinomio sono %f e %f.\n", x1, x2);
	else
		printf("I valori digitati non sono validi.");
	return 0;
}*/

// es1 - vettori

/*#define DIM 10

int main()
{
	int i = 0, num, V[DIM], size = 0;
	do
	{
		printf("Digita un numero intero: ");
		scanf("%d", &num);
		if (num != 0 && size < DIM)
		{
			V[size] = num;
			size++;
		}
		i++;
	} 
	while (i < DIM && num != 0);
	for (i = 0; i < size - 1; i++)
	{
		if (V[i] == V[i + 1])
				printf("%d ", V[i]);
	}
	return 0;
}*/

// es2 - vettori

/*#define DIM 10

int main()
{
	int i = 0, num, size = 0;
	int V[DIM], W[DIM];
	do
	{
		printf("Digita un numero intero: ");
		scanf("%d", &num);
		if (num > 0 && size < DIM)
		{
			V[size] = num;
			size++;
		}
		i++;
	} 
	while (i < DIM && num != 0);
	for (i = 0; i < size; i++)
	if (V[i] == i)
		printf("%d ", V[i]);
	return 0;
}*/

// es3 - vettori

/*#define DIM 10

int main()
{
	int i = 0, num, size = 0;
	int V[DIM], W[DIM];
	do
	{
		printf("Digita un numero intero: ");
		scanf("%d", &num);
		if (num > 0 && size < DIM)
		{
			V[size] = num;
			size++;
		}
		i++;
	}
	while (i < DIM && num != 0);
	for (i = size - 1; i >= 0; i--)
	{
		W[size - i] = V[i];
		printf("%d ", W[size - i]);
	}
	return 0;
}*/

//es 5

/*#define DIM 10

int main()
{
	int i = 0, j = 0;
	float v, num, V[DIM], MED[DIM];
	printf("Digita un valore di soglia: ");
	scanf("%f", &v);
	do
	{
		printf("Digita un numero reale della sequenza: ");
		scanf("%f", &num);
		if (num > v && num > 0)
		{
			V[j] = num;
			j++;
		}
		i++;
	} 
	while (i < DIM && j < DIM && num != 0);
	for (i = 0; i < j; i++)
	{
		MED[i] = (V[i] + v) / 2;
		printf("%f ", MED[i]);
	}
	return 0;
}*/

//es per casa: 4, 8, 9

//es 6

/*#define DIM 10

int main()
{
	int i = 0, j = 0;
	float n, NUM[DIM], MEDIE[DIM];
	do
	{
		printf("Digita un numero reale della sequenza: ");
		scanf("%f", &n);
		if (n > 0)
		{
			NUM[j] = n;
			j++;
		}
		i++;
	}
	while (i < DIM && j < DIM && n != 0);
	for (i = 0; i < j - 1; i++)
	{
		MEDIE[i] = (NUM[i] + NUM[i + 1]) / 2;
		printf("%f ", MEDIE[i]);
	}
	return 0;
}*/

//es 7

/*#define N 3

int main()
{
	int i, j, k, v1[N], v2[N], v3[2 * N];
	printf("Primo vettore: \n");
	for(i = 0; i < N; i++)
	{ 
		printf("Digita un valore: \n");
		scanf("%d", &v1[i]);
	}
	printf("Secondo vettore: \n");
	for (i = 0; i < N; i++)
	{
		printf("Digita un valore: \n");
		scanf("%d", &v2[i]);
	}
	j = 0;
	k = 0;
	for (i = 0; i < 2 * N; i++)
	{
		if (i % 2 == 0)
		{
			v3[i] = v1[j];
			j++;
		}
		else
		{
			v3[i] = v2[k];
			k++;
		}
		printf("%d ", v3[i]);
	}
	return 0;
}*/

//es 10

/*#define DIM 5

int main()
{
	int i = 0, j = 0, media = 0, loc[DIM], man_nev[DIM];
	do
	{
		printf("Digita il numero relativo ad una localita': ");
		scanf("%d", &loc[i]);
		if (loc[i] != 0)
		{
			printf("Digita il numero relativo ad una localita': ");
			scanf("%d", &man_nev[i]);
			media = media + man_nev[i];
			i++;
		}
	} 
	while (loc[i] != 0 && i < DIM);
	media = media / i;
	for (j = 0; j < i; j++)
	{
		if (man_nev[j] > media)
			printf("Valore superiore alla media: (localita') %d (manto nevoso) %d\n", loc[j], man_nev[j]);
	}
	return 0;
}*/

