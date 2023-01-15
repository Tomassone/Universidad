
#define _CRT_SECURE_NO_DEPRECATE

#include "element.h"
#include "gara.h"
#include "list.h"

int main()
{
	int dim, i;
	int dim1;
	list l;
	Atleta* elenco = leggiAtleti("File\\risultati.txt", &dim);
	Atleta* elenco_ordinato = ordinaAtletiPer(elenco, dim, "Totale", &dim1);
	/*Atleta prova;
	FILE* fp = fopen("File\\risultati.txt", "r");
	if (fp != NULL)
	{
		prova = leggiSingoloAtleta(fp);
		prova = leggiSingoloAtleta(fp);
		prova = leggiSingoloAtleta(fp);
		prova = leggiSingoloAtleta(fp);
		prova = leggiSingoloAtleta(fp);
		prova = leggiSingoloAtleta(fp);
		fclose(fp);
	}
	else
	{
		printf("Errore nell'apertura del file!\n");
	}*/
	printf("Atleti che hanno partecipato:\n");
	for (i = 0; i < dim; i++)
		stampaAtleta(elenco[i]);
	printf("Classifica generale:\n");
	for (i = 0; i < dim1; i++)
		stampaAtleta(elenco_ordinato[i]);
	elenco_ordinato = ordinaAtletiPer(elenco, dim, "Nuoto", &dim1); 
	printf("\nClassifica nuoto:\n");
	for (i = 0; i < dim1; i++)
		stampaAtleta(elenco_ordinato[i]);
	elenco_ordinato = ordinaAtletiPer(elenco, dim, "Bici", &dim1);
	printf("\nClassifica bici:\n");
	for (i = 0; i < dim1; i++)
		stampaAtleta(elenco_ordinato[i]);
	elenco_ordinato = ordinaAtletiPer(elenco, dim, "Corsa", &dim1);
	printf("\nClassifica corsa:\n");
	for (i = 0; i < dim1; i++)
		stampaAtleta(elenco_ordinato[i]);
	printf("\nAtleti premiati:\n");
	premi(elenco, dim);
	free(elenco);
	free(elenco_ordinato);
	return 0;
}