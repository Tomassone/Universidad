#!/bin/bash

path_rec="./recursive.sh"

if [[ ! $# -ge '3' ]]
then
	echo 'Numero di argomenti passanti errato.'
	exit
fi

case "$2" in
/*) 
    #la directory inizia per /
    #controllo anche che sia davvero una directory esistente 
    if [[ ! -d "$2" ]]
	then
		echo 'Il primo argomento non risulta una directory.'
		exit 3
	fi;;
*)
    #il nome della directory non inizia per /, quindi non è un path assoluto
    echo "$2 non è una directory assoluta." 
    exit 4;;
esac

string="$1"
out_dir="$2" 

shift
shift

while [ $# -ne "0" ]
do
    case "$1" in
    /*) 
        #la directory inizia per /
        #controllo anche che sia davvero una directory esistente 
        if [[ ! -d "$1" ]]
        then
            echo "Un argomento non risulta una directory."
            exit 3
        fi;;
    *)
        #il nome della directory non inizia per /, quindi non è un path assoluto
        echo "Un argomento non è una directory assoluta." 
        exit 4;;
    esac
    "$path_rec" "$string" "$out_dir" "$1" 
    shift
done