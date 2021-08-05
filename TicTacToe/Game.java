/**
 * @file: Game.java
 * @Author: Madeline Knez
 * @Date: July 12, 2021
 * @Description: Sets up the initial board, controls information on positions of the Xs and Os on the board,
 * and determines if there is a winner
 */

public class Game {
    private CellType[][] board = new CellType[3][3];
    private boolean nextPlayerTurn; //True = X, False = O

    /**
     * @brief Constructor of Game class
     * @details Creates an empty board
     * @param startPlayer A boolean value that indicates if the computer of user will start
     */
    public Game(boolean startPlayer){
        nextPlayerTurn = startPlayer;

        for(CellType[] row : board){
            for(int i = 0; i< board.length; i++){
                row[i] = CellType.None;
            }
        }
    }

    /**
     * @brief Gets the next player that has a turn
     * @return The next player's uses turn it is
     */
    public boolean getNextPlayerTurn(){
        return nextPlayerTurn;
    }

    /**
     * @brief Gets the board
     * @return The board of the current Tic Tac Toe game
     */
    public CellType[][] getBoard(){
        return board;
    }

    /**
     * @brief Gets the next player that has a turn
     * @param cell A CellType that will be set in the specified position on the board
     * @param row An integer that represents the row that is being set
     * @param column An integer that represents the column that is being set
     */
    public void setBoard(CellType cell, int row, int column){
        board[row][column] = cell;
    }

    /**
     * @brief Set the position of the next turn
     * @param row An integer that represents the row that is being changed for the next turn
     * @param column An integer that represents the column that is being changed for the next turn
     */
    public void nextTurn(int row, int column){
        if(nextPlayerTurn){
            board[row][column] = CellType.X;
        } else {
            board[row][column] = CellType.O;
        }
        nextPlayerTurn = !nextPlayerTurn;
    }

    /**
     * @brief Determine if there is a winner of the game
     * @return Returns the winner of the game: cellType X if Xs have won, cellType O if Os have won, and cellType
     * none if there is not a winner
     */
    public CellType isWinner(){
        if(isHorizontalWin(CellType.X) || isVerticalWin(CellType.X) || isDiagonalWin(CellType.X)){
            return CellType.X;
        }

        if(isHorizontalWin(CellType.O) || isVerticalWin(CellType.O) || isDiagonalWin(CellType.O)){
            return CellType.O;
        }

        return CellType.None;
    }

    /**
     * @brief Determine if the game is over
     * @return Returns true if there is a winner or there is a tie and false otherwise
     */
    public boolean isGameOver(){
        return isWinner() == CellType.X || isWinner() == CellType.O || isBoardFull();
    }

    /**
     * @brief Determine if there is a winner of the game
     * @param x The cellType that should be checked if there is three in a row horizontally
     * @return Returns true if there is a horizontal three in a row, and false otherwise
     */
    private boolean isHorizontalWin(CellType x){
        for(CellType[] row : board){
            int count = 0;
            for(CellType item : row){
                if(item == x){
                    count++;
                }
            }

            if(count == 3){
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Determine if there is a winner of the game
     * @param x The cellType that should be checked if there is three in a row vertically
     * @return Returns true if there is a vertical three in a row, and false otherwise
     */
    private boolean isVerticalWin(CellType x){
        for(int row = 0; row < board.length; row++){
            int count = 0;
            for (CellType[] cellTypes : board) {
                if (cellTypes[row] == x) {
                    count++;
                }
            }

            if(count == 3){
                return true;
            }
        }

        return false;
    }

    /**
     * @brief Determine if there is a winner of the game
     * @param x The cellType that should be checked if there is three in a row diagonally
     * @return Returns true if there is a diagonal three in a row, and false otherwise
     */
    private boolean isDiagonalWin(CellType x){
        if(board[0][0] == x && board[1][1] == x && board[2][2] == x){
            return true;
        }

        return board[2][0] == x && board[1][1] == x && board[0][2] == x;
    }

    /**
     * @brief Determine if the board is full
     * @return Returns true if the board has no more empty spaces, and false if there are still empty spaces
     */
    private boolean isBoardFull(){
        for(CellType[] row : board){
            for(CellType item : row){
                if(item == CellType.None){
                    return false;
                }
            }
        }
        return true;
    }
}
