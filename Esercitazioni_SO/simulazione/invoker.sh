#!/bin/bash

if [[ $# -ne 4 ]] #controllo che abbia solo un argomento.
    then echo Numero di argomenti sbagliato!; exit; 
fi

script_path="/home/LABS/s0001070949/Desktop/do_recursive.sh"

case $1 in
    /*)
        if [ ! -d $1 ]
        then
            "echo" "Dir 1 non esistente nel fs!"
            "exit" "1"
        fi
    ;;
    *)
        "echo" "Dir 1 non globale!"
        "exit" "2"
    ;;
esac

if [[ ! ($2 =~ ^[0-9]+$) ]] #controllo che l'argomento sia un intero maggiore di 0.
    then echo "Argomento 2 non intero!"; exit;
fi

if [[ ($3 =~ ^[0-9]+$) ]] #controllo che l'argomento non sia numerico.
    then echo "Argomento 3 numerico!"; exit;
fi

case $4 in
    /*)
        if [ -d $4 ]
        then
            "echo" "Dir 4 esistente nel fs!"
            "exit" "1"
        fi
    ;;
    *)
        "echo" "Dir 4 non globale!"
        "exit" "2"
    ;;
esac

#invoco il secondo script:

"$script_path" "$1" "$2" "$3" "$4"

