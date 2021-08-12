#include <stdio.h>
#include <string.h>

int vowelCounter(char * word);

int main(){
    printf("%d\n", vowelCounter("test phrase"));
}

int vowelCounter(char * word){
    int count = strlen(word);
    int counter = 0;
    int i;
    for (i = 0; i < count; i++)
    {
        if (word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u')
        {
            counter++;
        }
        
    }
    return counter; 
}