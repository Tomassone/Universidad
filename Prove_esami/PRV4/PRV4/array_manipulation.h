
#ifndef ARRAY_MANIP_H

    #define ARRAY_MANIP_H

    #include <stdio.h>
    #include "element.h"

    //funziione che restituisce un array vuoto (pieno di zeri).
    void empty_array(element* array, int dim);

    //funzione per caricare gli elementi di un array.
    void read_array(element* array, int dim);

    //funzione che stampa i primi dim elenmenti di un array.
    void print_array(element* array, int dim);

    //funzione che copia i primi dim elementi da un vettore all'altro.
    void copy_array(element* destination, element* source, int dim);

#endif
