import java.util.*;
public class Game {
    private int height;
    private int width;
    private int[][] board;
    private Snake snake;
    private Apple apple;
    private boolean alive;
    private int points;

    public Game(int height, int width){
        this.height = height;
        this.width = width;
        this.board = initialBoardState();

        snake = new Snake(board, "UP");

        apple = new Apple();
        apple.setApple(board, snake.getBody());

        alive = true;
        points = 0;
    }

    public int[][] initialBoardState(){
        int[][] board = new int[height][width];
        for(int[] row: board){
            for(int i = 0; i < width; i++){
                row[i] = 0;
            }
        }
        return board;
    }

    public void updateSnake(String direction){
        snake.setDirection(direction);

        int[] head = snake.snakeHead();
        int[] newPosition = null;
        if(direction.equals("UP")){
            if(head[0] - 1 >= 0) {
                newPosition = new int[] {head[0] - 1, head[1]};
            } else {
                newPosition = new int[] {height - 1, head[1]};
            }
        } else if(direction.equals("DOWN")){
            if(head[0] + 1 < height) {
                newPosition = new int[] {head[0] + 1, head[1]};
            } else {
                newPosition = new int[] {0, head[1]};
            }
        } else if(direction.equals("LEFT")){
            if (head[1] - 1 >= 0){
                newPosition = new int[] {head[0], head[1] - 1};
            } else {
                newPosition = new int[] {head[0], width - 1};
            }
        } else if(direction.equals("RIGHT")){
            if (head[1] + 1 < width){
                newPosition = new int[] {head[0], head[1] + 1};
            } else {
                newPosition = new int[] {head[0], 0};
            }
        }
        if(snake.hasPosition(newPosition))
            alive = false;
        snake.takeStep(newPosition);
    }

    public boolean isSnakeAlive(){
        return alive;
    }

    public void updateBoard(){
        resetBoard();

        int[][] positions = snake.getBody();

        for(int i = 1; i < positions.length; i++){
            int height = positions[i][0];
            int width = positions[i][1];
            board[height][width] = 1;
        }

        board[apple.getRow()][apple.getColumn()] = 3;
        board[positions[0][0]][positions[0][1]] = 2;
    }

    private void resetBoard(){
        for(int[] row: board){
            for(int i = 0; i < width; i++){
                row[i] = 0;
            }
        }
    }

    public boolean isAppleEaten(){
        return snake.snakeHead()[0] == apple.getRow() && snake.snakeHead()[1] == apple.getColumn();
    }

    public int getPoints(){
        return points;
    }

    public void display(){
        updateBoard();

        if(isAppleEaten()){
            apple.setApple(board, snake.getBody());
            snake.addLink();
            points++;
        }

        System.out.print("+");
        for(int i = 0; i < width; i++){
            System.out.print("-");
        }
        System.out.println("+");

        for(int[] row: board){
            System.out.print("|");
            for(int item: row){
                if(item == 2)
                    System.out.print("X");
                else if(item == 1)
                    System.out.print("O");
                else if(item == 4)
                    System.out.print("-");
                else if(item == 3)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println("|");
        }

        System.out.print("+");
        for(int i = 0; i < width; i++){
            System.out.print("-");
        }
        System.out.println("+");
    }
}
