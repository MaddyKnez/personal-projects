## @file WordsInWords.sh
#  @author Madeline Knez
#  @brief A script that accepts one arguement of a piece of text, and produces a list of words that
#  are found within other words in that text
#  @date 05/20/21

#!/bin/bash

if [ -f ./file ]
then
    rm ./file
fi 

for word in $1
do
    echo $word >> file
done

words=( "test" )
for word in $1
do
    result=$(egrep -i "$word+[a-z]|[a-z]+$word" ./file) 
    size=${#result}
    set -- $result

    counter="0"
    for t in ${words[@]} #Determine if the word has already been tested and printed
    do
        if [ $t == $word ] 
        then
            counter="1"
        fi
    done

    if [ $counter == "0" ] #If the word hasn't been printed yet
    then
        if [ $# == 1 ]
        then
            echo "The word '$word' is in the word: '$result'"
        elif [ $# -gt 1 ]
        then
            echo "The word '$word' is in the words: "
            for i in $result
            do
                echo "    $i"
            done
        fi
    fi

    if [ $# -gt 0 ] #Add the word to the list of words that have been printed
    then
        words+=( $word )
    fi
done
