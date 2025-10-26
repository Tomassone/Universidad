#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>

#define MaxP 5
#define MaxA 8
#define NumGruppi 30

// gcc -o es2_2 es2_2.c

typedef struct{
    int posti_liberi;
    int auto_libere;
    int ordine_arrivo; //quanti processi arrivati
    sem_t S; //semaforo condizione
    int gruppi_sospesi[NumGruppi]; //indice -> ordine di arrivo
    pthread_mutex_t m;
} parco;

parco p;

void entrata(int n_visitatori, int index)
{
    pthread_mutex_lock(&(p.m));

    while (n_visitatori > p.posti_liberi || (p.auto_libere > 0) || 
        !gruppi_sospesi[ordine_arrivo])
    { 
        p.sospesi++;
        pthread_mutex_unlock(&(p.m));
        printf("Processo %d sospeso all'entrata!\n", index);
        sem_wait(&p.S);
        pthread_mutex_lock(&(p.m));
        p.sospesi--;
        printf("Processo %d attivato all'entrata!\n", index);
    }
    
    p.posti_liberi--;
    if (tipo == MONOPATTINO)
    {
        printf("Processo %d ha preso un monopattino!\n", index);
        p.monop_liberi--;
    }
    else
    {
        printf("Processo %d ha preso una bici!\n", index);
        p.bici_libere--;
    }

    pthread_mutex_unlock(&(p.m));
}

void uscita(int n_visitatori, int index)
{
    pthread_mutex_lock(&(p.m));
    
    p.posti_liberi++;
    p.auto_libere++;

    pthread_mutex_unlock(&(p.m));

    for (int i = 0 ; i < p.sospesi; i++)
        sem_post(&p.S);

    printf("Processo %d uscito!\n", index);
}

void *thread_process (void * arg)
{ 
    int n_visitatori = 0;
    int indice = pthread_self();

    n_visitatori = (rand() % 5) + 1;
    printf("Creato processo %d!\n", indice);
    entrata(n_visitatori, indice);
    sleep(rand() % 10);
    uscita(n_visitatori, indice);

    printf("Processo %d ha terminato!\n", indice);
    pthread_exit (0);
}

int main()
{ 
    pthread_t gruppi[NumGruppi];
    srand(time(0));

    // inizializzazione struttura dati:
    p.auto_libere = MaxA;
    p.posti_liberi = MaxP;
    p.ordine_arrivo = 0;

    // i semafori sono inizialmente liberi:
    pthread_mutex_init (&(p.m), NULL);
    sem_init(&(p.S), 0, 0);

    for (int i = 0; i < NumGruppi; i++)
    {
        if (pthread_create(&(gruppi[i]), NULL, thread_process, NULL) < 0)
        { 
            fprintf (stderr, "create error for thread %d\n", i);
            exit (1);
        }
    }

    for (int i = 0; i < NumGruppi; i++)
        pthread_join (gruppi[i], NULL);
}