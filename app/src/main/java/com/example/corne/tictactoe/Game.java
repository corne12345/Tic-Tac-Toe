package com.example.corne.tictactoe;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState [][] board;

    private Boolean playerOneTurn;
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }
    public TileState choose(int row, int column) {
        TileState currentBox = board[row][column];
        if (currentBox == TileState.BLANK) {
            if (playerOneTurn == true) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return TileState.CROSS;
            } else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                return TileState.CIRCLE;
            }
        } else {
            return TileState.INVALID;
        }
    }
    public GameState won() {
        if (board[0][0] == TileState.CROSS & board[0][1] == TileState.CROSS & board[0][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[1][0] == TileState.CROSS & board[1][1] == TileState.CROSS & board[1][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[2][0] == TileState.CROSS & board[2][1] == TileState.CROSS & board[2][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[0][0] == TileState.CROSS & board[1][0] == TileState.CROSS & board[2][0] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[0][1] == TileState.CROSS & board[1][1] == TileState.CROSS & board[2][1] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[0][2] == TileState.CROSS & board[1][2] == TileState.CROSS & board[2][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[0][0] == TileState.CROSS & board[1][1] == TileState.CROSS & board[2][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[0][2] == TileState.CROSS & board[1][1] == TileState.CROSS & board[2][0] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        // Check if player two wins
        else if (board[0][0] == TileState.CIRCLE & board[0][1] == TileState.CIRCLE & board[0][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[1][0] == TileState.CIRCLE & board[1][1] == TileState.CIRCLE & board[1][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[2][0] == TileState.CIRCLE & board[2][1] == TileState.CIRCLE & board[2][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[0][0] == TileState.CIRCLE & board[1][0] == TileState.CIRCLE & board[2][0] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[0][1] == TileState.CIRCLE & board[1][1] == TileState.CIRCLE & board[2][1] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[0][2] == TileState.CIRCLE & board[1][2] == TileState.CIRCLE & board[2][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[0][0] == TileState.CIRCLE & board[1][1] == TileState.CIRCLE & board[2][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[0][2] == TileState.CIRCLE & board[1][1] == TileState.CIRCLE & board[2][0] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }

        return GameState.IN_PROGRESS;
    }
}
