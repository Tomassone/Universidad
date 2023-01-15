
#define _CRT_SECURE_NO_DEPRECATE

#include "ordinamento.h"

void scambia(element* a, element* b)
{
	element tmp;
	assignElement(&tmp, a);
	assignElement(a, b);
	assignElement(b, &tmp);
}

//

void naiveSort(element v[], int n)
{
	int p;
	while (n > 1) 
	{
		p = trovaPosMax(v, n);
		if (p < n - 1)
			scambia(&v[p], &(v[n - 1]));
		n--;
	}
}

int trovaPosMax(element v[], int n)
{
	int i, posMax = 0;
	for (i = 1; i < n; i++)
		if (isLess(v[posMax], v[i]))
			posMax = i;
	return posMax;
}

//

void bubbleSort(element v[], int n)
{
	int i, ordinato = 0;
	while (n > 1 && !ordinato)
	{
		ordinato = 1;
		for (i = 0; i < n - 1; i++)
			if (isLess(v[i + 1], v[i]))
			{
				scambia(&(v[i]), &(v[i + 1]));
				ordinato = 0;
			}
		n--;
	}
}

//

void insOrd(element v[], int pos)
{
	int i = pos - 1;
	element x = v[pos];
	while (i >= 0 && isLess(x, v[i])) 
	{
		v[i + 1] = v[i]; /* crea lo spazio */
		i--;
	}
	v[i + 1] = x; /* inserisce l’elemento */
}

void insertSort(element v[], int n)
{
	int k;
	for (k = 1; k < n; k++)
		insOrd(v, k);
}

//

void mergeSort(element v[], int first, int last, element vout[])
{
	int mid;
	if (first < last) 
	{
		mid = (last + first) / 2;
		mergeSort(v, first, mid, vout);
		mergeSort(v, mid + 1, last, vout);
		merge(v, first, mid + 1, last, vout);
	}
}

void merge(element v[], int i1, int i2, int fine, element vout[])
{
	int i = i1, j = i2, k = i1;
	while (i <= i2 - 1 && j <= fine) 
	{
		if (v[i] < v[j])
			assignElement(&(vout[k]), &(v[i++]));
		else
			assignElement(&(vout[k]), &(v[j++]));
		k++;
	}
	while (i <= i2 - 1)
	{
		assignElement(&(vout[k]), &(v[i++]));
		k++;
	}
	while (j <= fine)
	{ 
		assignElement(&(vout[k]), &(v[j++]));
		k++; 
	}
	for (i = i1; i <= fine; i++) 
		assignElement(&(v[i]), &(vout[i]));
}

//

void quickSort(element a[], int dim)
{
	quickSortR(a, 0, dim - 1);
}

void quickSortR(element a[], int iniz, int fine)
{
	int i, j, iPivot;
	element pivot;
	if (iniz < fine)
	{
		i = iniz;
		j = fine;
		iPivot = fine;
		pivot = a[iPivot];
		do /* trova la posizione del pivot */
		{
			while (i < j && (isLess(a[i], pivot) || isEqual(a[i], pivot)))
				i++;
			while (j > i && (isLess(pivot, a[j]) || isEqual(pivot, a[j])))
				j--;
			if (i < j)
				scambia(&a[i], &a[j]);
		}
		while (i < j);
		if (i != iPivot && !isEqual(a[i], a[iPivot]))
		{
			scambia(&a[i], &a[iPivot]);
			iPivot = i;
		}
		/* ricorsione sulle sottoparti, se necessario */
		if (iniz < iPivot - 1)
			quickSortR(a, iniz, iPivot - 1);
		if (iPivot + 1 < fine)
			quickSortR(a, iPivot + 1, fine);
	} /* (iniz < fine) */
} /* quickSortR */

//
