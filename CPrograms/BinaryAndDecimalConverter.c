#include <stdio.h>
#include <math.h>
#include <string.h>

void binaryToDecimal(char * num);
void decimalToBinary(int num);

int main(){
    char * binary = "1100";
    
    binaryToDecimal(binary);

    decimalToBinary(1222);

    return 0;
}

void binaryToDecimal(char * num){
    int n = strlen(num);

    int i;
    double sum = 0;
    for(i = 0; i < n; i++) {

        if(num[i] == 49){
            sum+=1*pow(2, n - 1 - i);
        }
    }
    printf("%f\n",sum);
}


void decimalToBinary(int num){
    char temp[100] = "";
    int counter = 0;

    while (num > 0) {
        int i = num % 2;
        char ch = i +'0';
        num = num / 2;

        temp[counter] = ch;
        counter++;
    }

    int length = 0;
    while (temp[length] != '\0')
    {
        length++;
    }

    char binary[100] = "";
    int i;
    for (i = 0; i < length; i++)
    {
        binary[i] = temp[length - 1 - i];
    }

    printf("%s\n", binary);
}