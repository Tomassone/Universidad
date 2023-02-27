
#ifndef ELEMENT_H
#define ELEMENT_H

typedef struct
{
	int id_unico;
	char stazione_partenza[256];
	char stazione_arrivo[256];
} evento;

typedef struct
{
	char stazione_partenza[256];
	char stazione_arrivo[256];
	float costo;
} tariffa;

typedef evento element;

#endif 
