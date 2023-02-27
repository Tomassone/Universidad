
#define _CRT_SECURE_NO_DEPRECATE

#ifndef MOD_H
	
	#define MOD_H

	#include <stdio.h>
	#include <stdlib.h>

	typedef struct
	{
		int id_unico;
		float importo;
		char nome_negozio[1024];
	} Operazione;

	typedef struct
	{
		char nome_negozio[1024];
		char tipo; //F o V
	} Negozio;

	Operazione* leggiTutteOperazioni(char* fileName, int* dim);

	void stampaOperazioni(Operazione* v, int dim);

	float spesaCliente(int idCliente, Operazione* v, int dim);

#endif