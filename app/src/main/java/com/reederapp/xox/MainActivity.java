package com.reederapp.xox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;     //x for player1
    private int count;    //9 for field

    private int player1Points;      // Player1 points
    private int player2Points;   //Player2 points

    private TextView textViewResult;
    private TextView textViewTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textViewResult = findViewById(R.id.tvResult);
        //textViewTurn = findViewById(R.id.tvTurn);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        //Button buttonPlayAgain = findViewById(R.id.btnAgain);
        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) { //is player1Turn
            ((Button) view).setText("X");
            textViewTurn.setText("TURN OF: X");

        } else {
            ((Button) view).setText("O");
            textViewTurn.setText("TURN OF: O");
        }
        count++;

        if (checkWin()) {
            if (player1Turn) {
                player1Points++;
                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_LONG).show();
            } else {
                player2Points++;
                Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_LONG).show();
            }
            updatePoints();
            resetBoard();

        } else if (count == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
            resetBoard();

        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][1].equals(field[i][2]) && !field[i][0].equals("")) {   //rows check
                return true;
            } else if (field[0][i].equals(field[1][i]) && field[1][i].equals(field[2][i]) && !field[0][i].equals("")) {   //columns check
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) { //cross (left to right)
            return true;
        } else if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) { //cross (right to left)
            return true;
        }
        return false;
    }

    private void updatePoints() {
        textViewResult.setText("Player1 " + player1Points + "- " + player2Points + " Player2");

    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        count = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePoints();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) { //for rotate
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) { //for rotate
        super.onRestoreInstanceState(savedInstanceState);

        count = savedInstanceState.getInt("count");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}