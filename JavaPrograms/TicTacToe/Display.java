/**
 * @file: Display.java
 * @Author: Madeline Knez
 * @Date: July 12, 2021
 * @Description: displays the game of Tic Tac Toe
 */

public class Display {
    public void printWelcome(){
        System.out.println("************************");
        System.out.println(" Welcome to Tic Tac Toe ");
        System.out.println("************************");
    }

    /**
     * @brief Displays a prompt to the user to input the position of their next move
     */
    public void printInputPrompt(){
        System.out.println("Enter the coordinates that you would like your piece to be places");
        System.out.println("Format of input: row number space column number (i.e. \"1 2\")");
    }

    /**
     * @brief Prompt that informs the user that it is the computer's turn
     */
    public void computerOutputMessage(){
        System.out.println("It was the computer's turn:");
    }

    /**
     * @brief Displays the current positions of the Xs and Os on the screen
     * @details The display will be in a grid organization
     * @param board The array that holds the current positions of the Xs and Os on the board
     */
    public void display(CellType[][] board){
        int countRow = 0;
        for(CellType[] row : board){
            int countColumn = 0;
            for(CellType item : row){
                countColumn ++;
                if(item != CellType.None){
                    System.out.print(item.name());
                } else {
                    System.out.print(" ");
                }

                if(countColumn < 3){
                    System.out.print('|');
                }
            }
            countRow++;
            System.out.println();
            if(countRow < 3){
                System.out.println("_____");
            }
        }
        System.out.println();
    }

    /**
     * @brief Display a message that the game has ended in a tie
     */
    public void printGameOver(){
        System.out.println("************************");
        System.out.println("     It's a Tie!        ");
        System.out.println("************************");
    }

    /**
     * @brief Display a message that the user has won
     */
    public void printWinningMessage(){
        System.out.println("************************");
        System.out.println("        You win!        ");
        System.out.println("************************");
    }

    /**
     * @brief Display a message that the user has lost
     */
    public void printLosingMessage(){
        System.out.println("************************");
        System.out.println("        You Lost        ");
        System.out.println("************************");
    }
}
