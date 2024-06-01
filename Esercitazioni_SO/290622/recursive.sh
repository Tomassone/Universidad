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
				n_x=exp("$n_x" + "1");
			fi
			if [ "$owner" = "$2" ]
			then
				n_y=exp("$n_y" + "1");
			fi
			;;
		*)
			;;
		esac
    else
        "./recursive.sh"  "$1" "$2" "$3" "$4/$file"
    fi
done

echo $n_x
echo $n_y
