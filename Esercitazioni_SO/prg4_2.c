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
        
        char* string, * stringTemp;    
        string = malloc(1000 * sizeof(char));
        stringTemp = malloc(10 * sizeof(char));
        
        for (int i = 0; i < 6; i++)
        {
            sprintf(stringTemp, "%d;%d\n", elenco[i].mcg, elenco[i].id);
            strcat(string, stringTemp);
		}
        
        printf("%s\n", string);
        write(fd, string, strlen(string) * sizeof(char));
        close(fd);

        exit(1); //terminazione processo figlio.
    }
    else if (pid > 0) //codice eseguito dal processo padre. 
    {
        int status; 
        wait(&status);
        
        char* stringNum = malloc(10 * sizeof(char));
        char* stringTemp = malloc(10 * sizeof(char));
        
        fd = open(argv[1], 0640);
        
        int pm25, aqi;
        
        //salto i primi due elementi
        while (strcmp(stringTemp, "\n"))
			read(fd, stringTemp, sizeof(char));
			read(fd, stringTemp, sizeof(char)); 
		while (strcmp(stringTemp, "\n"))
			read(fd, stringTemp, sizeof(char)); 
        
        while (strcmp(stringTemp, ";"))
        {
            read(fd, stringTemp, sizeof(char));            
            if (strcmp(stringTemp, ";"))
				strcat(stringNum, stringTemp);
		}
		
		pm25 = atoi(stringNum);
		stringNum[0]= '\0';
		
		//salto gli altri elementi mancanti
        while (strcmp(stringTemp, "\n"))
			read(fd, stringTemp, sizeof(char));
			read(fd, stringTemp, sizeof(char)); 
		while (strcmp(stringTemp, "\n"))
			read(fd, stringTemp, sizeof(char));
			read(fd, stringTemp, sizeof(char)); 
		while (strcmp(stringTemp, "\n"))
			read(fd, stringTemp, sizeof(char));
        
        while (strcmp(stringTemp, ";"))
        {
            read(fd, stringTemp, sizeof(char));            
            if (strcmp(stringTemp, ";"))
				strcat(stringNum, stringTemp);
		}
		
		aqi = atoi(stringNum);
        
        close(fd);
        fd = creat(argv[2], 0640);
        
        write(fd, &pm25, sizeof(int));
        write(fd, &aqi, sizeof(int));
                
        close(fd);
        unlink(argv[1]);
    }
    else //errore nella creazione del processo.
    {
        perror("Errore nella creazione del processo figlio P1!\n");
        exit(3);
    }

    free(elenco);

    return 0;
}
