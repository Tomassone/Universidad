#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <ctype.h>


void genChild(char* addr, char* numb)
{
    int status;
    int pid = fork();
    
    if (pid == 0)
    { 
        execl("/bin/grep", "grep", "-x", "-c", numb, addr, (char*) 0);
        perror("exec fallita a causa dell’errore:");
        exit(1);
    }
    else if (pid > 0)
        pid = wait(&status); /* gestione dello stato.. */
    else 
        perror("fork fallita a causa dell’errore:");
}

int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        printf("Il numero di argomenti non e' adeguato!\n");
        return 1;
    }

    if (!isdigit((argv[2])[0]))
    {
        printf("Il secondo argomento dato non è un numero!\n");
        return 2;
    }

    char* fAddress = argv[1];
    char* grade = (argv[2]); //conversione da stringa a decimale.

    genChild(fAddress, grade);
   
    return 0;
}