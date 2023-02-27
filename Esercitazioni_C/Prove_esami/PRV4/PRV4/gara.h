
#ifndef GARA_H

	#define GARA_H

	#include "element.h"

	#include "ordinamento.h"
	#include <string.h>
	#include "list.h"

	Atleta* ordinaAtletiPer(Atleta* a, int dimA, char* tipo, int* dim);

	int ctrl_no_doppioni(list l, element e);

	list atletiPremiati(Atleta* a, int dimA);

	void premi(Atleta* a, int dimA);

#endif 