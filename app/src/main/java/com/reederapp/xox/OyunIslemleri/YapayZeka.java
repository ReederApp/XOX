package com.reederapp.xox.OyunIslemleri;

import android.content.Context;
import android.widget.Button;

import com.reederapp.xox.enums.OyunKey;

public class YapayZeka {
    private final String TAG = "MainActivity";

    private final Button[][] buttons;
    private final Context mContext;

    public static boolean xoxBulunduMu = false;
    public static int btnBirSatir, btnBirSutun;
    public static int btnIkiSatir, btnIkiSutun;
    public static int btnUcSatir, btnUcSutun;

    public YapayZeka(Button[][] buttons, Context mContext) {
        this.buttons = buttons;
        this.mContext = mContext;
    }

    public void defansAtakKontrolu(int[][] xoxMatrisi, int digerSira) {
        boolean defansYapildiMi = defansYap(xoxMatrisi, digerSira);
        if (!defansYapildiMi) {
            int sira = digerSira == OyunKey.X.getDeger() ? OyunKey.O.getDeger() : OyunKey.X.getDeger();
            boolean atakYapildiMi = defansYap(xoxMatrisi, sira);
            if (!atakYapildiMi) atakYap(xoxMatrisi, digerSira);
        }
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
                if ((i - 2 >= 0) && (j + 2 < matris.length) && (!eklendiMi)) {
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
        boolean atakYapildiMi = false;
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris.length; j++) {
                if (!atakYapildiMi && (j + 2 < matris.length)) {
                    if ((matris[i][j] == oyunSirasi) && (matris[i][j + 1] == OyunKey.BOS.getDeger()) && (matris[i][j + 1] == matris[i][j + 2])) {
                        matris[i][j + 1] = oyunSirasi;
                        buttons[i][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        atakYapildiMi = true;
                    }
                }
                if (!atakYapildiMi && (i + 2 < matris.length)) {
                    if ((matris[i][j] == oyunSirasi) && (matris[i + 1][j] == OyunKey.BOS.getDeger()) && (matris[i + 1][j] == matris[i + 2][j])) {
                        matris[i + 1][j] = oyunSirasi;
                        buttons[i + 1][j].setText(getOyunSirasiText(oyunSirasi));
                        atakYapildiMi = true;
                    }
                }
                if (!atakYapildiMi && (i + 2) < matris.length && ((j + 2) < matris.length)) {
                    if ((matris[i][j] == oyunSirasi) && (matris[i + 1][j + 1] == OyunKey.BOS.getDeger()) && (matris[i + 1][j + 1] == matris[i + 2][j + 2])) {
                        matris[i + 1][j + 1] = oyunSirasi;
                        buttons[i + 1][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        atakYapildiMi = true;
                    }
                }
                if (!atakYapildiMi && (i - 2 >= 0) && (j + 2 < matris.length)) {
                    if ((matris[i][j] == oyunSirasi) && (matris[i - 1][j + 1] == OyunKey.BOS.getDeger()) && (matris[i - 1][j + 1] == matris[i - 2][j + 2])) {
                        matris[i - 1][j + 1] = oyunSirasi;
                        buttons[i - 1][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        atakYapildiMi = true;
                    }

                }
            }
        }
        if (!atakYapildiMi) {
            if (matris[0][matris.length - 1] == OyunKey.BOS.getDeger()) {
                matris[0][matris.length - 1] = oyunSirasi;
                buttons[0][matris.length - 1].setText(getOyunSirasiText(oyunSirasi));
            } else if (matris[matris.length - 1][0] == OyunKey.BOS.getDeger()) {
                matris[matris.length - 1][0] = oyunSirasi;
                buttons[matris.length - 1][0].setText(getOyunSirasiText(oyunSirasi));
            } else if (matris[matris.length - 1][matris.length - 1] == OyunKey.BOS.getDeger()) {
                matris[matris.length - 1][matris.length - 1] = oyunSirasi;
                buttons[matris.length - 1][matris.length - 1].setText(getOyunSirasiText(oyunSirasi));
            } else if (matris[0][0] == OyunKey.BOS.getDeger()) {
                matris[0][0] = oyunSirasi;
                buttons[0][0].setText(getOyunSirasiText(oyunSirasi));
            } else {
                for (int i = 0; i < matris.length; i++) {
                    for (int j = 0; j < matris.length; j++) {
                        if ((!atakYapildiMi) && (matris[i][j] == OyunKey.BOS.getDeger())) {
                            matris[i][j] = oyunSirasi;
                            buttons[i][j].setText(getOyunSirasiText(oyunSirasi));
                            atakYapildiMi = true;
                        }
                    }
                }
            }
        }

    }

    private String getOyunSirasiText(int oyunSirasi) {
        if (oyunSirasi == OyunKey.X.getDeger()) return "X";
        else return "O";
    }

    public boolean matrisKonumunaEkle(int[][] matris, int oyunSirasi, int satir, int sutun) {
        if (matris[satir][sutun] != OyunKey.BOS.getDeger()) {
            return false;
        }
        matris[satir][sutun] = oyunSirasi;
        return true;
    }

    public boolean matrisBosMu(int[][] matris) {
        boolean bosMu = false;
        for (int[] ints : matris) {
            for (int j = 0; j < matris.length; j++) {
                if ((!bosMu) && (ints[j] == OyunKey.BOS.getDeger())) {
                    bosMu = true;
                    break;
                }
            }
        }
        return bosMu;
    }

    public void tamamlandiMi(int[][] matris) {

        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris.length; j++) {
                int matrisDegeri = matris[i][j];
                if (matrisDegeri != OyunKey.BOS.getDeger()) {
                    if (((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i][j + 1]) && (matris[i][j] == matris[i][j + 2])) {
                            xoxBulunduMu = true;
                            btnDegerleriniSetle(i, j, i, j + 1, i, j + 2);
                        }
                    }
                    if ((i + 2) < matris.length) {
                        if ((matris[i][j] == matris[i + 1][j]) && (matris[i + 1][j] == matris[i + 2][j])) {
                            xoxBulunduMu = true;
                            btnDegerleriniSetle(i, j, i + 1, j, i + 2, j);
                        }
                    }
                    if (((i + 2) < matris.length) && ((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i + 1][j + 1]) && (matris[i + 1][j + 1] == matris[i + 2][j + 2])) {
                            xoxBulunduMu = true;
                            btnDegerleriniSetle(i, j, i + 1, j + 1, i + 2, j + 2);
                        }
                    }
                    if (((i - 2) >= 0) && ((j + 2) < matris.length)) {
                        if ((matris[i][j] == matris[i - 1][j + 1]) && (matris[i - 1][j + 1] == matris[i - 2][j + 2])) {
                            xoxBulunduMu = true;
                            btnDegerleriniSetle(i, j, i - 1, j + 1, i - 2, j + 2);
                        }
                    }
                }
            }
        }
    }

    private void btnDegerleriniSetle(int i1, int j1, int i2, int j2, int i3, int j3) {
        btnBirSatir = i1;
        btnIkiSatir = i2;
        btnUcSatir = i3;
        btnBirSutun = j1;
        btnIkiSutun = j2;
        btnUcSutun = j3;
    }
}
