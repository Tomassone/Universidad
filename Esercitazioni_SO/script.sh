#!/bin/bash

if [[ $# -ne 1 ]] #controllo che abbia solo un argomento.
    then echo Numero di argomenti sbagliato!; exit; 
fi

if [[ ! ($1 =~ ^[0-9]+$) ]] #controllo che l'argomento sia maggiore di 0.
    then echo Argomento non intero!; exit; 
fi

echo Digita il valore di Fin:
read Fin

if [[ $Fin =~ /+ ]] #controllo che non contenga /, cioè che non sia un indirizzo assoluto. 
    then echo Non è un indirizzo locale!; exit; 
fi

if [[ ! -f $HOME/$Fin ]] #controllo che sia l'indirizzo di un file.
    then echo Non è un indirizzo di un file esistente in home!; exit; 
fi

D=`stat $Fin --printf=%s` #mi prendo la dimensione del file.

D=`expr $D / 1024` #converto in Kilobyte

if [[ $D -ge $1 ]] #controllo che sia l'indirizzo di un file.
    then echo Il file $HOME/$Fin ha dimensione $D, maggiore di $1 KByte!;
else
    echo Il file $HOME/$Fin ha dimensione $D, minore di $1 KByte!;
fi