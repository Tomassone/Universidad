
#define _CRT_SECURE_NO_DEPRECATE

#include "string_manipulation.h"

void printString(char* string)
{
	int i = 0;
	while (string[i] != '\0')
	{
		printf("%c", string[i]);
		i++;
	}
	printf("\n");
}