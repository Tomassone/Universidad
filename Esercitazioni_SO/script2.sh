#!/bin/bash

if [[ $# -lt 3 ]] #controllo che abbia almeno tre argomenti.
    then echo Numero di argomenti sbagliato!; exit; 
fi

if [[ ! ($2 =~ ^[0-9]+$) ]] #controllo che l'argomento 2 sia maggiore di 0.
    then echo Argomento non intero!; exit; 
fi

shift; shift; #slitto gli argomenti di due posizioni

while [ $# -ne 0 ] #controllo che i sia minore del numero di argomenti
do
	echo $1
	
	if [[ ! $1 =~ /+ ]] #controllo che gli indirizzi contengano /, cioè che non siano indirizzi assoluti. 
		then echo Non è un indirizzo assoluto!; exit;  
	fi

	if [[ ! -d $1 ]] #controllo che siano gli l'indirizzi di un direttorio.
		then echo Non è un indirizzo assoluto di un direttorio esistente!; exit;
	fi
	
	cd $1 #cambio direttorio.
	ls
	
	shift; #slitto gli argomenti di una posizione
done

