#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <signal.h>
#include <unistd.h>
#include <ctype.h>

int pid1 = 0, pid2 = 0;
int n_term = 0;

void taskOne()
{
    srand(time(NULL));
    
    int chance = rand() % 2 + 1;
    
    sleep(3);

    if (chance == 1)
        kill(getppid(), SIGUSR1);
    else 
        kill(getppid(), SIGUSR2);

}

void sig_usr1_handler()
{
    //printf("Son P0 e ho ricevuto un messaggio da P1 kek\n");
    kill(pid1, SIGKILL);
    kill(pid2, SIGKILL);
    printf("<<Finito!>>\n");
    exit(1);
}

void sig_usr2_handler()
{
    //printf("Son P2 e ho ricevuto un messaggio da P1 kek\n");
    kill(pid2, SIGUSR2);
}

void sig_usrx_handler()
{
	int pid = fork();
	
    if (pid != 0)
	{
		pause();
		printf("Comando date eseguito correttamente!\n");
	}
    else
		execl("/bin/date","date", (char *)0);
}

void sig_child_handler()
{
    n_term++;
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        printf("Il numero di argomenti non e' adeguato!\n");
        return 1;
    }

    if (!isdigit((argv[1])[0]))
    {
        printf("L'argomenti dato non è un intero!\n");
        return 2;
    }

    //imposto gli handler relativi alla gestione dei segnali.
    signal(SIGUSR1, sig_usr1_handler);
    signal(SIGUSR2, sig_usr2_handler);
    signal(SIGCHLD, sig_child_handler);

    pid2 = fork();

    if (pid2 == 0) //codice eseguito dai processi figli.
    {
        signal(SIGUSR2, sig_usrx_handler);
        pause();        
        exit(1); //terminazione processo figlio.
    }
    else if (pid2 > 0) //codice eseguito dal processo padre. 
    {
        pid1 = fork();

        if (pid1 == 0) //codice eseguito dai processi figli.
        {
            taskOne();
            exit(1); //terminazione processo figlio.
        }
        else if (pid1 > 0) //codice eseguito dal processo padre. 
        {
            for (int i = 0; i < atoi((argv[1])) && n_term != 2; i++)
            {
                printf("P0 (PID=%d): attendo un segnale da %d secondi\n", getpid(), i);
                sleep(1);
            }

            if (n_term == 2)
			{
				sleep(1);
				printf("<<Figli terminati!>>\n");
			}
            else
                printf("<<Timeout scaduto!!>>\n");
        }
        else //errore nella creazione del processo.
        {
            perror("Errore nella creazione del processo figlio P1!\n");
            exit(3);
        }
    }
    else //errore nella creazione del processo.
    {
        perror("Errore nella creazione del processo figlio P1!\n");
        exit(3);
    }

    return 0;
}
