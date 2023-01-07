
#ifndef ORDINAMENTO_H

    #define ORDINAMENTO_H
    
    void scambia(int *a, int *b);

    void naiveSort(int v[], int n);
    int trovaPosMax(int v[], int n);
    
    void bubbleSort(int v[], int n);
    
    void insOrd(int v[], int pos);

    void mergeSort(int v[], int first, int last, int vout[]); 
    void merge(int v[], int i1, int i2, int fine, int vout[]);

    void quickSort(int a[], int dim);
    void quickSortR(int a[], int iniz, int fine);

#endif