## @file GameOfLife.py
#  @author Madeline Knez
#  @brief Contains a class that calculates and prints a game of life board
#  @date 06/01/21

from __future__ import print_function
import random
import copy

class gameOfLife():
    board = []
    width = 0
    height = 0

    ## @brief A constructor of the gameOfLife class
	#  @param width The width that the board should be
    #  @param height The height that the board should be
    def __init__(self, width, height):
        self.board = self.initialBoard(width, height)
        self.width = width
        self.height = height

    ## @brief A constructor of the gameOfLife class
	#  @param file A file that contains information about the initial board state
    #  @param width The width that the board should be
    #  @param height The height that the board should be
    def __init__(self, file, width, height):
        self.board = self.loadBoardState(file)
        self.width = width
        self.height = height

    ## @brief Gets the current board state of the object
	#  @return The current board state
    def getBoardState(self):
        return self.board

    ## @brief Creates an initial board with randomized cell values of deid or alive
	#  @param width The width of the board
    #  @param height The height of the board
    #  @return A 2D array of the initial board with randomized cell values
    def initialBoard(self, width, height):
        board = []
        for _ in range(height):
            list = []
            for _ in range(width):
                list.append(self.getCellValue())
            board.append(list)
        return board

    ## @brief Returns the value of True or False randomly
    #  @return A boolean value that has been randomly chosen
    def getCellValue(self):
        if random.random() >= 0.5:
            return True
        return False

    ## @brief Creates an initial board sate with information from a file
	#  @param filepath A file with information about what cells are died or alive
    #  @return A 2D array or the board of the information from the filepath
    def loadBoardState(self, filepath):
        f = open(filepath, "r")
        board = []

        fileLine = f.readline()
        while fileLine != "":
            list = []
            for item in fileLine:
                if item == '0':
                    list.append(False)
                if item == '1':
                    list.append(True)
            board.append(list)
            fileLine = f.readline()
        return board

    ## @brief Gets the width of the board 
	#  @return The width of the board
    def getWidth(self):
        return self.width

    ## @brief Gets the height of the board 
	#  @return The height of the board
    def getHeight(self):
        return self.height

    ## @brief Prints the given board in the terminal
	#  @param board The board sate that should be printed
    def displayBoard(self, board):
        print(" ", end='')
        for _ in range(len(board[0])):
            print("-", end="")
        print()

        for row in board:
            print("|", end="")
            for item in row:
                if item:
                    print("*", end="")
                else:
                    print(" ", end="")
            print("|")

        print(" ", end="")
        for _ in range(len(board[0])):
            print("-", end="")
        print()

    ## @brief Calculate the next state of the board
	#  @param board The board that will be used to calculate with
    #  @return The next state of the given board
    def nextBoardState(self, board):
        boardCopy = copy.deepcopy(board)
        for row in range(len(board)):
            for column in range(len(board[0])):
                neighbours = self.getNeighbours(board, row, column)
                aliveCount = self.getAliveCount(neighbours)
                if board[row][column]:
                    if aliveCount <= 1 or aliveCount > 3:
                        boardCopy[row][column] = False
                    else:
                        boardCopy[row][column] = True
                else:
                    if aliveCount == 3:
                        boardCopy[row][column] = True
                    else:
                        boardCopy[row][column] = False

        return boardCopy

    ## @brief Identifies the neighbours of a cell and saves their information in an array to be returned
	#  @param board The current board state
    #  @param row The row of the cell
    #  @param column The column of the cell
    #  @return A list of a given cell's neighbours' life status 
    def getNeighbours(self, board, row, column):
        neighbours = []

        try:
            if row -1 < 0 or column - 1 < 0:
                raise Exception(IndexError)
            neighbours.append(board[row-1][column-1])
        except:
            pass

        try:
            if row - 1 < 0:
                raise Exception(IndexError)
            neighbours.append(board[row-1][column])
        except:
            pass
        
        try:
            if row - 1 < 0:
                raise Exception(IndexError)
            neighbours.append(board[row-1][column+1])
        except:
            pass
        
        try:
            if column - 1 < 0:
                raise Exception(IndexError)
            neighbours.append(board[row][column-1])
        except:
            pass

        try:
            neighbours.append(board[row][column+1])
        except:
            pass

        try:
            if column - 1 < 0:
                raise Exception(IndexError)
            neighbours.append(board[row+1][column-1])
        except:
            pass
        
        try:
            neighbours.append(board[row+1][column])
        except:
            pass
        
        try:
            neighbours.append(board[row+1][column+1])
        except:
            pass

        return neighbours

    ## @brief Count the number of neighbours that are alive 
	#  @param neighbours A list of neighbours of a cell
    #  @return The number of neighbours of a cell that are alive
    def getAliveCount(self, neighbours):
        counter = 0
        for neighbour in neighbours:
            if neighbour:
                counter+=1
        return counter


    ## @brief Prints the next board state of the object's board forever
    def eternalLife(self):
        counter = 0
        life = self.board
        self.displayBoard(life)

        while(counter < 20):
            life = self.nextBoardState(life)
            self.displayBoard(life)
            counter+=1

#g = gameOfLife("./toad.txt")
#g.eternalLife()