#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <unistd.h>
#include <ctype.h>

#define STRLEN 100

void specialRead(char str[], char spChar)
{
    int status = (str[0] == spChar);

    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] == '\n' && str[i + 1] != spChar)
        {
            status = 0;
            printf("\n");
        }

        if (str[i] == '\n' && str[i + 1] == spChar)
            status = 1;

        if (status == 1 && str[i] != '\n')
            printf("%c", str[i]);

        if ((i == strlen(str) - 1) && status == 1)
            printf("\n");
    }
}

int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        printf("Il numero di argomenti non e' adeguato :(\n");
        exit(1);
    }

    int fd[2];
    pipe(fd); //creo una pipe.

    int pid = fork();
    char *strFin, *strTemp;
    //argv[1] = FileName; arv[2] = Char

    strTemp = (char*) malloc(sizeof(char) * 1);
    strFin = (char*) malloc(sizeof(char) * STRLEN);

    if (pid > 0) //codice eseguito dal processo padre.
    {
        close(fd[1]); //chiudo estremità scrittura.
        read(fd[0], strFin, sizeof(char) * STRLEN);
        
        specialRead(strFin, (argv[2])[0]);
    }
    else if (pid == 0) //codice eseguito dal processo figlio.
    {
        int fdFile = open(argv[1], O_RDONLY, 0777);
        lseek(fdFile, -1, SEEK_END);

        do
        {
            read(fdFile, strTemp, sizeof(char));
            strcat(strFin, strTemp);
        }
        while(lseek(fdFile, -2, SEEK_CUR) >= 0);

        close(fd[0]); //chiudo estremità lettura.
        write(fd[1], strFin, strlen(strFin));     
    } 
    else
    {
        printf("Errore nella creazione del processo figlio :(\n");
        exit(2);
    }

    free(strTemp);
    free(strFin);

    return 0;
}