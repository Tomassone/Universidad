#!/bin/bash

path_rec="./recursive.sh"

if [[ ! $# = '2' ]]
then
	echo 'Numero di argomenti passanti errato.'
	exit
fi

case "$1" in
/*) 
    #la directory inizia per /
    #controllo anche che sia davvero una directory esistente 
    if [[ ! -d "$1" ]]
	then
		echo 'Il primo argomento non risulta una directory.'
		exit 3
	fi;;
*)
    #il nome della directory non inizia per /, quindi non è un path assoluto
    echo "$1 non è una directory assoluta." 
    exit 4;;
esac

case "$1" in
/*) 
    #la directory inizia per /
    #controllo anche che sia davvero una directory esistente  
    if [[ ! -d "$2" ]]
	then
		echo 'Il secondo argomento non risulta una directory.'
		exit 3
	fi;;
*)
    #il nome della directory non inizia per /, quindi non è un path assoluto
    echo "$2 non è una directory assoluta." 
    exit 4;;
esac

#invoco il secondo script:

"$path_rec" "$1" "$2"
