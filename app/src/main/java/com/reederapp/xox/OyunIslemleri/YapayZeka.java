package com.reederapp.xox.OyunIslemleri;

import android.widget.Button;

import com.reederapp.xox.enums.OyunKey;

import java.util.Random;

public class YapayZeka {
    private final Button[][] buttons;
    public static boolean xoxBulunduMu = false;
    public static int btnBirSatir, btnBirSutun;
    public static int btnIkiSatir, btnIkiSutun;
    public static int btnUcSatir, btnUcSutun;

    public YapayZeka(Button[][] buttons) {
        this.buttons = buttons;
    }

    public void defansAtakKontrolu(int[][] xoxMatrisi, int digerSira) {
        boolean defansYapildiMi = ikiliKontrol(xoxMatrisi, digerSira);
        if (!defansYapildiMi) {
            int sira = digerSira == OyunKey.X.getDeger() ? OyunKey.O.getDeger() : OyunKey.X.getDeger();
            boolean atakYapildiMi = ikiliKontrol(xoxMatrisi, sira);
            if (!atakYapildiMi) tekliKontrol(xoxMatrisi, digerSira);
        }
    }

    private boolean ikiliKontrol(int[][] matris, int oyunSirasi) {
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

    private void tekliKontrol(int[][] matris, int oyunSirasi) {
        boolean eklendiMi = false;
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris.length; j++) {
                if (!eklendiMi && (j + 2 < matris.length)) {
                    if ((matris[i][j] == oyunSirasi) && (matris[i][j + 1] == OyunKey.BOS.getDeger()) && (matris[i][j + 1] == matris[i][j + 2])) {
                        matris[i][j + 1] = oyunSirasi;
                        buttons[i][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    }
                }
                if (!eklendiMi && (i + 2 < matris.length)) {
                    if ((matris[i][j] == oyunSirasi) && (matris[i + 1][j] == OyunKey.BOS.getDeger()) && (matris[i + 1][j] == matris[i + 2][j])) {
                        matris[i + 1][j] = oyunSirasi;
                        buttons[i + 1][j].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    }
                }
                if (!eklendiMi && (i + 2) < matris.length && ((j + 2) < matris.length)) {
                    if ((matris[i][j] == oyunSirasi) && (matris[i + 1][j + 1] == OyunKey.BOS.getDeger()) && (matris[i + 1][j + 1] == matris[i + 2][j + 2])) {
                        matris[i + 1][j + 1] = oyunSirasi;
                        buttons[i + 1][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    }
                }
                if (!eklendiMi && (i - 2 >= 0) && (j + 2 < matris.length)) {
                    if ((matris[i][j] == oyunSirasi) && (matris[i - 1][j + 1] == OyunKey.BOS.getDeger()) && (matris[i - 1][j + 1] == matris[i - 2][j + 2])) {
                        matris[i - 1][j + 1] = oyunSirasi;
                        buttons[i - 1][j + 1].setText(getOyunSirasiText(oyunSirasi));
                        eklendiMi = true;
                    }

                }
            }
        }
        if (!eklendiMi) {
            Random random = new Random();
            int randomNumber = random.nextInt(5);
            if (randomNumber == 0 && !eklendiMi) {
                if (matris[0][matris.length - 1] == OyunKey.BOS.getDeger()) {
                    matris[0][matris.length - 1] = oyunSirasi;
                    buttons[0][matris.length - 1].setText(getOyunSirasiText(oyunSirasi));
                    eklendiMi = true;
                }
            }
            if (randomNumber == 1 && !eklendiMi) {
                if (matris[matris.length - 1][0] == OyunKey.BOS.getDeger()) {
                    matris[matris.length - 1][0] = oyunSirasi;
                    buttons[matris.length - 1][0].setText(getOyunSirasiText(oyunSirasi));
                    eklendiMi = true;
                }
            }
            if (randomNumber == 2 && !eklendiMi) {
                if (matris[matris.length - 1][matris.length - 1] == OyunKey.BOS.getDeger()) {
                    matris[matris.length - 1][matris.length - 1] = oyunSirasi;
                    buttons[matris.length - 1][matris.length - 1].setText(getOyunSirasiText(oyunSirasi));
                    eklendiMi = true;
                }
            }
            if (randomNumber == 3 && !eklendiMi) {
                if (matris[0][0] == OyunKey.BOS.getDeger()) {
                    matris[0][0] = oyunSirasi;
                    buttons[0][0].setText(getOyunSirasiText(oyunSirasi));
                    eklendiMi = true;
                }
            } else {
                for (int i = 0; i < matris.length; i++) {
                    for (int j = 0; j < matris.length; j++) {
                        if ((!eklendiMi) && (matris[i][j] == OyunKey.BOS.getDeger())) {
                            matris[i][j] = oyunSirasi;
                            buttons[i][j].setText(getOyunSirasiText(oyunSirasi));
                            eklendiMi = true;
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
