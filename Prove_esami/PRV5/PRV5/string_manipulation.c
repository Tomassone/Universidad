
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

int readField(char buffer[], char sep, FILE* fp)
{
	int i = 0;
	char ch = fgetc(fp);
	while (ch != sep && ch != '\n' && ch != EOF)
	{
		buffer[i] = ch;
		i++;
		ch = fgetc(fp);
	}
	buffer[i] = '\0';
	return ch;
}