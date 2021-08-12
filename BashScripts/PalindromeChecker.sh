## @file PalindromeChecker.sh
#  @author Madeline Knez
#  @brief Given a word, the script checks if that word is a palindrome
#  @date 08/12/21

#!/bin/bash

palindrome=0
string=$1
i=0
j=${#string}
j=$(($j-1))

while [ $i -lt $j ]
do
    if [ ${string:$i:1} != ${string:$j:1} ]
    then
        echo "$string is not a palindrome."
        palindrome=1
        break
    fi

    i=$(($i+1))
    j=$(($j-1))
done

if [ $palindrome == 0 ]
then
    echo "$string is a palindrome."
fi