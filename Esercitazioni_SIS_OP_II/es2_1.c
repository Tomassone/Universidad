#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>

#define MaxP 5
#define MaxM 3
#define MaxB 2
#define NumVis 30

#define BICICLETTA 0
#define MONOPATTINO 1

// gcc -o es2_1 es2_1.c

typedef struct {
    int posti_liberi;
    int bici_libere;
    int monop_liberi;
    sem_t S; //semaforo condizione
    int sospesi;
    pthread_mutex_t m; //mutua escl. nell'accesso al gestore
} parco;

parco p;

void entrata(int tipo, int index)
{
    pthread_mutex_lock(&(p.m));

    while (p.posti_liberi <= 0 || 
        (tipo == BICICLETTA && p.bici_libere <= 0) ||
            (tipo == MONOPATTINO && p.monop_liberi <= 0))
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

void uscita(int tipo, int index)
{
    pthread_mutex_lock(&(p.m));
    
    p.posti_liberi++;
    if (tipo == MONOPATTINO)
        p.monop_liberi++;
    else 
        p.bici_libere++;

    pthread_mutex_unlock(&(p.m));

    for (int i = 0 ; i < p.sospesi; i++)
        sem_post(&p.S);

    printf("Processo %d uscito!\n", index);
}

void *thread_process (void * arg)
{ 
    int tipo = 0;
    int indice = pthread_self();

    tipo = (rand() % 2);
    printf("Creato processo %d!\n", indice);
    entrata(tipo, indice);
    sleep(rand() % 10);
    uscita(tipo, indice);

    printf("Processo %d ha terminato!\n", indice);
    pthread_exit (0);
}

int main()
{ 
    pthread_t visitatori[NumVis];
    srand(time(0));

    // inizializzazione struttura dati:
    p.posti_liberi = MaxP;
    p.bici_libere = MaxB;
    p.monop_liberi = MaxM;
    p.sospesi = 0;

    // i semafori sono inizialmente liberi:
    pthread_mutex_init (&(p.m), NULL);
    sem_init(&(p.S), 0, 0);

    for (int i = 0; i < NumVis; i++)
    {
        if (pthread_create(&(visitatori[i]), NULL, thread_process, NULL) < 0)
        { 
            fprintf (stderr, "create error for thread %d\n", i);
            exit (1);
        }
    }

    for (int i = 0; i < NumVis; i++)
        pthread_join (visitatori[i], NULL);
}