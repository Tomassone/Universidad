#!/bin/bash

path_rec="./recursive.sh"

#variabili utilizzate

n_arg="4"
est="$3"
dir="$1"
num="$2"

# controllo numero argomenti.
if [[ ! $# = $n_arg ]]
then
	echo 'Numero di argomenti passanti errato.'
	exit 1
fi

#controllo estensione
case $est in
.*)
	;;
*)
    echo "$est non è un'estensione." 
    exit 2;;
esac

#stringa lunghezza fissa
if ! [[ "$est" = .??? ]]
then
   echo "Errore: $est deve avere 4 caratteri e cominciare per ."
   exit 1
fi

#controllo cartella
case "$dir" in
/*) 
    #la directory inizia per /
    #controllo anche che sia davvero una directory esistente 
    if [[ ! -d "$dir" ]]
	then
		echo 'Il primo argomento non risulta una directory.'
		exit 3
	fi;;
*)
    #il nome della directory non inizia per /, quindi non è un path assoluto
    echo "$dir non è una directory assoluta." 
    exit 4;;
esac

#controllo numerico
if [[ ! ($num =~ ^[0-9]+$) ]] #controllo che l'argomento sia un intero maggiore di 0.
    then echo "Argomento 2 non intero!"; exit;

#invoco il secondo script:

"$path_rec" "$1" "$2" "$3" "$4"
