
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>

#define TERM_VOLONTARIA 1

void gestioneFigli(int iFile, char* dir1, char* dir2, char** files)
{
	int pid = getpid();
	char source[200];
	
	//creo l'indirizzo completo del file da copiare/eliminare.
	stpcpy(source, dir1);
	strcat(source, "/");
	strcat(source, *(files + iFile));
		
	if (pid % 2 == 0)
		execl("/bin/cp", "cp", source, dir2, (char *)0);
	else
		execl("/bin/rm", "rm", source, (char *)0);
	
	exit(TERM_VOLONTARIA);
}

int main(int argc, char **argv)
{
	char* dir1 = argv[1]; //nomi assoluti
	char* dir2 = argv[2];
	
	int nFile = argc - 1 - 2;
	int* pids = malloc(sizeof(int) * nFile); 
	int waitStatus; 
		
	for (int i = 0; i < nFile; i++)
	{
		*(pids + i) = fork();
				
		if (*(pids + i) == 0) //processo figlio
			gestioneFigli(i, dir1, dir2, argv + 1 + 2);
		else if (*(pids + i) > 0); //processo padre
		else //errore
			perror("Errore nella creazione del processo figlio.\n");
	}
	
	for (int i = 0; i < nFile; i++)
	{
		*pids = wait(&waitStatus);
	
		if ((waitStatus << 8) != 0) //terminazione involontaria
			printf("Il figlio con PID = %d e' terminato involontariamente.\n", *pids);
	}
	
	printf("Contenuto dir2: \n");
	execl("/bin/ls", "ls", dir2, (char *)0);
	
	free(pids);
	return 0;
}

