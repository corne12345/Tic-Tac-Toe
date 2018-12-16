package com.example.corne.tictactoe;


import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private Boolean playerOneTurn;
    private int movesPlayed;
    private Boolean gameOver;

    // Create instance of Game
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {

        // Check if current block is empty and fill it, while updating moves
        TileState current = board[row][column];
        if(current == TileState.BLANK){
            if (playerOneTurn){
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                movesPlayed +=1;
                return TileState.CROSS;
            } else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                movesPlayed +=1;
                return TileState.CIRCLE;
            }
        } else {
            return TileState.INVALID;
        }
    }

    public GameState won(){

        // Check for a draw
        if (movesPlayed == 9) {
            return GameState.DRAW;
        }

        // Check for diagonals (hard coded)
        else if (board[0][0] == TileState.CROSS & board[1][1] == TileState.CROSS & board[2][2] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[0][2] == TileState.CROSS & board[1][1] == TileState.CROSS & board[2][0] == TileState.CROSS){
            return GameState.PLAYER_ONE;
        }
        else if (board[0][0] == TileState.CIRCLE & board[1][1] == TileState.CIRCLE & board[2][2] == TileState.CIRCLE){
            return GameState.PLAYER_TWO;
        }
        else if (board[0][2] == TileState.CIRCLE & board[1][1] == TileState.CIRCLE & board[2][0] == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }

        // check for horizontal or vertical uniform rows and return correct winner
        for (int i = 0; i < BOARD_SIZE; i++) {
            int counterP1H = 0;
            int counterP2H = 0;
            int counterP1V = 0;
            int counterP2V = 0;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == TileState.CROSS) {
                    counterP1H += 1;
                }
                else if (board[i][j] == TileState.CIRCLE){
                    counterP2H += 1;
                }
                if(board[j][i] == TileState.CROSS){
                    counterP1V += 1;
                }
                else if(board[j][i] == TileState.CIRCLE){
                    counterP2V += 1;
                }
            }

            // Return win of player 1 if a whole row or column is X
            if (counterP1H == BOARD_SIZE || counterP1V == BOARD_SIZE){
                return GameState.PLAYER_ONE;
            }

            // Return win of player 2 if a whole row or column is O
            else if (counterP2H == BOARD_SIZE || counterP2V == BOARD_SIZE){
                return GameState.PLAYER_TWO;
            }
        }

        return GameState.IN_PROGRESS;
    }

    // Getter for the turn (needed in visual for turns)
    public Boolean getPlayerOneTurn() {
        return playerOneTurn;
    }
}
