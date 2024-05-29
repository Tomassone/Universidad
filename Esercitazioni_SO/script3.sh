#!/bin/bash

if [[ $# -ne 3 ]] #controllo che abbia tre argomenti.
    then echo Numero di argomenti sbagliato!; exit; 
fi

if [[ ! ($1 =~ ^[0-9]+$) ]] #controllo che l'argomento 2 sia maggiore di 0.
    then echo Argomento non intero!; exit; 
fi

if [[ ! $2 =~ /+ ]] #controllo che gli indirizzi contengano /, cioè che non siano indirizzi assoluti. 
	then echo Non è un indirizzo assoluto!; exit;  
fi

if [[ ! -f $2 ]] #controllo che sia l'indirizzo di un file.
	then echo Non è un indirizzo assoluto di un direttorio esistente!; exit;
fi

if [[ ! -r $2 ]] #controllo che sia leggibile.
	then echo Non è leggibile!; exit;
fi
