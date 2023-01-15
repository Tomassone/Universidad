
#ifndef ELEMENT_H
	
	#define ELEMENT_H
	#define TRUE 1
	#define FALSE 0

	typedef float element; //DEFINIZIONE
	typedef int boolean; 

	boolean isLess(element, element);
	boolean isEqual(element, element);
	element getElement(void); //RICEVO DA INPUT
	void printElement(element);
	void assignElement(element*, element*);

#endif 
