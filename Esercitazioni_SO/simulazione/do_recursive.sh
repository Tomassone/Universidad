#!/bin/bash

script_path="/home/LABS/s0001070949/Desktop/do_recursive.sh"

x="0"
y="0"

#ciclo ricorsivo:

for file in $1/*
do
    if [[ ! -d "$file" ]]
    then 
        cd $1
        fileid=`ls -l $file | awk '{ print $3 }'`
        if [ $fileid = $USER ]
        then
            x=`expr $x + 1`
        fi

        case $file in
        *$3)
            y=`expr $y + 1`
        ;;
esac

    else
        "$script_path" "$1/$file" "$2" "$3" "$4"
    fi
done

if [ `expr $x + $y` -gt $2 ]
then
    if [ ! -d $4 ]
    then
        mkdir $4
    fi
    echo "$(pwd) $x $y" >> "$4/report.txt"
fi