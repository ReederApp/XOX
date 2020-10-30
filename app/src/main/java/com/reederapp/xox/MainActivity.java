package com.reederapp.xox;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.reederapp.xox.enums.OyunKey;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons = new Button[3][3];

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
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 0, 0);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn1.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
        btn2.setOnClickListener(v -> {
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 0, 1);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn2.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
        btn3.setOnClickListener(v -> {
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 0, 2);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn3.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
        btn4.setOnClickListener(v -> {
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 1, 0);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn4.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
        btn5.setOnClickListener(v -> {
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 1, 1);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn5.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
        btn6.setOnClickListener(v -> {
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 1, 2);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn6.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
        btn7.setOnClickListener(v -> {
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 2, 0);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn7.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
        btn8.setOnClickListener(v -> {
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 2, 1);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn8.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
        btn9.setOnClickListener(v -> {
            boolean ekle = matrisKonumunaEkle(xoxMatrisi, OyunKey.X.getDeger(), 2, 2);
            if (ekle) {
                tamamlandiMi(xoxMatrisi);
                btn9.setText("X");
                hamleYap(xoxMatrisi, OyunKey.O.getDeger());
                tamamlandiMi(xoxMatrisi);
            }
        });
    }

    private void hamleYap(int[][] matris, int oyunSirasi) {
        boolean eklendiMi = false;
        for (int i = 0; i < matris.length; i++) {
            if (eklendiMi) break;
            for (int j = 0; j < matris.length; j++) {
                //yatayda 3 tane bulma
                if (((j + 2) < matris.length) && (!eklendiMi)) {
                    if ((matris[i][j] != OyunKey.BOS.getDeger()) && (matris[i][j] == matris[i][j + 1]) && (matris[i][j + 2] == OyunKey.BOS.getDeger())) {
                        matris[i][j + 2] = oyunSirasi;
                        buttons[i][j + 2].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    } else if ((matris[i][j + 1] != OyunKey.BOS.getDeger()) && (matris[i][j + 1] == matris[i][j + 2]) && (matris[i][j] == OyunKey.BOS.getDeger())) {
                        matris[i][j] = oyunSirasi;
                        buttons[i][j].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    } else if ((matris[i][j] != OyunKey.BOS.getDeger()) && (matris[i][j] == matris[i][j + 2]) && (matris[i][j + 1] == OyunKey.BOS.getDeger())) {
                        matris[i][j + 1] = oyunSirasi;
                        buttons[i][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    }
                }
                //dikeyde 3 tane bulma
                if (((i + 2) < matris.length) && (!eklendiMi)) {
                    if ((matris[i][j] != OyunKey.BOS.getDeger()) && (matris[i][j] == matris[i + 1][j]) && (matris[i + 2][j] == OyunKey.BOS.getDeger())) {
                        matris[i + 2][j] = oyunSirasi;
                        buttons[i + 2][j].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    } else if ((matris[i][j] != OyunKey.BOS.getDeger()) && (matris[i][j] == matris[i + 2][j]) && (matris[i + 1][j] == OyunKey.BOS.getDeger())) {
                        matris[i + 1][j] = oyunSirasi;
                        buttons[i + 1][j].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    } else if ((matris[i + 1][j] != OyunKey.BOS.getDeger()) && (matris[i + 1][j] == matris[i + 2][j]) && (matris[i][j] == OyunKey.BOS.getDeger())) {
                        matris[i][j] = oyunSirasi;
                        buttons[i][j].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    }
                }
                //aşağı çaprazda bulma
                if ((i + 2) < matris.length && ((j + 2) < matris.length) && (!eklendiMi)) {
                    if ((matris[i][j] != OyunKey.BOS.getDeger()) && (matris[i][j] == matris[i + 1][j + 1]) && (matris[i + 2][j + 2] == OyunKey.BOS.getDeger())) {
                        matris[i + 2][j + 2] = oyunSirasi;
                        buttons[i + 2][j + 2].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    } else if ((matris[i][j] != OyunKey.BOS.getDeger()) && (matris[i][j] == matris[i + 2][j + 2]) && (matris[i + 1][j + 1] == OyunKey.BOS.getDeger())) {
                        matris[i + 1][j + 1] = oyunSirasi;
                        buttons[i + 1][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    } else if ((matris[i + 1][j + 1] != OyunKey.BOS.getDeger()) && (matris[i + 1][j + 1] == matris[i + 2][j + 2]) && (matris[i][j] == OyunKey.BOS.getDeger())) {
                        matris[i][j] = oyunSirasi;
                        buttons[i][j].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    }
                }
                //yukarı çaprazda bulma
                if ((i - 2 >= 0) && (j + 2 < matris.length)) {
                    if ((matris[i][j] != OyunKey.BOS.getDeger()) && (matris[i][j] == matris[i - 1][j + 1]) && (matris[i - 2][j + 2] == OyunKey.BOS.getDeger())) {
                        matris[i - 2][j + 2] = oyunSirasi;
                        buttons[i - 2][j + 2].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    } else if ((matris[i][j] != OyunKey.BOS.getDeger()) && (matris[i][j] == matris[i - 2][j + 2]) && (matris[i - 1][j + 1] == OyunKey.BOS.getDeger())) {
                        matris[i - 1][j + 1] = oyunSirasi;
                        buttons[i - 1][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    } else if ((matris[i - 1][j + 1] != OyunKey.BOS.getDeger()) && (matris[i - 1][j + 1] == matris[i - 2][j + 2]) && (matris[i][j] == OyunKey.BOS.getDeger())) {
                        matris[i][j] = oyunSirasi;
                        buttons[i][j].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    }
                }

            }
        }
    }

    private String getOyunSirasiText(int oyunSirasi) {
        if (oyunSirasi == OyunKey.X.getDeger()) return "X";
        else return "O";
    }

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
                    if (((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i][j + 1]) && (matris[i][j] == matris[i][j + 2])) {
                            mesaj = "Yatayda 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if ((i + 2) < matris.length) {
                        if ((matris[i][j] == matris[i + 1][j]) && (matris[i + 1][j] == matris[i + 2][j])) {
                            mesaj = "Dikeyde 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if (((i + 2) < matris.length) && ((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i + 1][j + 1]) && (matris[i + 1][j + 1] == matris[i + 2][j + 2])) {
                            mesaj = "Çaprazda aşağıya 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if (((i - 2) >= 0) && ((j + 2) < matris.length)) {
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