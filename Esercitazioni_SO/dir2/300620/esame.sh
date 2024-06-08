#!/bin/bash

path_rec="./recursive.sh"

#variabili utilizzate

n_arg="4"
dir="$4"
num_1="$2"
num_2="$3"
str="$1"

# controllo numero argomenti.
if [[ ! $# -ge $n_arg ]]
then
	echo 'Numero di argomenti passanti errato.'
	exit 1
fi

#controllo numerico
if [[ ! ($num_1 =~ ^[0-9]+$) ]] #controllo che l'argomento sia un intero maggiore di 0.
    then echo "Argomento 2 non intero!"; exit;
    fi

#controllo numerico
if [[ ! ($num_2 =~ ^[0-9]+$) ]] #controllo che l'argomento sia un intero maggiore di 0.
    then echo "Argomento 3 non intero!"; exit;
    fi
    
#controllo cartella
while [ $# -ge $n_arg ]
do
	case "$dir" in
	/*) 
		#la directory inizia per /
		#controllo anche che sia davvero una directory esistente 
		if [[ ! -d "$dir" ]]
		then
			echo 'Il primo argomento non risulta una directory.'
			exit 3
		fi
		
		i="0"
		while [ "$i" -lt "$num_2" ]
		do
			"$path_rec" "$str" "$num_1" "$num_2" "$4"
			i=$(expr "$i" + "1")
		done
		sleep "$num_1"
		
		shift
		dir="$4";;
	*)
		#il nome della directory non inizia per /, quindi non è un path assoluto
		echo "$dir non è una directory assoluta." 
		exit 4;;
	esac
done
