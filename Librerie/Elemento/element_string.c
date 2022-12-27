
//element.c per i float.

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