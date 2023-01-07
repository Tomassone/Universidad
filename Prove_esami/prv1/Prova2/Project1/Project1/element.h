
#ifndef ELEMENT_H

	#define ELEMENT_H
	#include <stdio.h>
	#include <stdlib.h>
	#include <string.h>
	
	typedef struct
	{
		char codice[6];
		char nome[21];
		float raro;
		char tipo; //O, V, C, P
	} Specie;

	typedef struct
	{
		char nome[16];
		char codice[6];
		int numero;
		int distanza;
	} Avvistamento;

	Specie* leggiUccelli(char nomefile[], int* dim);

	void stampaUccelli(Specie* uccelli, int dim);

	float valore_avvistamento(Avvistamento a, Specie* uccelli, int dim_u);

#endif