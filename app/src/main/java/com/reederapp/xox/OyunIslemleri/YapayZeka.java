package com.reederapp.xox.OyunIslemleri;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.reederapp.xox.enums.OyunKey;

public class YapayZeka {
    private final String TAG = "MainActivity";

    private final Button[][] buttons;
    private final Context mContext;

    public boolean bulunduMu = false;

    public YapayZeka(Button[][] buttons, Context mContext) {
        this.buttons = buttons;
        this.mContext = mContext;
    }

    public void defansAtakKontrolu(int[][] xoxMatrisi, int digerSira) {
        boolean defansYapildiMi = defansYap(xoxMatrisi, digerSira);
        if (!defansYapildiMi) atakYap(xoxMatrisi, digerSira);
    }

    private boolean defansYap(int[][] matris, int oyunSirasi) {
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
        return eklendiMi;
    }

    private void atakYap(int[][] matris, int oyunSirasi) {
    }

    private String getOyunSirasiText(int oyunSirasi) {
        if (oyunSirasi == OyunKey.X.getDeger()) return "X";
        else return "O";
    }

    public boolean matrisKonumunaEkle(int[][] matris, int oyunSirasi, int satir, int sutun) {
        if (matris[satir][sutun] != OyunKey.BOS.getDeger()) {
            Log.d(TAG, "Eklenecek konum dolu");
            return false;
        }
        matris[satir][sutun] = oyunSirasi;
        return true;
    }

    public void tamamlandiMi(int[][] matris) {

        String mesaj = "";
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris.length; j++) {
                int matrisDegeri = matris[i][j];
                if (matrisDegeri != OyunKey.BOS.getDeger()) {
                    if (((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i][j + 1]) && (matris[i][j] == matris[i][j + 2])) {
//                            mesaj = "Yatayda 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if ((i + 2) < matris.length) {
                        if ((matris[i][j] == matris[i + 1][j]) && (matris[i + 1][j] == matris[i + 2][j])) {
//                            mesaj = "Dikeyde 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if (((i + 2) < matris.length) && ((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i + 1][j + 1]) && (matris[i + 1][j + 1] == matris[i + 2][j + 2])) {
//                            mesaj = "Çaprazda aşağıya 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                    if (((i - 2) >= 0) && ((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i - 1][j + 1]) && (matris[i - 1][j + 1] == matris[i - 2][j + 2])) {
//                            mesaj = "Çaprazda yukarıya 3 tane " + matrisDegeri + " bulundu.";
                            bulunduMu = true;
                        }
                    }
                }
            }
        }
//        if (bulunduMu) Toast.makeText(mContext, mesaj, Toast.LENGTH_SHORT).show();
    }
}
