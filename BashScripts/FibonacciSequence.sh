## @file FibonacciSequence.sh
#  @author Madeline Knez
#  @brief Given a number as input, the script will find the fibonacci sequence up to that number
#  @date 08/12/21

#!/bin/bash

item1=0
item2=1

echo "$item1 $item2 \c"
for ((i=0;i<$1;i++))
do
    temp=$(($item1+$item2))
    echo " $temp \c"
    item1=$item2
    item2=$temp
done