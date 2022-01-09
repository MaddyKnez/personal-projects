import java.util.ArrayList;
import java.util.Random;

public class Snake {
    private ArrayList<int[]> body;
    private String direction;

    public Snake(int[][] board, String initDirection){
        setInitBody(board);

        direction = initDirection;
    }

    public void setInitBody(int[][] board){
        Random rand = new Random();
        body = new ArrayList<>();

        int row = rand.nextInt(board.length);
        int column = rand.nextInt(board[0].length);
        body.add(new int[] {row, column});

        if(row - 1 >= 0){
            body.add(new int[] {row - 1, column});
        } else if(row + 1 < board.length){
            body.add(new int[] {row + 1, column});
        } else if(column - 1 >= 0){
            body.add(new int[] {row, column - 1});
        } else if(column + 1 < board.length){
            body.add(new int[] {row, column + 1});
        }
    }

    public void takeStep(int[] position){
        for(int i = body.size() - 1; i > 0; i--){
            body.set(i, body.get(i - 1));
        }
        body.set(0, position);
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    public int[] snakeHead(){
        return body.get(0);
    }

    public void addLink(){
        if(direction.equals("UP")){
            int[] link = new int[] {body.get(body.size() - 1)[0] - 1, body.get(body.size() - 1)[1]};
            body.add(link);
        } else if(direction.equals("DOWN")){
            int[] link = new int[] {body.get(body.size() - 1)[0] + 1, body.get(body.size() - 1)[1]};
            body.add(link);
        } else if(direction.equals("LEFT")){
            int[] link = new int[] {body.get(body.size() - 1)[0], body.get(body.size() - 1)[1] - 1};
            body.add(link);
        } else if(direction.equals("RIGHT")){
            int[] link = new int[] {body.get(body.size() - 1)[0], body.get(body.size() - 1)[1] + 1};
            body.add(link);
        }
    }

    public boolean hasPosition(int[] position){
        for(int[] row: body){
            if(row[0] == position[0] && row[1] == position[1]){
                return true;
            }
        }
        return false;
    }

    public int[][] getBody(){
        int[][] bodyArray = new int[body.size()][2];

        int counter = 0;
        for(int[] link : body){
            bodyArray[counter] = link;
            counter++;
        }
        return bodyArray;
    }

}
