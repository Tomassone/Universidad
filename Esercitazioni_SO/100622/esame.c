
/*
Si realizzi un programma C con la seguente interfaccia:
esame Fin S I N
dove:
• Fin è il nome assoluto di un file di testo esistente nel file system
• S, I ed N sono numeri interi positivi
Dopo aver effettuato gli opportuni controlli sui parametri in ingresso,
il programma P0 deve impostare un timeout di S secondi e poi generare
due figli: P1 e P2.
P2 deve filtrare la I -esima parola di ogni riga del file Fin. Si veda a tale
proposito l’opzione -f del comando cut.
Si supponga inoltre che il file Fin sia stato redatto correttamente e,
pertanto, presenti solo parole separate da un unico spazio, senza alcun
carattere speciale.
L'output del comando cut deve essere rediretto al processo P1, il quale
stamperà su standard output solo le parole ricevute che presentino più di N
caratteri.
S secondi dopo l’inizio dell’esecuzione, P0 deve stampare a video il
messaggio "Timeout scaduto" e terminare entrambi i figli.
 * */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <unistd.h>
#include <signal.h>
#include <fcntl.h>
#include <string.h>

int PID1, PID2;

void handler()
{
	printf("Timeout scaduto\n");
	//termino entrambi i processi figli.
	kill(PID1, SIGKILL);
	kill(PID2, SIGKILL);
}

int main(int argc, char **argv)
{
	int fd = 0;
	int pid1 = 0, pid2 = 0, pid3 = 0;
	
	if (argc != 5)
	{
		printf("Numero di argomenti sbagliato!\n");
		exit(1);
	}
		
	fd = open(argv[1], O_RDONLY);
	if (fd == -1)
	{
		printf("Primo argomento non corrispondente a file del fs!\n");
		exit(3);
	}
	
	if (!isdigit(argv[2][0])) //se il secondo argomento non è numerico.
	{
		printf("Secondo argomento non numerico!\n");
		exit(2);
	}
	
	if (!isdigit(argv[3][0])) //se il terzo argomento non è numerico.
	{
		printf("Terzo argomento non numerico!\n");
		exit(2);
	}
	
	if (!isdigit(argv[4][0])) //se il quarto argomento non è numerico.
	{
		printf("Quarto argomento non numerico!\n");
		exit(2);
	}
	
	int S = atoi(argv[2]);
	int I = atoi(argv[3]);
	int N = atoi(argv[4]);
	
	signal(SIGALRM, handler);
	
	//setto l'allarme che scadrà tra S secondi.
	alarm(S);
	
	//creo pipe per la comunicazione tra p1 e p2.
	int est[2];
	pipe(est);
	//creo file temporaneo.
	int temp = 0;
	
	//creo i due sottoprocessi.
	pid1 = fork();
	if (pid1 != 0)
		pid2 = fork();
	
	//aggiorno i valori visibili globalmente.
	PID1 = pid1;
	PID2 = pid2;
	
	if (pid1 != 0 && pid2 == 0) //processo p2.
	{
		close(est[0]); //chiudo estremità lettura.
		//temp = creat("temp.txt", 0777);

		pid3 = fork();
		printf("%d\n", pid3);
		
		if (pid3 == 0)
		{
			temp = creat("temp.txt", 0777);
			execlp("/bin/cut", "cut", "-f", "1", "-d", " ", "prova", ">", "/home/aten/Scrivania/temp.txt", (char*)0);
		}
		
		/*char *strTemp, *strFin;
		strTemp = malloc(1);
		strFin = malloc(100);
		
		do
        {
            read(temp, strTemp, sizeof(char));
            strcat(strFin, strTemp);
        }
        while(lseek(temp, 1, SEEK_CUR) >= 0);*/
	}
	
	if (pid1 != 0 && pid2 == 0) //processo p1.
	{
		close(est[1]); //chiudo estremità scrittura.
	}
	
	return 0;
}

