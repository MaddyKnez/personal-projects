#include <stdio.h>
#include <math.h>

void temperatureConvertion(double temp, char type);
void areaConvertion(double area, char type);
void lengthConvertion(double length, char type);
void weightConvertion(double weight, char type);

int main(){
    temperatureConvertion(32, 'K');
    return 0;
}

void temperatureConvertion(double temp, char type){
    if (type == 'C'){
        double fahrenheit = ((temp * (9.0/5.0)) + 32);
        double kelvin = temp + 273.15;

        printf("%f%c converts to %fF and %fK", temp, type, fahrenheit, kelvin);
    } else if (type == 'F'){
        double celcius = (5 * (temp - 32)) / 9.0;
        double kelvin = (temp - 32) * (5.0/9.0) + 273.15;

        printf("%f%c converts to %fC and %fK", temp, type, celcius, kelvin);
    } else if (type == 'K'){
        double celcius = temp - 273.15;
        double fahrenheit = (temp - 273.15) * (9.0/5.0) + 32;

        printf("%f%c converts to %fC and %fF", temp, type, celcius, fahrenheit);
    }
}

void areaConvertion(double area, char type){
    //TODO: Implement
}

void lengthConvertion(double length, char type){
    //TODO: Implement
}

void weightConvertion(double weight, char type){
    //TODO: Implement
}