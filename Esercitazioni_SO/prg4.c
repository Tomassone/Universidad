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

typedef struct
{
    int id; //id intero dell’inquinante
    int mcg; //mcg rilevati
} inquinante;

void insertId(inquinante* array)
{
    for (int i = 0; i < 6; i++)
        array[i].id = i;
}

int isAbsolute(char* path)
{
    return (path[0] == '/');
}

void genNumbers(inquinante* array)
{
    srand(time(NULL));
    for (int i = 0; i < 5; i++)
        array[i].mcg = rand() % 101; 
}

void sumElement(inquinante* array)
{
    int n = 0;

    for (int i = 0; i < 5; i++)
        n = n + array[i].mcg;

    array[5].mcg = n;
}

int findTwoID(inquinante* array)
{
    return array[2].mcg;
}

int main(int argc, char *argv[])
{
    int fd, pid;
    inquinante* elenco;

    if (argc != 3)
    {
        printf("Il numero di argomenti non e' adeguato :(\n");
        return 1;
    }

    if (!isAbsolute(argv[1]) || !isAbsolute(argv[2]))
      {
        printf("Uno dei due argomenti non e' assoluto :(\n");
        return 2;
    }
    
    //argv[1] Ftemp; argv[2] Fout 

    elenco = malloc(6 * sizeof(inquinante));
    insertId(elenco);
    pid = fork();

    if (pid == 0) //codice eseguito dai processi figli.
    {
        genNumbers(elenco);
        sumElement(elenco);
        fd = creat(argv[1], 0640); 
        write(fd, elenco, 6 * sizeof(inquinante)); //printf("%d %d\n", elenco[i].mcg, elenco[i].id); 
        
        for (int i = 0; i < 6; i++)
            printf("%d %d\n", elenco[i].mcg, elenco[i].id);

        close(fd);

        exit(1); //terminazione processo figlio.
    }
    else if (pid > 0) //codice eseguito dal processo padre. 
    {
        int status; 
        inquinante twoMcg, mediaMcg; 
        char* string;
        wait(&status);

        string = malloc(1000 * sizeof(char));
        fd = open(argv[1], O_RDONLY);

        lseek(fd, 2 * sizeof(inquinante), SEEK_SET);
        read(fd, &twoMcg, sizeof(inquinante));

        lseek(fd, 5 * sizeof(inquinante), SEEK_SET);
        read(fd, &mediaMcg, sizeof(inquinante));

        //read(fd, elenco, 6 * sizeof(inquinante));

        //twoMcg = findTwoID(elenco);

        sprintf(string, "AQI: %d\nPM2.5: %d", mediaMcg.mcg, twoMcg.mcg);
        printf("%s\n", string);
        close(fd);

        fd = creat(argv[2], 0640);
        write(fd, string, strlen(string) * sizeof(char)); //printf("%d %d\n", elenco[i].mcg, elenco[i].id); 
        unlink(argv[1]);

        free(string);
    }
    else //errore nella creazione del processo.
    {
        perror("Errore nella creazione del processo figlio P1!\n");
        exit(3);
    }

    free(elenco);

    return 0;
}