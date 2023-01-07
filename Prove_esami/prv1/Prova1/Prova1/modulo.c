
#define _CRT_SECURE_NO_DEPRECATE

#include "modulo.h"
#include "C:\Users\cosot\Desktop\Programmazione\Sorgenti\Universidad\Librerie\Ordinamento\ordinamento.h"

Operazione* leggiTutteOperazioni(char* fileName, int* dim)
{
	int i;
	Operazione temp;
	Operazione* result;
	FILE* fp = fopen(fileName, "r");
	*dim = 0;
	if (fp != NULL)
	{
		while (fscanf(fp, "%d%f%s", &(temp.id_unico), &(temp.importo), temp.nome_negozio) == 3)
			*dim = *dim + 1;
		rewind(fp);
		result = (Operazione*) malloc(*dim * sizeof(Operazione));
		for (i = 0; i < *dim; i++)
			fscanf(fp, "%d %f %s", &(result[i].id_unico), &(result[i].importo), result[i].nome_negozio);
		fclose(fp);
		return result;
	}
	else
	{
		printf("Errore di lettura!\n");
		return NULL;
	}
}

void stampaOperazioni(Operazione* array, int dim)
{
	if (dim > 0)
	{
		stampaOperazioni(array, dim - 1);
		printf("%d %f %s\n", array[dim - 1].id_unico, array[dim - 1].importo, array[dim - 1].nome_negozio);
	}
}

float spesaCliente(int idCliente, Operazione* v, int dim)
{
	float total_amount = 0;
	int i;
	for (i = 0; i < dim; i++)
	{
		if (v[i].id_unico == idCliente)
		{
			total_amount = total_amount + v[i].importo;
			//printf("dentro %f", v[i].importo);
		}
	}
	return total_amount;
}