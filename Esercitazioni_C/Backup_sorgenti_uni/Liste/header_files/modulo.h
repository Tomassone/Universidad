
#include "list.h"

int select(list l, int n);

list crossSelection(list l1, list l2);

list real_cross(list l, list l1, list l2);

typedef struct
{
	char nome_cl[65];
	int giorno;
	float importo;
} work;

list findBills(char* fileName, char* clientName);

void build_binary();