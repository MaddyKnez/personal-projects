from PIL import Image
from colorama import Fore, Back, Style
import subprocess

def pixelMatrix(image):
    pixels = list(image.getdata())
    return [pixels[i:i+image.width] for i in range(0, len(pixels), image.width)]

def convertPixelsAverage(matrix):
    for row in range(len(matrix)):
        for column in range(len(matrix[row])):
            R = matrix[row][column][0]
            B = matrix[row][column][1]
            G = matrix[row][column][2]

            matrix[row][column] = (R + B + G) / 3
    return matrix

def convertPixelsLightness(matrix):
    for row in range(len(matrix)):
        for column in range(len(matrix[row])):
            R = matrix[row][column][0]
            B = matrix[row][column][1]
            G = matrix[row][column][2]

            matrix[row][column] = (max(R, G, B) + min(R, G, B)) / 2
    return matrix

def convertPixelsLuminosity(matrix):
    for row in range(len(matrix)):
        for column in range(len(matrix[row])):
            R = matrix[row][column][0]
            B = matrix[row][column][1]
            G = matrix[row][column][2]

            matrix[row][column] = 0.21*R + 0.72*G + 0.07*B
    return matrix

def convertPixels(matrix, convertionType):
    if convertionType == "Average":
        return convertPixelsAverage(matrix)
    elif convertionType == "Lightness":
        return convertPixelsLightness(matrix)
    else:
        return convertPixelsLuminosity(matrix)

def invertColour(matrix):
    maxBrightness = 255
    for row in range(len(matrix)):
        for column in range(len(matrix[row])):
            matrix[row][column] = maxBrightness - matrix[row][column]
    return matrix

def convertToASCII(matrix, ASCIIChars):
    maxBrightness = 255
    for row in range(len(matrix)):
        for column in range(len(matrix[row])):
            currentBightness = matrix[row][column]
            if(currentBightness == 0):
                matrix[row][column] = ASCIIChars[0]
            else:
                matrix[row][column] = ASCIIChars[round((len(ASCIIChars)-1) / (maxBrightness / currentBightness))]
    return matrix

def printPicture(image, ASCIIChars, convertionType, colour, invert):
    pixels = pixelMatrix(image)
    
    pixels = convertPixels(pixels, convertionType)

    if invert:
        pixels = invertColour(pixels)

    pixels = convertToASCII(pixels, ASCIIChars)

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
    

ASCII = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$"

filepath = "./image.jpg"
subprocess.call(["imagesnap", filepath, "-w", "2"])

img = Image.open(filepath)

img = img.resize((300, 200))

pixelConvertionType = input("Enter a pixel convertion type (enter 'Lightness', 'Average' or 'Luminosity'): ")
color = input("Enter a color (enter 'red', 'blue', 'green', or 'default'): ")
invert = input("Enter 'True' to invert the picture, or 'False' to not invert the picture: ")
printPicture(img, ASCII, pixelConvertionType, color, invert)