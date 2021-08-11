#include <stdio.h>
#include <math.h>

void temperatureConvertion(double temp, char type);
void areaConvertion(double area, char type);
void lengthConvertion(double length, char type);
void weightConvertion(double weight, char type);

int main(){
    temperatureConvertion(32, 'K');
    areaConvertion(10, 'K');
    lengthConvertion(100, 'Y');
    weightConvertion(50, 'P');
    return 0;
}

void temperatureConvertion(double temp, char type){
    if (type == 'C'){
        double fahrenheit = ((temp * (9.0/5.0)) + 32);
        double kelvin = temp + 273.15;

        printf("%f%c converts to %fF and %fK\n", temp, type, fahrenheit, kelvin);
    } else if (type == 'F'){
        double celcius = (5 * (temp - 32)) / 9.0;
        double kelvin = (temp - 32) * (5.0/9.0) + 273.15;

        printf("%f%c converts to %fC and %fK\n", temp, type, celcius, kelvin);
    } else if (type == 'K'){
        double celcius = temp - 273.15;
        double fahrenheit = (temp - 273.15) * (9.0/5.0) + 32;

        printf("%f%c converts to %fC and %fF\n", temp, type, celcius, fahrenheit);
    }
}

void areaConvertion(double area, char type){
    if (type == 'K')
    {
        double hectare = area * 100;
        double squareMile = area / 2.59;
        double squareYard = area * 1196000;
        double squareFoot = area * 1.076e+7;
        double acre = area * 247;

        printf("%f square kilometers converts to %f hectares, %f square miles, %f square yards, %f square feet and %f acres\n", area, hectare, squareMile, squareYard, squareFoot, acre);
    } else if (type == 'H')
    {
        double squareKilometer = area / 100;
        double squareMile = area / 259;
        double squareYard = area * 11960;
        double squareFoot = area * 107639;
        double acre = area * 2.471;

        printf("%f hectares converts to %f square kilometers, %f square miles, %f square yards, %f square feet and %f acres\n", area, squareKilometer, squareMile, squareYard, squareFoot, acre);
    } else if (type == 'M')
    {
        double squareKilometer = area * 2.59;
        double hectare = area * 259;
        double squareYard = area * 3.098e+6;
        double squareFoot = area * 2.788e+7;
        double acre = area * 640; 

        printf("%f square miles converts to %f square kilometers, %f hectares, %f square yards, %f square feet and %f acres\n", area, squareKilometer, hectare, squareYard, squareFoot, acre);
    } else if (type == 'Y')
    {
        double squareKilometer = area / 1.196e+6;
        double hectare = area / 11960;
        double squareMile = area / 3.098e+6;
        double squareFoot = area * 9;
        double acre = area / 4840; 

        printf("%f square yards converts to %f square kilometers, %f hectares, %f square miles, %f square feet and %f acres\n", area, squareKilometer, hectare, squareMile, squareFoot, acre);
    } else if (type == 'F')
    {
        double squareKilometer = area / 1.076e+7;
        double hectare = area / 107639;
        double squareMile = area / 2.788e+7;
        double squareYard = area / 9;
        double acre = area / 43560; 

        printf("%f square feet converts to %f square kilometers, %f hectares, %f square miles, %f square yards and %f acres\n", area, squareKilometer, hectare, squareMile, squareYard, acre);
    } else if (type == 'A')
    {
        double squareKilometer = area / 247;
        double hectare = area / 2.471;
        double squareMile = area / 640;
        double squareYard = area  * 4840;
        double squareFoot = area * 43560; 

        printf("%f acres converts to %f square kilometers, %f hectares, %f square miles, %f square yards and %f square feet\n", area, squareKilometer, hectare, squareMile, squareYard, squareFoot);
    }
    
}

void lengthConvertion(double length, char type){
    if (type == 'K')
    {
        double mile = length / 1.609;
        double yard = length * 1094;
        double feet = length * 3281;

        printf("%f kilometers converts to %f miles, %f yards and %f feet\n", length, mile, yard, feet);
    } else if (type == 'M')
    {
        double kilometers = length * 1.609;
        double yard = length * 1760;
        double feet = length * 5280;

        printf("%f miles converts to %f kilometers, %f yards and %f feet\n", length, kilometers, yard, feet);
    } else if (type == 'Y')
    {
        double kilometers = length / 1094.0;
        double mile = length / 1760.0;
        double feet = length * 3;

        printf("%f yards converts to %f kilometers, %f miles and %f feet\n", length, kilometers, mile, feet);
    } else if (type == 'F')
    {
        double kilometers = length / 3281.0;
        double mile = length / 5280.0;
        double yard = length / 3.0;

        printf("%f feet converts to %f kilometers, %f miles and %f yards\n", length, kilometers, mile, yard);
    } 
}

void weightConvertion(double weight, char type){
    if (type == 'G')
    {
        double ton = weight / 907185;
        double pound = weight / 454;
        double carrat = weight * 5;

        printf("%f grams converts to %f tons, %f pounds and %f carrats\n", weight, ton, pound, carrat);
    } else if (type == 'T')
    {
        double gram = weight * 907185;
        double pound = weight * 2000;
        double carrat = weight * 4.536e+6;

        printf("%f tons converts to %f grams, %f pounds and %f carrats\n", weight, gram, pound, carrat);
    } else if (type == 'P')
    {
        double ton = weight / 2000;
        double gram = weight * 454;
        double carrat = weight * 2268;

        printf("%f pounds converts to %f tons, %f grams and %f carrats\n", weight, ton, gram, carrat);
    } else if (type == 'C')
    {
        double ton = weight / 4.536e+6;
        double pound = weight / 2268;
        double gram = weight / 5;

        printf("%f carrats converts to %f tons, %f pounds and %f grams\n", weight, ton, pound, gram);
    }
}