package com.reederapp.xox;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.reederapp.xox.OyunIslemleri.ButonIslemleri;
import com.reederapp.xox.OyunIslemleri.YapayZeka;
import com.reederapp.xox.enums.OyunKey;


public class MainActivity extends AppCompatActivity {

    private Button[][] buttons;
    private ButonIslemleri butonIslemi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        buttons = new Button[3][3];
        int[][] xoxMatrisi = new int[3][3];
        YapayZeka zeka = new YapayZeka(buttons, this.getApplicationContext());
        butonIslemi = new ButonIslemleri(zeka, this.getApplicationContext());
        butonIslemi.matrisiDoldur(xoxMatrisi);
        onClickIslemleri(xoxMatrisi);
    }

    private void onClickIslemleri(int[][] xoxMatrisi) {
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnClear = findViewById(R.id.btnClr);
        buttons[0][0] = btn1;
        buttons[0][1] = btn2;
        buttons[0][2] = btn3;
        buttons[1][0] = btn4;
        buttons[1][1] = btn5;
        buttons[1][2] = btn6;
        buttons[2][0] = btn7;
        buttons[2][1] = btn8;
        buttons[2][2] = btn9;


        btn1.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 0, 0, btn1);
        });
        btn2.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 0, 1, btn2);
        });
        btn3.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 0, 2, btn3);
        });
        btn4.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 1, 0, btn4);
        });
        btn5.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 1, 1, btn5);
        });
        btn6.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 1, 2, btn6);
        });
        btn7.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 2, 0, btn7);
        });
        btn8.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 2, 1, btn8);
        });
        btn9.setOnClickListener(v -> {
            butonIslemi.oyunuBaslat(xoxMatrisi, OyunKey.X.getDeger(), 2, 2, btn9);
        });
        btnClear.setOnClickListener(v -> {
            butonIslemi.matrisiBosalt(xoxMatrisi, buttons);
            YapayZeka.xoxBulunduMu = false;
        });
    }


}