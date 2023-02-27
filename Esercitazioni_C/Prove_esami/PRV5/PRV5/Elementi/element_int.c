
#define _CRT_SECURE_NO_DEPRECATE

//element.c per gli interi.

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
    scanf("%d", &el);
    return el; 
}

void printElement(element el) 
{
    printf("%d", el); 
}

void assignElement(element* e1, element* e2)
{
    *e1 = *e2;
}