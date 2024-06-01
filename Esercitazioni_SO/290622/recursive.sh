#!/bin/bash

n_x="0"
n_y="0"

for file in "$4"/*
do
    if [ -f "$file" ]  #se è un file
    then
        case "$file" in
		*"$3")
			owner=$(ls "-l" "$file" | "awk" '{ print $3 }')
			if [ "$owner" = "$1" ]
			then
				n_x=$(expr "$n_x" + "1")
			fi
			if [ "$owner" = "$2" ]
			then
				n_y=$(expr "$n_y" + "1")
			fi
			;;
		*)
			;;
		esac
    else
        "./recursive.sh"  "$1" "$2" "$3" "$4/$file"
    fi
done

if [ "$n_x" -gt "$n_y" ] 
then
	echo "$(pwd) $(expr "$n_x" - "$n_y")"  >> "$HOME"/esame
fi
