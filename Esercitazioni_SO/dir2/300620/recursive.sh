#!/bin/bash

dir_i=$4
dir_o=$HOME
report=esame

for file in "$dir_i"/*
do
    if [ -f "$file" ]  #se è un file
    then
    
		echo "$file"
		echo "$dir_i"/"$1"
        
		if [ "$file" = "$dir_i"/"$1" ]
		then
			echo "$(date) :: We found $dir_i/$1" > "$HOME"/$$"$1".log
		fi
    
        case "$file" in
		*"$3")
		    #token=`grep -o "$stringa" "$file" | wc -l` #numero file occorrenze stringa
			#owner=$(ls "-l" "$file" | "awk" '{ print $3 }') #proprietario file
			#dir_name=`cut -f 2 -d " " "$file" | head -n 1` #seconda parola prima riga
			#num=`wc -w $file | awk '{ print $1 }'` #numero caratteri file
			#x=$(expr "$n_x" + "1") #incremento
			#y=$(expr "$n_y" + "1")
			#D=`stat $Fin --printf=%s` #mi prendo la dimensione del file in byte.
			;;
		*)
			;;
		esac
    else
        "./recursive.sh"  "$1" "$2" "$3" "$4/$file"
    fi
done

#aggiorno report
: '

if [ "$n_x" -gt "$n_y" ] 
then
	echo "$(pwd) $(expr "$n_x" - "$n_y")"  >> "$dir_o"/$report
fi
'


#per n argomenti 
: '

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
'
