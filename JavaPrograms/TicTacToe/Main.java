/**
 * @file: Main.java
 * @Author: Madeline Knez
 * @Date: July 12, 2021
 * @Description:
 */
import java.util.Random;
import java.util.Scanner;

public class Main {
    Game game;
    Display display;
    Control control;
    Scanner sc= new Scanner(System.in);

    /**
     * @brief The constructor of the Main class
     * @param startPlayer The player that will start the game
     */
    public Main(boolean startPlayer){
        game = new Game(startPlayer);
        display = new Display();

        control = new Control(game);
    }

    /**
     * @brief A loop that will control the game. The loop terminates when someone has won or there is a tie
     */
    public void gameControl(){
        display.printWelcome();
        while (!game.isGameOver()){
            if(game.getNextPlayerTurn()){
                nextPlayerTurn();
            } else {
                int[] spot = control.nextComputerTurn();
                game.nextTurn(spot[0], spot[1]);

                display.computerOutputMessage();
                display.display(game.getBoard());
            }
        }

        gameEndedMessage();
    }

    /**
     * @brief Prompts the user to enter the positions for their next turn, and collects that data
     */
    public void nextPlayerTurn(){
        display.printInputPrompt();
        String input = sc.nextLine();
        game.nextTurn(Integer.parseInt(String.valueOf(input.charAt(0))), Integer.parseInt(String.valueOf(input.charAt(2))));
        display.display(game.getBoard());
    }

    /**
     * @brief Determines which ending game message should be printed and prints it
     */
    public void gameEndedMessage(){
        if(game.isWinner() == CellType.X){
            display.printWinningMessage();
        } else if(game.isWinner() == CellType.O){
            display.printLosingMessage();
        } else{
            display.printGameOver();
        }
    }

    public static void main(String[] args){
        Random rd = new Random();
        //Main m = new Main(rd.nextBoolean());
        Main m = new Main(true);
        m.gameControl();
    }
}
