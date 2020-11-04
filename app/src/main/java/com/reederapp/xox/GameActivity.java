package com.reederapp.xox;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.reederapp.xox.OyunIslemleri.MatrisIslemleri;
import com.reederapp.xox.OyunIslemleri.YapayZeka;
import com.reederapp.xox.enums.OyunKey;

public class GameActivity extends AppCompatActivity {
    private final Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;     //x for player1
    private int count;    //9 for field

    private int player1Points;      // Player1 points
    private int player2Points;   //Player2 points

    private TextView textViewResult;
    private TextView textViewTurn;
    private MatrisIslemleri butonIslemi;
    private int[][] xoxMatrisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        textViewResult = findViewById(R.id.skorText);
        textViewTurn = findViewById(R.id.turnOf);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
            }
        }
        init();
        Button buttonPlayAgain = findViewById(R.id.btnPlayAgain);
        buttonPlayAgain.setOnClickListener(view -> {
            resetGame();
            butonIslemi.matrisiBosalt(xoxMatrisi, buttons);
            YapayZeka.xoxBulunduMu = false;
        });
    }

    private void init() {
        xoxMatrisi = new int[3][3];
        butonIslemi = new MatrisIslemleri(new YapayZeka(buttons), getApplicationContext());
        butonIslemi.matrisiDoldur(xoxMatrisi);
        onClickIslemleri(xoxMatrisi);
    }

    private void onClickIslemleri(int[][] xoxMatrisi) {
        buttons[0][0].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 0, 0, buttons[0][0]);
        });
        buttons[0][1].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 0, 1, buttons[0][1]);
        });
        buttons[0][2].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 0, 2, buttons[0][2]);
        });
        buttons[1][0].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 1, 0, buttons[1][0]);
        });
        buttons[1][1].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 1, 1, buttons[1][1]);
        });
        buttons[1][2].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 1, 2, buttons[1][2]);
        });
        buttons[2][0].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 2, 0, buttons[2][0]);
        });
        buttons[2][1].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 2, 1, buttons[2][1]);
        });
        buttons[2][2].setOnClickListener(v -> {
            butonIslemi.hamleYap(xoxMatrisi, OyunKey.X.getDeger(), 2, 2, buttons[2][2]);
        });
    }
//
//    @Override
//    public void onClick(View view) {
//        if (!((Button) view).getText().toString().equals("")) {
//            return;
//        }
//        if (player1Turn) { //is player1Turn
//            ((Button) view).setText("X");
//            textViewTurn.setText("TURN OF: X");
//
//        } else {
//            ((Button) view).setText("O");
//            textViewTurn.setText("TURN OF: O");
//        }
//        count++;
//
//        if (checkWin()) {
//            if (player1Turn) {
//                player1Points++;
//                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_LONG).show();
//            } else {
//                player2Points++;
//                Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_LONG).show();
//            }
//            updatePoints();
//            resetBoard();
//
//        } else if (count == 9) {
//            Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
//            resetBoard();
//
//        } else {
//            player1Turn = !player1Turn;
//        }
//    }

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
