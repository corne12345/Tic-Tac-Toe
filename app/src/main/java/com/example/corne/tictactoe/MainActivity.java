package com.example.corne.tictactoe;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check for saved instance (after turning)
        if (savedInstanceState == null){
            mGame = new Game();
        }

        // Load game if present
        else {

            // Get all serializable saved instances
            mGame = (Game) savedInstanceState.getSerializable("gameState");

            // Loop over all the buttons and connect save to the button
            for (int i = 0; i < 9; i++) {
                String buttonString = "button" + i;
                int loopID = getResources().getIdentifier(buttonString, "id", getPackageName());
                Button button = findViewById(loopID);
                String tileState = (String) savedInstanceState.getSerializable(buttonString);

                // Set button image based on "invisible text"
                if (tileState.equals("x")) {
                    button.setBackgroundResource(R.drawable.x);
                } else if (tileState.equals("o")) {
                    button.setBackgroundResource(R.drawable.o);
                }
            }
        }
    }

    public void tileClicked (View view){

        // Get ID of clicked button and compare it with all 9 buttons
        int id = view.getId();
        int row = 0;
        int column = 0;
        for (int i = 0; i < 9; i++){
            String button = "button"+i;
            int loopID = getResources().getIdentifier(button, "id", getPackageName());

            // If a match is found, update the row and column
            if (loopID == id){
                row = i/3;
                column = i % 3;
            }
        }

        // Check for a valid move, update symbol and "invisible" text
        TileState state = mGame.choose(row, column);
        Button button = findViewById(id);
        switch(state) {
            case CROSS:
                button.setBackgroundResource(R.drawable.x);
                button.setText("x");
                break;
            case CIRCLE:
                button.setBackgroundResource(R.drawable.o);
                button.setText("o");
                break;
            case INVALID:
                break;
        }

        // Update text presenting turn
        Boolean turn = mGame.getPlayerOneTurn();
        String symbol;
        if (turn == true){
            symbol = "x";
        } else
            {symbol = "o";
        }
        TextView turnText = findViewById(R.id.turnView);
        String text = "It is " + symbol + "'s turn";
        turnText.setText(text);

        // Check if game is finished and disable buttons if so
        GameState check = mGame.won();
        disableButtons(check);
    }

    public void disableButtons(GameState check) {

        // Show toast if either player has won or when drawn
        if (check == GameState.PLAYER_ONE){
            Toast.makeText(this, "Player one won", Toast.LENGTH_LONG).show();
        }
        else if (check == GameState.PLAYER_TWO){
            Toast.makeText(this, "Player two won", Toast.LENGTH_LONG).show();
        }
        else if (check == GameState.DRAW){
            Toast.makeText(this, "Draw", Toast.LENGTH_LONG).show();
        }

        // Disable all buttons and make them red if the game is over
        if (check != GameState.IN_PROGRESS){
            for (int i = 0; i < 9; i ++){
                String buttonString = "button"+i;
                int loopID = getResources().getIdentifier(buttonString, "id", getPackageName());
                Button button = findViewById(loopID);
                button.setEnabled(false);
                button.setBackgroundColor(0xFFFF0000);
            }
        }
    }

    // Reset game and view
    public void resetClicked(View view){
        mGame = new Game();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        // Loop over all buttons and save the button and corresponding text
        for (int i = 0; i < 9; i ++) {
            String buttonString = "button" + i;
            int loopID = getResources().getIdentifier(buttonString, "id", getPackageName());
            Button button = findViewById(loopID);
            String save = button.getText().toString();
            outState.putSerializable(buttonString, save);
        }
        outState.putSerializable("gameState", mGame);
    }
}
