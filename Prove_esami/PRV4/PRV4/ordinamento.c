
#define _CRT_SECURE_NO_DEPRECATE

#include "ordinamento.h"

void scambia(element* a, element* b)
{
	element tmp;
	tmp = *a;
	*a = *b;
	*b = tmp;
}

//

void naiveSort(element v[], int n)
{

}

int trovaPosMax(element v[], int n)
{
	
}

//

void bubbleSort(element* v, int n, char* tipo)
{
	int i, ordinato = 0;
	while (n > 1 && !ordinato)
	{
		ordinato = 1;
		for (i = 0; i < n - 1; i++)
		{
			if (compare((v[i]), (v[i + 1]), tipo) == 1)
			{
				scambia(&(v[i]), &(v[i + 1]));
				ordinato = 0;
			}
		}
		n--;
	}
}


//

void insOrd(element v[], int pos)
{
	
}

void insertSort(element v[], int n)
{
}

//

void mergeSort(element v[], int first, int last, element vout[])
{
	
}

void merge(element v[], int i1, int i2, int fine, element vout[])
{
	
}

//

void quickSort(element a[], int dim)
{
	
}

void quickSortR(element a[], int iniz, int fine)
{
	
	 /* (iniz < fine) */
} /* quickSortR */

//
