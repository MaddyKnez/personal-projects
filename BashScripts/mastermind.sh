## @file mastermind.sh
#  @author Madeline Knez
#  @brief A script that runs the game of mastermind
#  @date 08/11/21

#!/bin/bash

function correctPositions {
    correctPosition=0
    for (( i=0; i<${#code}; i++ ))
    do
        if [ ${code:$i:1} == ${1:$i:1} ]
        then
            correctPosition=$(($correctPosition+1))
        fi 
    done
    return $correctPosition
}

function correctColours {
    correctColour=0
    for (( i=0; i<${#code}; i++ ))
    do
        if [[ "$1" == *"${code:$i:1}"* ]] 
        then
            correctColour=$(($correctColour+1))
        fi  
    done
    return $correctColour
}

function resultsOfRound {
    echo -n "       "
    for (( i=0; i<$1; i++ ))
    do 
        echo -n "*"
    done

    for (( i=0; i<$2; i++ ))
    do 
        echo -n "#"
    done 
    echo ""
}

function finalResult {
    if [ $1 == 1 ]
    then
        echo "You cracked the code!"
    else
        echo "You did not crack the code in time. You lost :("
    fi
}

function main {
    code="bgrp"
    found=0

    inputCounter="0"
    while [ $inputCounter -lt 8 ]
    do
        echo "Enter a new code: "
        read input

        correctPositions $input
        cp=$?

        correctColours $input
        cc=$?
        cc=$(($cc-$cp))

        if [ $cp == 4 ]
        then
            found=1
            break
        fi

        resultsOfRound $cp $cc

        inputCounter=$(($inputCounter+1))
    done

    finalResult $found
}
main