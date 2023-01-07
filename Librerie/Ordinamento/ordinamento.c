
#define _CRT_SECURE_NO_DEPRECATE

#include "ordinamento.h"

void scambia(int* a, int* b)
{
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

//

void naiveSort(int v[], int n)
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

int trovaPosMax(int v[], int n)
{
	int i, posMax = 0;
	for (i = 1; i < n; i++)
		if (v[posMax] < v[i])
			posMax = i;
	return posMax;
}

//

void bubbleSort(int v[], int n)
{
	int i, ordinato = 0;
	while (n > 1 && !ordinato)
	{
		ordinato = 1;
		for (i = 0; i < n - 1; i++)
			if (v[i] > v[i + 1])
			{
				scambia(&(v[i]), &(v[i + 1]));
				ordinato = 0;
			}
		n--;
	}
}


//

void insOrd(int v[], int pos)
{
	int i = pos - 1, x = v[pos];
	while (i >= 0 && x < v[i]) 
	{
		v[i + 1] = v[i]; /* crea lo spazio */
		i--;
	}
	v[i + 1] = x; /* inserisce l’elemento */
}

//

void mergeSort(int v[], int first, int last, int vout[]) 
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

void merge(int v[], int i1, int i2, int fine, int vout[]) 
{
	int i = i1, j = i2, k = i1;
	while (i <= i2 - 1 && j <= fine) 
	{
		if (v[i] < v[j])
			vout[k] = v[i++];
		else
			vout[k] = v[j++];
		k++;
	}
	while (i <= i2 - 1)
	{
		vout[k] = v[i++]; 
		k++;
	}
	while (j <= fine)
	{ 
		vout[k] = v[j++]; 
		k++; 
	}
	for (i = i1; i <= fine; i++) 
		v[i] = vout[i];
}

//

void quickSort(int a[], int dim)
{
	quickSortR(a, 0, dim - 1);
}

void quickSortR(int a[], int iniz, int fine)
{
	int i, j, iPivot, pivot;
	if (iniz < fine)
	{
		i = iniz;
		j = fine;
		iPivot = fine;
		pivot = a[iPivot];
		do /* trova la posizione del pivot */
		{
			while (i < j && a[i] <= pivot) 
				i++;
			while (j > i && a[j] >= pivot) 
				j--;
			if (i < j)
				scambia(&a[i], &a[j]);
		}
		while (i < j);
		if (i != iPivot && a[i] != a[iPivot])
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
