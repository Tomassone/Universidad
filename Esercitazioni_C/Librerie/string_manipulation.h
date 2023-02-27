
#define _CRT_SECURE_NO_DEPRECATE

#ifndef STRING_MANIP_H

    #define STRING_MANIP_H

    #include <stdio.h>
    #include <string.h>

    //funzione per la stampa delle stringhe con spazi.
	void printString(char* string);

    //funzione che legge da un buffer testuale fino a che non incontra un determinato carattere.
    int readField(char buffer[], char sep, FILE* fp);

#endif