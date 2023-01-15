
#ifndef ELEMENT_H

	#define ELEMENT_H
	
	#include <stdio.h>
	#include <stdlib.h>
	#include <string.h>

	typedef struct
	{
		char codiceAtleta[5];
		char nomeAtleta[24];
		int gare_completate;
		int tempi_gare[3];
	} Atleta;

	typedef Atleta element;
	
	void assignElement(element* a, element* b);

	Atleta leggiSingoloAtleta(FILE* fp);

	Atleta* leggiAtleti(char fileName[], int* dim);

	void stampaAtleta(Atleta a);

	int compare(Atleta a1, Atleta a2, char* tipo);

#endif 
