package com.reederapp.xox.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.reederapp.xox.OyunIslemleri.MatrisIslemleri;
import com.reederapp.xox.OyunIslemleri.YapayZeka;
import com.reederapp.xox.R;
import com.reederapp.xox.enums.OyunKey;
import com.reederapp.xox.interfaces.OyunInterfaces;

public class GameActivity extends AppCompatActivity implements OyunInterfaces {
    private final Button[][] buttons = new Button[3][3];
    private int player1Points;      // Player1 points
    private int player2Points;   //Player2 points

    private TextView textViewResult;
    private TextView textViewTurn;
    private MatrisIslemleri butonIslemi;
    private int[][] xoxMatrisi;
    private boolean hazirMi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);
        hazirMi = true;
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
        restartGame();
    }

    private void init() {
        xoxMatrisi = new int[3][3];
        butonIslemi = new MatrisIslemleri(new YapayZeka(buttons), this);
        butonIslemi.matrisiDoldur(xoxMatrisi);
        onClickIslemleri(xoxMatrisi);
    }

    private void onClickIslemleri(int[][] xoxMatrisi) {
        buttons[0][0].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 0, 0, buttons[0][0]);
        });
        buttons[0][1].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 0, 1, buttons[0][1]);
        });
        buttons[0][2].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 0, 2, buttons[0][2]);
        });
        buttons[1][0].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 1, 0, buttons[1][0]);
        });
        buttons[1][1].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 1, 1, buttons[1][1]);
        });
        buttons[1][2].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 1, 2, buttons[1][2]);
        });
        buttons[2][0].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 2, 0, buttons[2][0]);
        });
        buttons[2][1].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 2, 1, buttons[2][1]);
        });
        buttons[2][2].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, OyunKey.X.getDeger(), 2, 2, buttons[2][2]);
        });
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnPlayAgain.setOnClickListener(view -> {
            restartGame();
        });

        Button btnResetGame = findViewById(R.id.btnResetGame);
        btnResetGame.setOnClickListener(view -> {
            resetGame();
        });
    }

    private void tiklamayaHazir(int[][] xoxMatrisi, int oyunSirasi, int satir, int sutun, Button button) {
        if (this.hazirMi) {
            butonIslemi.hamleYap(xoxMatrisi, oyunSirasi, satir, sutun, button);
        }
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
    }

    private void restartGame() {
        butonIslemi.hamleDurdur();
        butonIslemi.matrisiBosalt(xoxMatrisi);
        YapayZeka.xoxBulunduMu = false;
        updatePoints();
        resetBoard();
    }

    private void resetGame() {
        butonIslemi.hamleDurdur();
        butonIslemi.matrisiBosalt(xoxMatrisi);
        YapayZeka.xoxBulunduMu = false;
        player1Points = 0;
        player2Points = 0;
        updatePoints();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) { //for rotate
        super.onSaveInstanceState(outState);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) { //for rotate
        super.onRestoreInstanceState(savedInstanceState);

        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
    }

    @Override
    public void oyunSirasi(String sira) {
        textViewTurn.setText("TURN OF: " + sira);
    }

    @Override
    public void tiklamaKontrolu(boolean hazirMi) {
        this.hazirMi = hazirMi;
    }

    @Override
    public void oyunuKazanan(int oyunKey) {
        if (oyunKey == OyunKey.O.getDeger()) {
            player1Points++;
            Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_LONG).show();
        } else if (oyunKey == OyunKey.X.getDeger()) {
            player2Points++;
            Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Game equals!", Toast.LENGTH_LONG).show();
        }
        updatePoints();
    }
}
