
#define _CRT_SECURE_NO_DEPRECATE

#include <stdio.h>
#include "element.h"
#include "ordinamento.h"
#include "array_manipulation.h"
#include "file.h"

int main()
{
	element V[6] = { 1, 8, 9, 0, 6, 5 };
	naiveSort(V, 6);
	print_array(V, 6);
	return 0;
}