
#include "modulo.h"

/*void createFile(FILE* f)
{
	int V[7] = {1, 5, 6, 7, 8, 9, 2};
	fwrite(V, sizeof(int), 7, f);
}

int readLength(FILE* f, int* even, int* odd)
{
	int ctrl;
	while (fread(&ctrl, sizeof(int), 1, f) > 0)
	{
		if (ctrl % 2 == 0)
			*even = *even + 1;
		else
			*odd = *odd + 1;
	}
	return *odd + *even;
}

void makeArray(FILE* f, int* V, int even, int odd, int dim)
{
	int i = 0, j = 0, ctrl;
	while (fread(&ctrl, sizeof(int), 1, f) > 0 && j < even)
	{
		if (ctrl % 2 == 0)
		{
			V[j] = ctrl;
			j++;
		}
	}
	rewind(f);
	while (fread(&ctrl, sizeof(int), 1, f) > 0 && j < dim)
	{
		if (ctrl % 2 != 0)
		{
			V[j] = ctrl;
			j++;
		}
	}
	for (j = 0; j < dim; j++)
		printf("%d ", V[j]);
}*/

/*Rent* makeVet(Rent* V, FILE* f, int* dim_l)
{
	int i = 0, dim = 0;
	while (fscanf(f, "%d %s %d") == 3)
		dim++;
	rewind(f);
	V = (Rent*) malloc(dim * sizeof(Rent));
	while (fscanf(f, "%d %s %d", &(V[i]).cd_code, (V[i]).renter, &(V[i]).days) == 3)
	{
		printf("%d %s %d\n", (V[i]).cd_code, (V[i]).renter, (V[i]).days);
		i++;
	}
	fclose(f);
	*dim_l = dim;
	return V;
}*/

Item* articoli(FILE* listino, FILE* venduti, char* tipologia, int* len)
{
	int i, j, dim1 = 0, dim2 = 0, ctrl;
	char temp1[20], temp2[20]; 
	float temp3;
	Item* tot; Item* selez; Item* temp;
	while (fscanf(listino, "%s %s %f", temp1, temp2, &temp3) == 3)
		dim1++;
	rewind(listino);
	while (fscanf(venduti, "%s %s", temp1, temp2) == 2)
		dim2++;
	rewind(venduti);
	tot = (Item*) malloc(sizeof(Item) * dim1);
	temp = (Item*) malloc(sizeof(Item) * dim2);
	selez = (Item*) malloc(sizeof(Item) * dim1);
	for (i = 0; i < dim1; i++)
	{
		fscanf(listino, "%s %s %f", (tot[i].tipologia), (tot[i].marca), &(tot[i].prezzo));
		//printf("%s %s %f\n", (tot[i].tipologia), (tot[i].marca), (tot[i].prezzo));
	}
	for (i = 0; i < dim2; i++)
	{
		fscanf(venduti, "%s %s", (temp[i].tipologia), (temp[i].marca));
		//printf("%s %s\n", (temp[i].tipologia), (temp[i].marca));
	}
	for (i = 0; i < dim1; i++)
	{
		ctrl = FALSE;
		selez[i].n_venduti = 0;
		for (j = 0; j < dim2; j++)
		{
			if ((!strcmp((tot[i].tipologia), (temp[j].tipologia)) && !strcmp((tot[i].tipologia), tipologia)))
			{
				ctrl = TRUE;
				strcpy(selez[*len].marca, tot[i].marca);
				strcpy(selez[*len].tipologia, tot[i].tipologia);
				selez[*len].prezzo = tot[i].prezzo;
				if (!strcmp((tot[i].marca), (temp[j].marca)))
				{				
					selez[*len].n_venduti = (selez[*len].n_venduti) + 1;
					//printf("%d %d %s %s\n", *len, selez[*len].n_venduti, (temp[i].tipologia), (temp[i].marca));
				}
			}
		}
		if (ctrl == TRUE)
			*len = *len + 1;
	}
	return selez;
}
