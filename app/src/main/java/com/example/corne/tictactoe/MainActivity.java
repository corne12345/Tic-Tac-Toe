package com.example.corne.tictactoe;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            mGame = new Game();
        }
        else {
            mGame = (Game) savedInstanceState.getSerializable("gameState");
            for (int i = 0; i < 9; i++) {
                String buttonString = "button" + i;
                int loopID = getResources().getIdentifier(buttonString, "id", getPackageName());
                Button button = findViewById(loopID);
                String tileState = (String) savedInstanceState.getSerializable(buttonString);
                if (tileState.equals("x")) {
                    button.setBackgroundResource(R.drawable.x);
                } else if (tileState.equals("o")) {
                    button.setBackgroundResource(R.drawable.o);
                }
            }
        }
    }

    public void tileClicked (View view){
        int id = view.getId();
        int row = 0;
        int column = 0;
        for (int i = 0; i < 9; i++){
            String button = "button"+i;
            int loopID = getResources().getIdentifier(button, "id", getPackageName());
            if (loopID == id){
                row = i/3;
                column = i % 3;
            }
        }
        TileState state = mGame.choose(row, column);
        Button button = findViewById(id);

        switch(state){
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
        GameState check = mGame.won();
        disableButtons(check);
    }

    public void disableButtons(GameState check) {
        if (check == GameState.PLAYER_ONE){
            Toast.makeText(this, "Player one won", Toast.LENGTH_LONG).show();
        }
        else if (check == GameState.PLAYER_TWO){
            Toast.makeText(this, "Player two won", Toast.LENGTH_LONG).show();
        }
        if (check != GameState.IN_PROGRESS){
            for (int i = 0; i < 9; i ++){
                String buttonString = "button"+i;
                int loopID = getResources().getIdentifier(buttonString, "id", getPackageName());
                Button button = findViewById(loopID);
                button.setEnabled(false);
            }
        }
    }

    public void resetClicked(View view){
        mGame = new Game();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        for (int i = 0; i < 9; i ++) {
            String buttonString = "button" + i;
            int loopID = getResources().getIdentifier(buttonString, "id", getPackageName());
            Button button = findViewById(loopID);
            String save = button.getText().toString();
            Log.e("TESTTEST", save);
            outState.putSerializable(buttonString, save);
        }
        outState.putSerializable("gameState", mGame);
    }
}
