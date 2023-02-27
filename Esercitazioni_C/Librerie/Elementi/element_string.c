
#define _CRT_SECURE_NO_DEPRECATE

//element.c per le stringhe.

#include "element.h"
#include <stdio.h>
#include <string.h>

boolean isEqual(element e1, element e2) 
{
    if (strcmp(e1, e2) == 0)
        return TRUE;
    else
        return FALSE;
}

boolean isLess(element e1, element e2) 
{
    if (strcmp(e1, e2) < 0)
        return TRUE;
    else
        return FALSE;
}

element getElement() 
{
    element el;
    scanf("%s", el);
    return el; 
}

void printElement(element el) 
{
    printf("%s", el); 
}

void assignElement(element e1, element e2)
{
    strcpy(e1, e2);
}