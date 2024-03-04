
#define NO_BOATS 1
#define WRONG_LENGHT 2
#define WRONG_INITIALS 3
#define NOT_NUMERIC_FINALS 4

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int zeroInputs(int nOfInputs)
{
	if (nOfInputs <= 1)
	{
		printf("Non e' stata inserita alcuna nave!\n");
		return NO_BOATS;
	}
	return 0;
}

int wrongSize(char* string)
{
	if (strlen(string) != 6)
	{
		printf("La lunghezza di uno o più degli argomenti non rispetta le specifiche di lunghezza!\n");
		return WRONG_LENGHT;
	}
	return 0;
}

int wrongInitials(char* string)
{
	if (strncmp(string, "ME", 2) && strncmp(string, "PA", 2))
	{
		printf("Una o più delle stringhe digitate non inizia con ME o PA!\n");;
		return WRONG_INITIALS;
	}
	return 0;
}

int notNumericValue(char* string )
{
	for (int i = 2; i < 6; i++)
		if (!isdigit(*(string + i)))
		{
			printf("Uno o piu' dei 4 caratteri finali non e' un numero!\n");
			return NOT_NUMERIC_FINALS;
		}
	return 0;
}

int main(int argc, char* argv[])
{
	int numMerc = 0;
	int numPa = 0;

	if (zeroInputs(argc)) //controllo ci siano degli argomenti
		return NO_BOATS;
	else
	{	
		for (int i = 1; i < argc; i++)
		{
			if (wrongSize(*(argv + i))) //controllo lunghezza
				return WRONG_LENGHT;
			if (wrongInitials(*(argv + i))) //controllo prime due iniziali
				return WRONG_INITIALS;

			if (strncmp(argv[i], "ME", 2) == 0) //aggiorno conteggi
				numMerc++;
			if (strncmp(argv[i], "PA", 2) == 0)
				numPa++;

			if (notNumericValue(*(argv + i))) //controllo che gli ultimi 4 elementi siano numerici
				return NOT_NUMERIC_FINALS;
		}
	}	
	
	if (numMerc >= numPa)
	{
		for (int i = 1; i < argc; i++)
			if (strncmp(argv[i], "ME", 2) == 0)
				printf("%s\n", (*(argv + i) + 2));
	}
	else
	{
		for (int i = 1; i < argc; i++)
			if (strncmp(argv[i], "PA", 2) == 0)
				printf("%s\n", (*(argv + i) + 2));
	}
	
	return 0;
}
