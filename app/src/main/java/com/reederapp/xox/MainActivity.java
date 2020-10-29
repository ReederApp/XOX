package com.reederapp.xox;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.reederapp.xox.enums.OyunKey;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[][] xoxMatrisi = new int[3][3];
        for (int i = 0; i < 3; i++) {
            xoxMatrisi[i][0] = OyunKey.BOS.getDeger();
            xoxMatrisi[i][1] = OyunKey.BOS.getDeger();
            xoxMatrisi[i][2] = OyunKey.BOS.getDeger();
        }

        uiIslemleri(xoxMatrisi);
    }

    private void uiIslemleri(int[][] xoxMatrisi) {
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);

        btn1.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 0, 0);
            btn1.setText(mesaj);
        });
        btn2.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 0, 1);
            btn2.setText(mesaj);
        });
        btn3.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 0, 2);
            btn3.setText(mesaj);
        });
        btn4.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 1, 0);
            btn4.setText(mesaj);
        });
        btn5.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 1, 1);
            btn5.setText(mesaj);
        });
        btn6.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 1, 2);
            btn6.setText(mesaj);
        });
        btn7.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 2, 0);
            btn7.setText(mesaj);
        });
        btn8.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 2, 1);
            btn8.setText(mesaj);
        });
        btn9.setOnClickListener(v -> {
            String mesaj = kontrol(xoxMatrisi, OyunKey.X.getDeger(), 2, 2);
            btn9.setText(mesaj);
        });
    }

    private String kontrol(int[][] matris, int oyunSirasi, int satir, int sutun) {
        boolean eklendiMi = matrisKonumunaEkle(matris, oyunSirasi, satir, sutun);
        if (eklendiMi) {
            tamamlandiMi(matris);
            if (oyunSirasi == OyunKey.X.getDeger()) return "X";
            else return "O";
        }
        return "";
    }

    //Oyun.Key diyerek X,O ve BOŞ değerlerine ulaşabiliyoruz
    private boolean matrisKonumunaEkle(int[][] matris, int oyunSirasi, int satir, int sutun) {
        if (matris[satir][sutun] != OyunKey.BOS.getDeger()) {
            Log.d(TAG, "Eklenecek konum dolu");
            return false;
        }
        matris[satir][sutun] = oyunSirasi;
        return true;
    }

    private void tamamlandiMi(int[][] matris) {
        boolean bulunduMu = false;
        String mesaj = "";
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris.length; j++) {
                int matrisDegeri = matris[i][j];
                if (matrisDegeri != OyunKey.BOS.getDeger()) {
                    if (((j + 1) < matris.length) && ((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i][j + 1]) && (matris[i][j] == matris[i][j + 2])) {
                            mesaj = "Yatayda 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if (((i + 1) < matris.length) && ((i + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i + 1][j]) && (matris[i + 1][j] == matris[i + 2][j])) {
                            mesaj = "Dikeyde 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if ((i + 1) < matris.length && ((i + 2) < matris.length) && ((j + 1) < matris.length) && ((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i + 1][j + 1]) && (matris[i + 1][j + 1] == matris[i + 2][j + 2])) {
                            mesaj = "Çaprazda aşağıya 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if ((i - 1) >= 0 && ((i - 2) >= 0) && ((j + 1) < matris.length) && ((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i - 1][j + 1]) && (matris[i - 1][j + 1] == matris[i - 2][j + 2])) {
                            mesaj = "Çaprazda yukarıya 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                }
            }
        }
        if (bulunduMu) Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
    }
}