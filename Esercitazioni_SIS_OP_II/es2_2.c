#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>

#define MaxP 8
#define MaxA 12
#define NumGruppi 20

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

void shift_array(int* array, int index)
{
    for (int i = index; i < NumGruppi - 1; i++)
        array[i] = array[i + 1];
    array[NumGruppi - 1] = 0;
}

void entrata(int n_visitatori, int index)
{
    pthread_mutex_lock(&(p.m));

    while (n_visitatori > p.posti_liberi || (p.auto_libere <= 0) || 
        (p.ordine_arrivo > 0 && p.gruppi_sospesi[p.ordine_arrivo - 1]))
    { 
        p.gruppi_sospesi[p.ordine_arrivo] = 1;
        p.ordine_arrivo++;
        pthread_mutex_unlock(&(p.m));
        printf("Processo %d sospeso all'entrata!\n", index);
        sem_wait(&p.S);
        pthread_mutex_lock(&(p.m));
        p.gruppi_sospesi[p.ordine_arrivo - 1] = 0;
        printf("Processo %d attivato all'entrata!\n", index);
    }
    
    p.posti_liberi -= n_visitatori;
    p.auto_libere--;
    printf("Processo %d ha preso una macchina!\n", index);

    pthread_mutex_unlock(&(p.m));
}

void uscita(int n_visitatori, int index)
{
    pthread_mutex_lock(&(p.m));
    
    p.posti_liberi += n_visitatori;
    p.auto_libere++;
    shift_array(p.gruppi_sospesi, p.ordine_arrivo);
    p.ordine_arrivo--;

    pthread_mutex_unlock(&(p.m));

    for (int i = 0 ; i < NumGruppi; i++)
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
    pthread_exit(0);
}

int main()
{ 
    pthread_t gruppi[NumGruppi];
    srand(time(0));

    // inizializzazione struttura dati:
    p.auto_libere = MaxA;
    p.posti_liberi = MaxP;
    p.ordine_arrivo = 0;
    for (int i = 0; i < NumGruppi; i++)
        p.gruppi_sospesi[i] = 0;

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