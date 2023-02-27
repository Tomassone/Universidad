
#define _CRT_SECURE_NO_DEPRECATE

//element.c per i float.

#include "element.h"
#include <stdio.h>

boolean isEqual(element e1, element e2) 
{
    if (e1 == e2)
        return TRUE;
    else
        return FALSE;
}

boolean isLess(element e1, element e2) 
{
    if (e1 < e2)
        return TRUE;
    else
        return FALSE;
}

element getElement() 
{
    element el;
    scanf("%f", &el);
    return el; 
}

void printElement(element el) 
{
    printf("%f ", el); 
}

void assignElement(element* e1, element* e2)
{
    *e1 = *e2;
}