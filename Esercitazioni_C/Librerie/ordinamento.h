
#ifndef ORDINAMENTO_H

    #define ORDINAMENTO_H

    #include "element.h"
    
    void scambia(element* a, element* b);

    void naiveSort(element v[], int n);
    int trovaPosMax(element v[], int n);
    
    void bubbleSort(element v[], int n);
    
    void insOrd(element v[], int pos);
    void insertSort(element v[], int n);

    void mergeSort(element v[], int first, int last, element vout[]);
    void merge(element v[], int i1, int i2, int fine, element vout[]);

    void quickSort(element a[], int dim);
    void quickSortR(element a[], int iniz, int fine);

#endif