## @file GameOfLifeGUI.py
#  @author Madeline Knez
#  @brief Uses pygame to create a GUI for the Game of Life program
#  @date 06/01/21

import pygame, sys, random
from pygame.locals import *
pygame.init()
from GameOfLife import gameOfLife

g = gameOfLife("./GGG.txt", 40, 18)
init_state = g.getBoardState()

BACKGROUND = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 30, 70)

FPS = 60
fpsClock = pygame.time.Clock()
WINDOW_WIDTH = g.getWidth() * 10
WINDOW_HEIGHT = g.getHeight() * 10
 
WINDOW = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
pygame.display.set_caption('Life!')

pause_text = pygame.font.SysFont('Times New Roman', 16).render('Pause', True, pygame.color.Color('Black'))


def main (init_state) :
    RUNNING, PAUSE = 0, 1
    state = RUNNING
    looping = True
  
    while looping:

        #Get inputs
        for e in pygame.event.get():
            if e.type == QUIT :
                pygame.quit()
                sys.exit()
            
            if e.type == pygame.KEYDOWN:
                if e.key == pygame.K_p: 
                    state = PAUSE
                if e.key == pygame.K_s: 
                    state = RUNNING
                
            
        if state == RUNNING:
            pygame.time.delay(300)
            init_state = g.nextBoardState(init_state)

            aliveList = []
            for i in range(len(init_state)):
                for j in range(len(init_state[0])):
                    if init_state[i][j]:
                        rectangle = pygame.Rect(10*j, 10*i, 10, 10)
                        aliveList.append(rectangle)
 
            #Render elements of the game
            WINDOW.fill(BACKGROUND)

            for i in range(0, WINDOW_WIDTH, 10): #Draws vertical lines for grid
                pygame.draw.line(WINDOW, BLACK, (i, WINDOW_HEIGHT), (i, 0), 1)

            for i in range(0, WINDOW_HEIGHT, 10): #Draws horizontal lines for grid
                pygame.draw.line(WINDOW, BLACK, (0, i), (WINDOW_WIDTH, i), 1)

            for item in aliveList:
                pygame.draw.rect(WINDOW, BLACK, item)

        elif state == PAUSE:
            WINDOW.blit(pause_text, (0, 0))

        pygame.display.update()
        fpsClock.tick(FPS)
        looping+=1

main(init_state)