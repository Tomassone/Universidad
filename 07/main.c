
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include <stdio.h>
#include "modulo.c"

//es 1

/*int main()
{
      float re, im, r, phi;
      printf("Digita la parte reale di un numero complesso: ");
      scanf("%f", &re);
      printf("Digita la parte immaginaria di un numero complesso: ");
      scanf("%f", &im);
      converti_complex(re, im, &r, &phi);
      return 0;
}*/

//es 3

/*int main()
{
      int t_mill, t_sec, t_min;
      printf("Digita un tempo espresso in millisecondi: ");
      scanf("%d", &t_mill);
      millToDef(&t_mill, &t_sec, &t_min);
      printf("Il tempo effettivo e' %d:%d:%d.\n", t_min, t_sec, t_mill);
      return 0;
}*/

//es 5

/*int main()
{
      int l1, l2, b;
      int checkVal;
      float area, per;
      printf("Digita il valore del lato: ");
      scanf("%d", &l1);
      printf("Digita il valore del lato: ");
      scanf("%d", &l2);
      printf("Digita il valore della base: ");
      scanf("%d", &b);
      checkVal = calc_tr(l1, l2, b, &area, &per);
      if (checkVal == SUCCESS)
            printf("L'area del triangolo e' %f, mentre il suo perimetro e' %f.\n", area, per);
      else if (checkVal == NON_VALID_TR)
            printf("Il triangolo considerato non risulta valido.\n");
      else
            printf("Il triangolo considerato risulta degenere.\n");
      return 0;
}*/

//es 6


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
	intgr = rettangoli_it(a, b, N);
	printf("Il valore e' %f.\n", intgr);
	return 0;
}
