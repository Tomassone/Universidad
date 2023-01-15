
#ifndef ELEMENT_H

	#define ELEMENT_H
	
	#include <stdio.h>
	#include <stdlib.h>
	#include <string.h>

	typedef int element;

	#define TRUE 1
	#define FALSE 0
	typedef int boolean;

	boolean isEqual(element e1, element e2);

	void assignElement(element* a, element* b);

	void printElement(element el);

	element getElement(void); //RICEVO DA INPUT

	boolean isLess(element e1, element e2);

#endif 
