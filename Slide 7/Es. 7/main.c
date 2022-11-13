
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include <stdio.h>
#include "modulo.c"

int main()
{
	float a, b, N;
	float intgr;
	printf("Digita un a: ");
	scanf("%f", &a);
	printf("Digita un b: ");		
	scanf("%f", &b);
	printf("Digita un N: ");
	scanf("%f", &N);
	intgr = integrale_it(a, b, N);
	printf("Il valore iterativo e' %f.\n", intgr);
	intgr = integrale_ric(a, b, N);
	printf("Il valore ricorsivo e' %f.\n", intgr);
	return 0;
}
