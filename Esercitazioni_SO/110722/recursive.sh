#!/bin/bash

n_file="0"
n_string="0"

for file in "$3"/*
do
    if [ -f "$file" ] #se è un file
    then
        token=`grep -e "$1" "$file" | wc -c`
        if [ "$token" -ne "0" ]
        then
            n_string=$(expr "$n_string" + "1")
        fi
    else
        "./recursive.sh"  "$1" "$2" "$3/$file"
    fi
    n_file=$(expr "$n_file" + "1")
done

echo $n_file
echo $n_string

pid="sesso"

if [ "$n_string" -gt $(expr "$n_file" / "2") ]
then
    cd "$2"
    echo "$3" "$n_string" >> report.$pid.out.txt
fi