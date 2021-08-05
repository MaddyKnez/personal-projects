/**
 * @file: Control.java
 * @Author: Madeline Knez
 * @Date: July 12, 2021
 * @Description:
 */

import java.util.*;
public class Control {
    Game game;

    /**
     * @brief Constructor of Control class
     * @param game A Game object
     */
    public Control(Game game){
        this.game = game;
    }

    /**
     * @brief Calculates the position of the computer's next turn
     * @return An integer array of the spot of the next computer turn
     */
    public int[] nextComputerTurn(){
        int[] spot;
        if(twoInRow(CellType.O) != null){
            spot = twoInRow(CellType.O);
        } else if(twoInRow(CellType.X) != null){
            spot = twoInRow(CellType.X);
        } else if(fork(CellType.O) != null){
            spot = fork(CellType.O);
        } else if(blockFork() != null){
            spot = blockFork();
        } else if(center() != null){
            spot = center();
        } else if(oppositeCorner() != null){
            spot = oppositeCorner();
        } else if(emptyCorner() != null){
            spot = emptyCorner();
        } else {
            spot = randomSpot();
        }
        return spot;
    }

    /**
     * @brief Determine if there is a two in a row to create a three in a row
     * @param x The CellType that will check for two in a row
     * @return An integer array of the spot that will create a three in a row, and null otherwise
     */
    public int[] twoInRow(CellType x){
        if(twoInRowHorizontal(x) != null){
            return twoInRowHorizontal(x);
        }

        if(twoInRowVertical(x) != null){
            return twoInRowVertical(x);
        }

        if(twoInRowDiagonalLeft(x) != null){
            return twoInRowDiagonalLeft(x);
        }

        if(twoInRowDiagonalRight(x) != null){
            return twoInRowDiagonalRight(x);
        }
        return null;
    }

    /**
     * @brief Determine if there is a horizontal two in a row to create a three in a row
     * @param cell The CellType that will check for two in a row
     * @return An integer array of the spot that will create a three in a row horizontally, and null otherwise
     */
    private int[] twoInRowHorizontal(CellType cell){
        CellType[][] board = game.getBoard();
        for(int i = 0; i < 3; i++){ //Two in a row horizontally
            int count = 0;
            int column = 0;
            for(int j = 0; j < 3; j++){
                if(board[i][j] == cell){
                    count++;
                } else{
                    column = j;
                }
            }

            if(count == 2 && board[i][column] == CellType.None){
                return new int[] {i, column};
            }
        }
        return null;
    }

    /**
     * @brief Determine if there is a vertical two in a row to create a three in a row
     * @param cell The CellType that will check for two in a row
     * @return An integer array of the spot that will create a three in a row vertically, and null otherwise
     */
    private int[] twoInRowVertical(CellType cell){
        CellType[][] board = game.getBoard();
        for(int i = 0; i < 3; i++){ //Two in a row vertically
            int count = 0;
            int row = 0;
            for(int j = 0; j < 3; j++){
                if(board[j][i] == cell){
                    count++;
                } else{
                    row = j;
                }
            }

            if(count == 2 && board[row][i] == CellType.None){
                return new int[] {row, i};
            }
        }
        return null;
    }

    /**
     * @brief Determine if there is a left diagonal two in a row to create a three in a row
     * @param cell The CellType that will check for two in a row
     * @return An integer array of the spot that will create a three in a row diagonally to the left,
     * and null otherwise
     */
    private int[] twoInRowDiagonalLeft(CellType cell) {
        CellType[][] board = game.getBoard();
        if (board[0][0] == cell && board[1][1] == cell) { //two in a row diagonally
            if (board[2][2] == CellType.None) {
                return new int[] {2, 2};
            }
        } else if (board[2][2] == cell && board[1][1] == cell) {
            if (board[0][0] == CellType.None) {
                return new int[] {0, 0};
            }
        } else if (board[0][0] == cell && board[2][2] == cell) {
            if (board[1][1] == CellType.None) {
                return new int[] {1, 1};
            }
        }
        return null;
    }

    /**
     * @brief Determine if there is a right diagonal two in a row to create a three in a row
     * @param cell The CellType that will check for two in a row
     * @return An integer array of the spot that will create a three in a row diagonally to the right,
     * and null otherwise
     */
    private int[] twoInRowDiagonalRight(CellType cell){
        CellType[][] board = game.getBoard();
        if(board[0][2] == cell && board[1][1] == cell){
            if(board[2][0] == CellType.None){
                return new int[] {2, 0};
            }
        } else if(board[2][0] == cell && board[1][1] == cell){
            if(board[0][2] == CellType.None){
                return new int[] {0, 2};
            }
        } else if(board[2][0] == cell && board[0][2] == cell){
            if(board[1][1] == CellType.None){
                return new int[] {1, 1};
            }
        }

        return null;
    }


    /**
     * @brief Determine if there is a fork that can be created in the next turn
     * @param cell The CellType that will check for a fork
     * @return An integer array of the spot that will create a fork, and null otherwise
     */
    public int[] fork(CellType cell){
        CellType[][] board = game.getBoard();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == CellType.None && hasManyTwoInRow(cell, i,j)){
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }

    /**
     * @brief Determine if there are many two in a rows
     * @param cell The CellType that will check for many two in a rows
     * @return Returns true if there are many two in a rows, and false otherwise
     */
    private boolean hasManyTwoInRow(CellType cell, int i, int j){
        int countTotal = 0;
        game.setBoard(cell, i, j);

        if(twoInRowHorizontal(cell) != null){
            countTotal++;
        }

        if(twoInRowVertical(cell) != null){
            countTotal++;
        }

        if(twoInRowDiagonalLeft(cell) != null){
            countTotal++;
        }

        if(twoInRowDiagonalRight(cell) != null){
            countTotal++;
        }

        game.setBoard(CellType.None, i, j);
        return countTotal >= 2;
    }

    /**
     * @brief Determine if there is a fork that should be blocked in the next turn
     * @return An integer array of the spot that will block a fork, and null otherwise
     */
    public int[] blockFork(){
        CellType[][] board = game.getBoard();
        ArrayList<int[]> p = new ArrayList<>();

        int[] fork = fork(CellType.X);
        if(fork != null){
            game.setBoard(CellType.X, fork[0], fork[1]);

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++){
                    if(board[i][j] == CellType.None){
                        game.setBoard(CellType.O, i, j);

                        if(twoInRow(CellType.O) != null){
                            p.add(new int[] {i, j});
                        }

                        game.setBoard(CellType.None, i, j);
                    }
                }
            }

            game.setBoard(CellType.None, fork[0], fork[1]);
            if(p.size() == 1){
                return p.get(0);
            } else {
                for(int[] item : p){
                    if(!onSameLine(fork, item)){
                        return item;
                    }
                }
            }
        }
        return null;
    }

    private boolean onSameLine(int[] fork, int[] item){
        if(fork[0] == fork[1] && item[0] == item[1]){
            return true;
        }

        if(fork[0] == 0 && fork[1] == 2 || fork[0] == 2 && fork[1] == 0){
            if(item[0] == 0 && item[1] == 2 || item[0] == 2 && item[1] == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Determine if the center of the board is filled
     * @return An integer array of the center of the board, and null if the center has already been filled
     */
    public int[] center(){
        CellType[][] board = game.getBoard();

        for(CellType[] row : board){
            for(CellType item : row){
                if(item != CellType.None){
                    if(board[1][1] == CellType.None){
                        return new int[] {1, 1};
                    }
                }
            }
        }
        return null;
    }

    /**
     * @brief Determine if there is an opposite corner to be filled
     * @return An integer array of the spot that is the opposite corner, and null otherwise
     */
    public int[] oppositeCorner(){
        CellType[][] board = game.getBoard();

        if(board[0][0] == CellType.X && board[2][2] == CellType.None){
            return new int[] {2, 2};
        } else if(board[2][2] == CellType.X && board[0][0] == CellType.None){
            return new int[] {0, 0};
        } else if(board[0][2] == CellType.X && board[2][0] == CellType.None){
            return new int[] {2, 0};
        } else if(board[2][0] == CellType.X && board[0][2] == CellType.None){
            return new int[] {0, 2};
        }

        return null;
    }

    /**
     * @brief Determine if there is an empty corner on the board
     * @return An integer array of the spot that fills an empty corner, and null otherwise
     */
    public int[] emptyCorner(){
        CellType[][] board = game.getBoard();

        if(board[0][0] == CellType.None){
            return new int[] {0, 0};
        } else if(board[2][2] == CellType.None){
            return new int[] {2, 2};
        } else if(board[0][2] == CellType.None){
            return new int[] {0, 2};
        } else if(board[2][0] == CellType.None){
            return new int[] {2, 0};
        }

        return null;
    }

    /**
     * @brief Determine a random spot on the board that is not yet filled
     * @return An integer array of a spot on the board that has been randomly chosen
     */
    public int[] randomSpot(){
        CellType[][] board = game.getBoard();
        ArrayList<int[]> randomPositions = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == CellType.None){
                    int[] position = {i, j};
                    randomPositions.add(position);
                }
            }
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(randomPositions.size());
        return randomPositions.get(randomNum);
    }
}
