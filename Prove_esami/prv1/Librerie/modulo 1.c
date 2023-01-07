
#define _CRT_SECURE_NO_DEPRECATE //direttiva mandandata al compilatore; per la scanf_s (ci permette di usare la scanf normale)
#include "modulo.h"

evento leggiUno(FILE* fp)
{
	int i = 0;
	char temp;
	evento line_event;
	if (fp != NULL)
	{
		if (fscanf(fp, "%d ", &(line_event.id_unico)) > 0)
		{
			//printf("%d\n", line_event.id_unico);
			do
			{
				temp = fgetc(fp);
				if (temp != '@')
				{
					line_event.stazione_partenza[i] = temp;
					//printf("%c", line_event.stazione_partenza[i]);
					i++;
				}
			} while (temp != '@' && temp != 'EOF' && i < 255);
			line_event.stazione_partenza[i] = '\0';
			i = 0;
			//printf("\n");
			do
			{
				temp = fgetc(fp);
				if (temp != '\n')
				{
					line_event.stazione_arrivo[i] = temp;
					//printf("%c", line_event.stazione_arrivo[i]);
					i++;
				}
			} while (temp != '\n' && temp != 'EOF' && i < 255);
			line_event.stazione_arrivo[i] = '\0';
			//printf("\n");
		}
		else
		{
			line_event.id_unico = -1;
			strcpy(line_event.stazione_arrivo, "Errore");
			strcpy(line_event.stazione_partenza, "Errore");
		}
	}
	else
	{
		line_event.id_unico = -1;
		strcpy(line_event.stazione_arrivo, "Errore");
		strcpy(line_event.stazione_partenza, "Errore");
	}
	return line_event;
}

list leggiTutti(char* fileName)
{
	FILE* fp = fopen(fileName, "r");
	list event_list;
	evento temp;
	event_list = emptylist();
	if (fp != NULL)
	{
		do
		{
			temp = leggiUno(fp);
			//printf("%d ", temp.id_unico);
			//printString(temp.stazione_partenza);
			//printString(temp.stazione_arrivo);
			if (temp.id_unico != -1);
				event_list = cons(temp, event_list);
		}
		while(temp.id_unico != -1);
		fclose(fp);
	}
	else
	{
		printf("Errore di lettura!\n");
		exit(1);
	}
	return event_list;
}

tariffa* leggiTariffe(char* fileName, int* dim)
{
	FILE* fp = fopen(fileName, "r");
	tariffa* elenco;
	char temp;
	int i = 0, j = 0;
	if (fp != NULL)
	{
		do
		{
			temp = fgetc(fp);
			if (temp == '\n')
				(*dim)++;
		}
		while (temp != EOF);
		//printf("%d", *dim);
		rewind(fp);
		elenco = (tariffa*) malloc(sizeof(tariffa) * (*dim));
		for (i = 0; i < *dim; i++)
		{
			do
			{
				temp = fgetc(fp);
				if (temp != '@')
				{
					elenco[i].stazione_partenza[j] = temp;
					j++;
				}
			} 
			while (temp != '@' && temp != 'EOF' && j < 255);
			elenco[i].stazione_partenza[j] = '\0';
			j = 0;
			do
			{
				temp = fgetc(fp);
				if (temp != '@')
				{
					elenco[i].stazione_arrivo[j] = temp;
					j++;
				}
			}
			while (temp != '@' && temp != 'EOF' && j < 255);
			elenco[i].stazione_arrivo[j] = '\0';
			fscanf(fp, "%f", &(elenco[i].costo));
		}
		fclose(fp);
	}
	else
	{
		printf("Errore\n");
		exit(1);
	}
	return elenco;
}
