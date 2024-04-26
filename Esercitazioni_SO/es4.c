
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
	char source[200];
	char dest[200];
	
	//creo l'indirizzo completo del file da copiare/eliminare.
	stpcpy(source, dir1);
	strcat(source, "/");
	strcat(source, *(files + iFile));
	
	stpcpy(source, dir2);
	strcat(source, "/");
	strcat(source, "123");
	
	execl("/bin/mv", "mv", source, dest, (char *)0);
	
	exit(TERM_VOLONTARIA);
}

void gestioneNipoti(int iFile, char* dir1, char* dir2, char** files)
{
	char source[200];
	
	//creo l'indirizzo completo del file da copiare/eliminare.
	stpcpy(source, dir1);
	strcat(source, "/");
	strcat(source, *(files + iFile));
	
	execl("/bin/cp", "cp", source, dir2, (char *)0);
	
	exit(TERM_VOLONTARIA);
}

void terminaFigli(int nFile, int* pids, int* waitStatus)
{
	for (int i = 0; i < nFile; i++)
	{
		*pids = wait(waitStatus);
	
		if ((*waitStatus << 8) != 0) //terminazione involontaria
			printf("Il figlio con PID = %d e' terminato involontariamente.\n", *pids);
	}
}

void generaNipoti(int nFile, int* pids, char* dir1, char* dir2, char** elencoFile)
{
	int waitStatus;
	
	for (int i = 0; i < nFile; i++)
	{
		*(pids + i) = fork();
				
		if (*(pids + i) == 0) //processo figlio
			gestioneNipoti(i, dir1, dir2, elencoFile);
		else if (*(pids + i) > 0); //processo padre
		else //errore
			perror("Errore nella creazione del processo figlio.\n");
	}
	
	terminaFigli(nFile, pids, &waitStatus);
}

void generaFigli(int nFile, int* pids, int* pidsFigli, char* dir1, char* dir2, char** elencoFile)
{
	for (int i = 0; i < nFile; i++)
	{
		*(pids + i) = fork();
				
		if (*(pids + i) == 0) //processo figlio
			generaNipoti(nFile, pidsFigli, dir1, dir2, elencoFile);
			//gestioneFigli(i, dir1, dir2, elencoFile);
		else if (*(pids + i) > 0); //processo padre
		else //errore
			perror("Errore nella creazione del processo figlio.\n");
	}
}

int main(int argc, char **argv)
{
	char* dir1 = argv[1]; //nomi assoluti
	char* dir2 = argv[2];
	
	int nFile = argc - 1 - 2;
	int* pidsFigli = malloc(sizeof(int) * nFile);
	int* pidsNipoti = malloc(sizeof(int) * nFile);  
	int waitStatus; 
		
	generaFigli(nFile, pidsFigli, pidsNipoti, dir1, dir2, argv + 1 + 2);
	
	terminaFigli(nFile, pidsFigli, &waitStatus);
	
	printf("Contenuto dir2: \n");
	execl("/bin/ls", "ls", dir2, (char *)0);
	
	free(pidsFigli);
	free(pidsNipoti);
	return 0;
}

