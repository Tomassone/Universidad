
#ifndef ARRAY_MANIP_H

    #define ARRAY_MANIP_H

    #include <stdio.h>
    #include "element.h"

    //funziione che restituisce un array vuoto (pieno di zeri).
    void empty_array(element* array, int dim);

    //funzione per caricare gli elementi di un array.
    void read_array(element* array, int dim);

    //funzione che stampa i primi dim elementi di un array iterativamente.
    void print_array(element* array, int dim);

    //funzione che stampa i primi dim elementi di un array ricorsivamente.
    void print_array_r(element* array, int dim);

    //funzione che copia i primi dim elementi da un vettore all'altro.
    void copy_array(element* destination, element* source, int dim);

    //funzione che restituisce 1 se l'elemento dato come input è presente nel vettore; 0 in caso contrario.
    int array_member(element el, element* array, int dim);

#endif
