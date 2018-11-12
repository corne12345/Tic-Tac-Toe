package com.example.corne.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            game = new Game();
        }
        else{
            game = (Game) savedInstanceState.getSerializable("gameState");

            String state_tile = (String) savedInstanceState.getSerializable("button1");
            TextView text_tile = findViewById(R.id.button1);
            text_tile.setText(state_tile);
            state_tile = (String) savedInstanceState.getSerializable("button2");
            text_tile = findViewById(R.id.button2);
            text_tile.setText(state_tile);
            state_tile = (String) savedInstanceState.getSerializable("button3");
            text_tile = findViewById(R.id.button3);
            text_tile.setText(state_tile);
            state_tile = (String) savedInstanceState.getSerializable("button4");
            text_tile = findViewById(R.id.button4);
            text_tile.setText(state_tile);
            state_tile = (String) savedInstanceState.getSerializable("button5");
            text_tile = findViewById(R.id.button5);
            text_tile.setText(state_tile);
            state_tile = (String) savedInstanceState.getSerializable("button6");
            text_tile = findViewById(R.id.button6);
            text_tile.setText(state_tile);
            state_tile = (String) savedInstanceState.getSerializable("button7");
            text_tile = findViewById(R.id.button7);
            text_tile.setText(state_tile);
            state_tile = (String) savedInstanceState.getSerializable("button8");
            text_tile = findViewById(R.id.button8);
            text_tile.setText(state_tile);
            state_tile = (String) savedInstanceState.getSerializable("button9");
            text_tile = findViewById(R.id.button9);
            text_tile.setText(state_tile);
        }

    }

    // Reset the game when the reset button is pressed
    public void resetClicked(View view) {
        game = new Game();
        setContentView(R.layout.activity_main);
    }

    public void tileClicked(View view) {
        int id = view.getId();
        int row = 0;
        int column = 0;
        switch (id){
            case R.id.button1:
                row = 0;
                column = 0;
                break;
            case R.id.button2:
                row = 0;
                column = 1;
                break;
            case R.id.button3:
                row = 0;
                column = 2;
                break;
            case R.id.button4:
                row = 1;
                column = 0;
                break;
            case R.id.button5:
                row = 1;
                column = 1;
                break;
            case R.id.button6:
                row = 1;
                column = 2;
                break;
            case R.id.button7:
                row = 2;
                column = 0;
                break;
            case R.id.button8:
                row = 2;
                column = 1;
                break;
            case R.id.button9:
                row = 2;
                column = 2;
                break;
        }
        TileState state = game.choose(row, column);
        switch(state){
            case CROSS:
                TextView button = findViewById(id);
                button.setText("X");
                break;
            case CIRCLE:
                button = findViewById(id);
                button.setText("O");
                break;
            case INVALID:
                break;
        }
        GameState check = game.won();
        
        if (check == GameState.PLAYER_ONE || check == GameState.PLAYER_TWO){
            Button button1 = findViewById(R.id.button1);
            button1.setEnabled(false);
            Button button2 = findViewById(R.id.button2);
            button2.setEnabled(false);
            Button button3 = findViewById(R.id.button3);
            button3.setEnabled(false);
            Button button4 = findViewById(R.id.button4);
            button4.setEnabled(false);
            Button button5 = findViewById(R.id.button5);
            button5.setEnabled(false);
            Button button6 = findViewById(R.id.button6);
            button6.setEnabled(false);
            Button button7 = findViewById(R.id.button7);
            button7.setEnabled(false);
            Button button8 = findViewById(R.id.button8);
            button8.setEnabled(false);
            Button button9 = findViewById(R.id.button9);
            button9.setEnabled(false);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView state1 = findViewById(R.id.button1);
        String save1 = state1.getText().toString();
        outState.putSerializable("button1", save1);
        TextView state2 = findViewById(R.id.button2);
        String save2 = state2.getText().toString();
        outState.putSerializable("button2", save2);
        TextView state3 = findViewById(R.id.button3);
        String save3 = state3.getText().toString();
        outState.putSerializable("button3", save3);
        TextView state4 = findViewById(R.id.button4);
        String save4 = state4.getText().toString();
        outState.putSerializable("button4", save4);
        TextView state5 = findViewById(R.id.button5);
        String save5 = state5.getText().toString();
        outState.putSerializable("button5", save5);
        TextView state6 = findViewById(R.id.button6);
        String save6 = state6.getText().toString();
        outState.putSerializable("button6", save6);
        TextView state7 = findViewById(R.id.button7);
        String save7 = state7.getText().toString();
        outState.putSerializable("button7", save7);
        TextView state8 = findViewById(R.id.button8);
        String save8 = state8.getText().toString();
        outState.putSerializable("button8", save8);
        TextView state9 = findViewById(R.id.button9);
        String save9 = state9.getText().toString();
        outState.putSerializable("button9", save9);

        outState.putSerializable("gameState", game);
    }

}
