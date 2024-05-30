#!/bin/bash

for file in "$1"/*
do
    if [ -f "$file" ]  #se è un file
    then
        dir_name=`cut -f 2 -d " " "$file" | head -n 1`
        if [ -d "$2/$dir_name" ] #se esiste già la cartella
        then
            cp "$file" "$2/$dir_name"
        else
            mkdir "$2/$dir_name"
            cp "$file" "$2/$dir_name"
        fi
    else
        "./recursive.sh"  "$1/$file" "$2"
    fi
done