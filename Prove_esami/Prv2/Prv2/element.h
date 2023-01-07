
#ifndef ELEMENT_H

	#define ELEMENT_H

	#include <stdio.h>
	#include <stdlib.h>

	typedef struct
	{
		int anno;
		int mese;
		int giorno;
	} time;

	typedef struct
	{
		int id;
		char nome[32];
		float prezzo;
		time data_scadenza;
	} Ingrediente;

	typedef struct
	{
		char nome[64];
		int num_ingr;
		int* elenco_id_ingr;
	} Ricetta;

	Ingrediente* leggiIngredienti(char* nomefile, int* dimI);

	void stampaIngredienti(Ingrediente* i, int dimI);
	
#endif
