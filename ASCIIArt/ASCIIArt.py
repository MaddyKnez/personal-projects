## @file ASCIIArt.py
#  @author Madeline Knez
#  @brief Contains a class that converts an image to ascii art
#  @date 06/01/21

from PIL import Image
from colorama import Fore, Back, Style
import subprocess

class ASCIIArt():

    ## @brief Converts an image object to its pixel data in a matrix
	#  @param image Any given image 
    #  @return A matrix of the pixel data of the given image
    def pixelMatrix(self, image):
        pixels = list(image.getdata())
        return [pixels[i:i+image.width] for i in range(0, len(pixels), image.width)]

    ## @brief Averages the values of the R B G value of each pixel in the given matrix
	#  @param matrix A matrix that represents an image's pixel data
    #  @return The converted matrix
    def convertPixelsAverage(self, matrix):
        for row in range(len(matrix)):
            for column in range(len(matrix[row])):
                R = matrix[row][column][0]
                B = matrix[row][column][1]
                G = matrix[row][column][2]

                matrix[row][column] = (R + B + G) / 3
        return matrix

    ## @brief Converts the values of the R B G value of each pixel in the given matrix to represent its lightness
	#  @param matrix A matrix that represents an image's pixel data
    #  @return The converted matrix
    def convertPixelsLightness(self, matrix):
        for row in range(len(matrix)):
            for column in range(len(matrix[row])):
                R = matrix[row][column][0]
                B = matrix[row][column][1]
                G = matrix[row][column][2]

                matrix[row][column] = (max(R, G, B) + min(R, G, B)) / 2
        return matrix

    ## @brief Converts the values of the R B G value of each pixel in the given matrix to represent its luminosity
	#  @param matrix A matrix that represents an image's pixel data
    #  @return The converted matrix
    def convertPixelsLuminosity(self, matrix):
        for row in range(len(matrix)):
            for column in range(len(matrix[row])):
                R = matrix[row][column][0]
                B = matrix[row][column][1]
                G = matrix[row][column][2]

                matrix[row][column] = 0.21*R + 0.72*G + 0.07*B
        return matrix

    ## @brief Determines how the pixel matrix of an image will be converted 
	#  @param matrix A matrix that represents an image's pixel data
    #  @param convertionType Represets the type of convertion that the pixel data should under go
    #  @return The converted matrix
    def convertPixels(self, matrix, convertionType):
        if convertionType == "Average":
            return self.convertPixelsAverage(matrix)
        elif convertionType == "Lightness":
            return self.convertPixelsLightness(matrix)
        else:
            return self.convertPixelsLuminosity(matrix)

    ## @brief Converts the matrix to have inverted colours
	#  @param matrix A matrix that represents an image's pixel data
    #  @return The matrix with inverted colours
    def invertColour(self, matrix):
        maxBrightness = 255
        for row in range(len(matrix)):
            for column in range(len(matrix[row])):
                matrix[row][column] = maxBrightness - matrix[row][column]
        return matrix

    ## @brief Converts the matrix of pixel data to ascii values
	#  @param matrix A matrix that represents an image's pixel data
    #  @return A matrix of ascii characters
    def convertToASCII(self, matrix, ASCIIChars):
        maxBrightness = 255
        for row in range(len(matrix)):
            for column in range(len(matrix[row])):
                currentBightness = matrix[row][column]
                if(currentBightness == 0):
                    matrix[row][column] = ASCIIChars[0]
                else:
                    matrix[row][column] = ASCIIChars[round((len(ASCIIChars)-1) / (maxBrightness / currentBightness))]
        return matrix

    ## @brief Prints ascii art based on the given image and other given parameters
	#  @param image An image to be converted to ascii art
    #  @param ASCIIChars A list of ascii characters, ordered from least dense to most dense
    #  @param convertionType The type of convertion that the matrix of pixel data should have
    #  @param colour The colour that the ascii art should be printed in
    #  @param invert A boolean value that determines if the colours of the image should be inverted or not
    def printPicture(self, image, ASCIIChars, convertionType, colour, invert):
        pixels = self.pixelMatrix(image)
    
        pixels = self.convertPixels(pixels, convertionType)

        if invert:
            pixels = self.invertColour(pixels)

        pixels = self.convertToASCII(pixels, ASCIIChars)

        if colour == "green":
            print(Fore.GREEN)
        elif colour == "red":
            print(Fore.RED)
        elif colour == "blue":
            print(Fore.BLUE)

        for row in range(len(pixels)):
            for column in range(len(pixels[row])):
                print(pixels[row][column] + pixels[row][column] + pixels[row][column], end='')
            print() 
        print(Fore.RESET)
    


a = ASCIIArt()
ASCII = " .'`^\",:;Il!><~+_-?][}{1)(|\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$"

filepath = "./image.jpg"
subprocess.call(["imagesnap", filepath, "-w", "2"])

img = Image.open(filepath)
img = img.resize((300, 200))

pixelConvertionType = input("Enter a pixel convertion type (enter 'Lightness', 'Average' or 'Luminosity'): ")
color = input("Enter a color (enter 'red', 'blue', 'green', or 'default'): ")
invert = input("Enter 'True' to invert the picture, or 'False' to not invert the picture: ")
a.printPicture(img, ASCII, pixelConvertionType, color, invert)