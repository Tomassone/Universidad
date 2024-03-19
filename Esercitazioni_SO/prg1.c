#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>


void genNumbers(int* array, int lenght, int maxValue)
{
    srand(time(NULL)); //inizializzazione generatore.
    printf("Valori generati: \n");

    for (int i = 0; i < lenght; i++)
    {
        *(array + i) = rand() % maxValue;
        printf("%d\n", *(array + i));
    }
}

void genChild(int nChildren, int* array, int arrayLenght)
{
    int pid = 0;
    int pidTerm = 0, statusTerm = 0;

    for (int i = 0; i < nChildren; i++)
    {
        pid = fork();

        if (pid == 0) //codice eseguito dai processi figli.
        {
            int nIstances = 0;

            for (int j = 0; j < arrayLenght; j++)
            {
                if (*(array + j) == (i - 1))
                    nIstances++;
            }
            
            exit(nIstances); //richiedo al processo figlio di terminarsi e di restituirmi nIstances come stato di terminazione.
        }

        pidTerm = wait(&statusTerm);
        
        if ((i - 1) >= 0)
            printf("PID: %d, VOTO: %d, OCCORRENZE: %d;\n", pidTerm, i - 1, statusTerm >> 8);
    }
}

int main(int argc, char *argv[])
{
    int* numArray;
    int arrayLenght, numBase;

    arrayLenght = atoi(argv[1]); //conversione da stringa a decimale.
    numBase = atoi(argv[2]);

    numArray = malloc(sizeof(int) * arrayLenght); //alloco la memoria necessaria per l'array di n casuali.
    genNumbers(numArray, arrayLenght, numBase);

    genChild(numBase + 1, numArray, arrayLenght);

    return 0;
}