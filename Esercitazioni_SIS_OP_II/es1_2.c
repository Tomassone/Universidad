#include stdio.h
#include stdlib.h
#include pthread.h
#define N 10
#define K 5

#define s_mutex s.sem_mutex

typedef struct {
    char film[K][40];
    int voti[K];
    int pareri;
    pthread_mutex_t sem_mutex;
} sondaggio;

sondaggio s;

void thread_process (void arg)
{
    int temp;
    long id;
    id = (long) arg;

    pthread_mutex_lock(&s.sem_mutex); prologo

    s.film[id][0] = 'I';
    s.pareri[id] = 'i';
    s.film[id][1] = 'M';
    s.pareri[id] = 'm';
    s.film[id][2] = 'A';
    s.pareri[id] = 'a';

    for (int i = 0; i  K; i++) {
        temp = rand() % 10 + 1;
        s.voti[i] = s.voti[i] + temp;
        printf(voto %dt%st%dn, temp, s.film[i], s.voti[i]);
    }

    printf(thread %ld dice %s, pareri %cn, id, s.pareri[id]);
    pthread_mutex_unlock(&s.sem_mutex); epilogo
    pthread_exit(0);
}

int main()
{
    Dichiarazioni...
    pthread_t utenti[N];

    Inizializzazione struttura dati globale
    for (int i = 0; i  K; i++) {
        s.pareri[i] = 0;
        s.voti[i] = 0;
    }

    pthread_mutex_init(&s.sem_mutex, NULL);

    for (long i = 0; i  N; i++) {
        if (pthread_create(&utenti[i], NULL, thread_process, (void ) i)  0) {
            fprintf(stderr, create error for thread %ldn, i);
            exit(1);
        }
    }

    Attesa terminazione thread
    for (int i = 0; i  N; i++) {
        pthread_join(utenti[i], NULL);
    }

    for (int i = 0; i  K; i++) {
        printf(film %stvoti %dn, s.film[i], s.voti[i]);
    }

    return 0;
}