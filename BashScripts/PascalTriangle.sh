## @file PascalTriangle.py
#  @author Madeline Knez
#  @brief A script that accepts one arguement of the triagnle height, and prints 
#  a pascal triagnle of that height in the terminal
#  @date 05/20/21

#!/bin/bash

height=$1
one=1

row=()
updatedRow=()
echo $one
for ((i=1;i<=height;i++))
do
    for ((j=0;j<=i;j++))
    do
        if [ $j == 0 ] || [ $j == $i ]
        then
            updatedRow+=( $one )
        else
            k=$(($j-$one))
            value=$((${row[$k]}+${row[$j]}))
            updatedRow+=( $value )
        fi
    done

    row=()
    for item in ${updatedRow[@]}
    do
        row+=( $item )
    done
    echo ${row[@]}
    updatedRow=()
done