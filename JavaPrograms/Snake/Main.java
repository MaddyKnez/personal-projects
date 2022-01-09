import java.util.*;
public class Main {
    public static void main(String args[])
    {
        Scanner sc= new Scanner(System.in);

        Game game = new Game(11, 10);
        game.display();

        String str= sc.nextLine();
        while (!str.equals("e")){
            if(str.equals("w"))
                game.updateSnake("UP");
            else if(str.equals("s"))
                game.updateSnake("DOWN");
            else if(str.equals("a"))
                game.updateSnake("LEFT");
            else if(str.equals("d"))
                game.updateSnake("RIGHT");

            if(game.isSnakeAlive()){
                game.display();
            } else {
                System.out.println("Your snake is dead :(");
                System.out.println("You scored: " + game.getPoints());
                break;
            }
            str= sc.nextLine();
        }
    }
}