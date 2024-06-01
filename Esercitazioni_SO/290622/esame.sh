#!/bin/bash

path_rec="./recursive.sh"

if [[ ! $# = '4' ]]
then
	echo 'Numero di argomenti passanti errato.'
	exit 1
fi

case "$3" in
.*)
	;;
*)
    echo "$3 non è un'estensione." 
    exit 2;;
esac

if [[ ! -d "$4" ]]
then
	echo 'Il quarto argomento non risulta una directory.'
	exit 3
fi

#invoco il secondo script:

"$path_rec" "$1" "$2" "$3" "$4"
