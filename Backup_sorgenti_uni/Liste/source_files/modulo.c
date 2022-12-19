
#include "modulo.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

list real_cross(list l, list l1, list l2)
{
	element temp_pos;
	if (!empty(l1))
	{
		temp_pos = select(l2, head(l1));
		//printf("%d", temp_pos);
		if (temp_pos != -1)
			return cons(temp_pos, real_cross(l, tail(l1), l2));
		else
			return real_cross(l, tail(l1), l2);
	}
	else
		return l;
}

list crossSelection(list l1, list l2)
{
	list l;
	l = emptylist();
	l = real_cross(l, l1, l2);
	return l;
}

int select(list l, int n)
{
	if (!empty(l))
	{
		if (n == 1)
			return head(l);
		else
			return (select(tail(l), n - 1));
	}
	else
		return -1;
}

void build_binary()
{
	work w1, w2, w3;
	FILE* fp = fopen("//Users//Cesare//Documents//GitHub//Universidad//Backup_sorgenti_uni//Liste//file//binario.dat", "wb");
	if (fp != NULL)
	{
		w1.giorno = 1;
		w1.importo = 25;
		strcpy(w1.nome_cl, "PaoloBellavista");
		w2.giorno = 5;
		w2.importo = 75;
		strcpy(w2.nome_cl, "FedericoChesani");
		w3.giorno = 6;
		w3.importo = 85;
		strcpy(w3.nome_cl, "PaolaMello");
		fwrite(&w1, sizeof(work), 1, fp);
		fwrite(&w2, sizeof(work), 1, fp);
		fwrite(&w3, sizeof(work), 1, fp);
		/*fwrite(w1.nome_cl, sizeof(char), strlen(w1.nome_cl) * sizeof(char), fp);
		fwrite(&(w1.giorno), sizeof(int), 1, fp);
		fwrite(&(w1.importo), sizeof(float), 1, fp);
		fwrite(w2.nome_cl, sizeof(char), strlen(w2.nome_cl) * sizeof(char), fp);
		fwrite(&(w2.giorno), sizeof(int), 1, fp);
		fwrite(&(w2.importo), sizeof(float), 1, fp);
		fwrite(w3.nome_cl, sizeof(char), strlen(w3.nome_cl) * sizeof(char), fp);
		fwrite(&(w3.giorno), sizeof(int), 1, fp);
		fwrite(&(w3.importo), sizeof(float), 1, fp);*/
		fclose(fp);
	}
	else
	{
		printf("Errore di apertura (2)!");
		exit(1);
	}
}


list findBills(char* fileName, char* clientName)
{
	FILE* fp = fopen(fileName, "rb");
	work temp;
	list lista = emptylist();
	if (fp != NULL)
	{
		while (fread(&temp, sizeof(work), 1, fp) > 0)
		{
			//printf("%f", temp.importo);
			if (!strcmp(temp.nome_cl, clientName))
				lista = cons(temp.importo, lista);
		}
		rewind(fp);
		fclose(fp);
		return lista;
	}
	else
	{
		printf("Errore di apertura (3)!");
		exit(1);
	}
}
