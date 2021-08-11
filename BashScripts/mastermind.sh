## @file mastermind.sh
#  @author Madeline Knez
#  @brief A script that runs the game of mastermind
#  @date 08/11/21

#!/bin/bash

## @brief Determines the number of colours in the input that have the same position has the code being guessed
#  @return The number of similar positions between input and the code 
function correctPositions {
    correctPosition=0
    for (( i=0; i<${#code}; i++ ))
    do
        if [ ${code:$i:1} == ${input:$i:1} ]
        then
            correctPosition=$(($correctPosition+1))
        fi 
    done
    return $correctPosition
}

## @brief Determines the number of colours appearing code that also appear in the input
#  @return The number of similar colours between input and the code
function correctColours {
    correctColour=0
    for (( i=0; i<${#code}; i++ ))
    do
        if [[ "$input" == *"${code:$i:1}"* ]] 
        then
            correctColour=$(($correctColour+1))
        fi  
    done
    return $correctColour
}

## @brief Prints the similarities between the input and the code
#  @param The number of colour positions that are the same between input and the code 
#  @param The number of similar colours between input and the code
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

## @brief Prints whether the user has won or lost
function finalResult {
    if [ $found == 1 ]
    then
        echo "You cracked the code!"
    else
        echo "You did not crack the code in time. You lost :("
    fi
}

## @brief Runs the game of mastermind
#  @details A loop runs 8 times or until the code has been guessed by the user
function main {
    code="bgrp"
    found=0

    inputCounter="0"
    while [ $inputCounter -lt 8 ]
    do
        echo "Enter a new code: "
        read input

        correctPositions
        cp=$?

        correctColours
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

    finalResult
}
main