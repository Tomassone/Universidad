
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//void createFile(FILE* f);

//int readLength(FILE* f, int* even, int* odd);

//void makeArray(FILE* f, int* V, int even, int odd, int dim);

#define DIM 65
typedef struct {
	int cd_code;
	char renter[DIM];
	int days;
} Rent;

//Rent* makeVet(Rent *V, FILE *f, int *dim_l);

typedef struct
{
	char tipologia[20];
	char marca[20];
	float prezzo;
	int n_venduti;
} Item;

#define TRUE 1
#define FALSE 0

Item* articoli(FILE* listino, FILE* venduti, char* tipologia, int* len);
