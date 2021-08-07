import java.util.*;
public class Apple {
    private ArrayList<int[]> possiblePositions;
    private int row;
    private int column;

    public Apple(){
        possiblePositions = new ArrayList<>();
    }

    public void setAppleMaze(int height, int width){
        row = height - 1;
        column = width - 1;
    }

    public void setApple(int[][] board, int[][] snake){
        Random rand = new Random();
        getPossiblePositions(board, snake);

        int applePosition = rand.nextInt(possiblePositions.size());
        row = possiblePositions.get(applePosition)[0];
        column = possiblePositions.get(applePosition)[1];
    }

    private void getPossiblePositions(int[][] board, int[][] snake){
        possiblePositions = new ArrayList<>();
        for(int row = 0; row < board.length; row++){
            for(int column = 0; column < board[0].length; column++){
                int[] item = new int[] {row, column};
                possiblePositions.add(item);

                for(int[] s : snake){
                    if(item[0] == s[0] && item[1] == s[1]){
                        possiblePositions.remove(item);
                    }
                }
            }
        }
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }
}
