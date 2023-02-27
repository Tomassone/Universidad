
#ifndef ORDINAMENTO_H

    #define ORDINAMENTO_H
    
    #include "C:\Users\cosot\Desktop\Programmazione\Sorgenti\Universidad\Prove_esami\prv1\Prova1\Prova1\modulo.h"

    void scambia(int *a, int *b);

    void scambia1(float* a, float* b);

    void naiveSort(Operazione v[], int n);
    int trovaPosMax(Operazione v[], int n);
    
    void bubbleSort(Operazione v[], int n);
    
    void insOrd(int v[], int pos);

    void mergeSort(int v[], int first, int last, int vout[]); 
    void merge(int v[], int i1, int i2, int fine, int vout[]);

    void quickSort(int a[], int dim);
    void quickSortR(int a[], int iniz, int fine);

    void ordina(Operazione* v, int dim);

#endif