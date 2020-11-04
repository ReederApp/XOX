package com.reederapp.xox.ui;

import android.graphics.drawable.GradientDrawable;
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
    private int i;

    private boolean kullaniciMiBaslayacak;
    private int kullaniciSirasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);
        hazirMi = true;
        textViewResult = findViewById(R.id.skorText);
        textViewTurn = findViewById(R.id.turnOf);
        kullaniciMiBaslayacak = true;
        i = 0;
        kullaniciSirasi = OyunKey.X.getDeger();
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
        butonIslemi = new MatrisIslemleri(new YapayZeka(buttons, this), this);
        butonIslemi.matrisiDoldur(xoxMatrisi);
        onClickIslemleri(xoxMatrisi);
    }

    private void onClickIslemleri(int[][] xoxMatrisi) {
        buttons[0][0].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 0, 0, buttons[0][0]);
        });
        buttons[0][1].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 0, 1, buttons[0][1]);
        });
        buttons[0][2].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 0, 2, buttons[0][2]);
        });
        buttons[1][0].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 1, 0, buttons[1][0]);
        });
        buttons[1][1].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 1, 1, buttons[1][1]);
        });
        buttons[1][2].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 1, 2, buttons[1][2]);
        });
        buttons[2][0].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 2, 0, buttons[2][0]);
        });
        buttons[2][1].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 2, 1, buttons[2][1]);
        });
        buttons[2][2].setOnClickListener(v -> {
            tiklamayaHazir(xoxMatrisi, kullaniciSirasi, 2, 2, buttons[2][2]);
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
            if (!kullaniciMiBaslayacak) {
                butonIslemi.bilgisayarHamlesi(xoxMatrisi, oyunSirasi);
                kullaniciMiBaslayacak = true;
            } else {
                butonIslemi.hamleYap(xoxMatrisi, oyunSirasi, satir, sutun, button);
            }
        }
    }

    private void updatePoints() {
        textViewResult.setText("Player1 " + player2Points + "- " + player1Points + " Player2");
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                GradientDrawable gradientDrawable = (GradientDrawable) buttons[i][j].getBackground();
                gradientDrawable.setColor(getResources().getColor(R.color.purple_500));
                buttons[i][j].setBackground(gradientDrawable);
            }
        }
    }

    private void restartGame() {
        butonIslemi.hamleDurdur();
        butonIslemi.matrisiBosalt(xoxMatrisi);
        YapayZeka.xoxBulunduMu = false;
        this.hazirMi = true;
        updatePoints();
        resetBoard();

        if (!kullaniciMiBaslayacak) {
            kullaniciSirasi = kullaniciSirasi == OyunKey.X.getDeger() ? OyunKey.O.getDeger() : OyunKey.X.getDeger();

            butonIslemi.bilgisayarHamlesi(xoxMatrisi, kullaniciSirasi);
            kullaniciSirasi = kullaniciSirasi == OyunKey.X.getDeger() ? OyunKey.O.getDeger() : OyunKey.X.getDeger();
            kullaniciMiBaslayacak = true;
        }
    }

    @Override
    public void oyunuKazanan(int oyunKey) {
        this.hazirMi = false;
        if (oyunKey == OyunKey.O.getDeger()) {
            player1Points++;
            Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_LONG).show();
        } else if (oyunKey == OyunKey.X.getDeger()) {
            player2Points++;
            Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Game equals!", Toast.LENGTH_LONG).show();
        }
        updatePoints();
        i++;
        if (kullaniciMiBaslayacak) {
            if (i % 2 == 1)
                kullaniciMiBaslayacak = !kullaniciMiBaslayacak;
        }

    }

    private void resetGame() {
        butonIslemi.hamleDurdur();
        butonIslemi.matrisiBosalt(xoxMatrisi);
        YapayZeka.xoxBulunduMu = false;
        this.hazirMi = true;
        player1Points = 0;
        player2Points = 0;
        updatePoints();
        resetBoard();
        if (!kullaniciMiBaslayacak) {
            kullaniciSirasi = kullaniciSirasi == OyunKey.X.getDeger() ? OyunKey.O.getDeger() : OyunKey.X.getDeger();

            butonIslemi.bilgisayarHamlesi(xoxMatrisi, kullaniciSirasi);
            kullaniciSirasi = kullaniciSirasi == OyunKey.X.getDeger() ? OyunKey.O.getDeger() : OyunKey.X.getDeger();
            kullaniciMiBaslayacak = true;
        }
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
    public void btnDegerleriniSetle(int i1, int j1, int i2, int j2, int i3, int j3) {
        GradientDrawable gradientDrawable = (GradientDrawable) buttons[i1][j1].getBackground();
        gradientDrawable.setColor(getResources().getColor(R.color.white));
        buttons[i1][j1].setBackground(gradientDrawable);

        gradientDrawable = (GradientDrawable) buttons[i2][j2].getBackground();
        gradientDrawable.setColor(getResources().getColor(R.color.white));
        buttons[i2][j2].setBackground(gradientDrawable);

        gradientDrawable = (GradientDrawable) buttons[i3][j3].getBackground();
        gradientDrawable.setColor(getResources().getColor(R.color.white));
        buttons[i3][j3].setBackground(gradientDrawable);

    }
}
