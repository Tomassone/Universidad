#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#define N 10
#define K 5

#define s_mutex s.sem_mutex

// gcc -o es1_2 es1_2.c

typedef struct {
    char film[K][40];
    int voti[K];
    int pareri;
    pthread_mutex_t sem_mutex;
} sondaggio;

sondaggio s;

void *thread_process (void * arg)
{
    int temp = 0;
    pthread_mutex_lock(&(s.sem_mutex)); 

    s.pareri++;

    for (int i = 0; i < K; i++) {
        temp = rand() % 10 + 1;
        s.voti[i] = s.voti[i] + temp;
        printf("Valore generato: %d, %d, %d \n", temp, i, s.voti[i]);
    }

    printf("Accessi: %d\n", s.pareri);
    pthread_mutex_unlock(&(s.sem_mutex));
    pthread_exit(0);
}

int main()
{
    // dichiarazioni
    pthread_t utenti[N];
    srand(time(0));

    // inizializzazione struttura dati globale
    s.pareri = 0;
    for (int i = 0; i < K; i++) {
        s.voti[i] = 0;

        s.film[i][0] = 'F';
        s.film[i][1] = 'I';
        s.film[i][2] = 'L';
        s.film[i][3] = 'M';
        s.film[i][4] = i + 48 + 1;
        s.film[i][5] = '\0';
    }

    pthread_mutex_init(&(s.sem_mutex), NULL);

    for (int i = 0; i < N; i++) {
        if (pthread_create(&(utenti[i]), NULL, thread_process, NULL) < 0) {
            fprintf(stderr, "create error for thread %d\n", i);
            exit(1);
        }
    }

    // attesa terminazione thread
    for (int i = 0; i < N; i++) {
        pthread_join(utenti[i], NULL);
    }

    for (int i = 0; i < K; i++) {
        printf("\n%s %d", s.film[i], s.voti[i] / N);
    }
    printf("\n");

    return 0;
}