package com.reederapp.xox.OyunIslemleri;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import com.reederapp.xox.enums.OyunKey;

public class MatrisIslemleri {

    private final YapayZeka zeka;
    private final Context mContext;

    public MatrisIslemleri(YapayZeka zeka, Context mContext) {
        this.zeka = zeka;
        this.mContext = mContext;
    }

    public void hamleYap(int[][] xoxMatrisi, int oyunSirasi, int satir, int sutun, Button button) {
        boolean matrisBosMu = zeka.matrisBosMu(xoxMatrisi);
        if (matrisBosMu) {
            String gecerliOyuncuTexti, siradakiOyuncuTexti;
            int digerSira;
            if (oyunSirasi == OyunKey.X.getDeger()) {
                gecerliOyuncuTexti = "X";
                siradakiOyuncuTexti = "O";
                digerSira = OyunKey.O.getDeger();
            } else {
                gecerliOyuncuTexti = "O";
                siradakiOyuncuTexti = "X";
                digerSira = OyunKey.X.getDeger();
            }
            if (!YapayZeka.xoxBulunduMu) {
                boolean matrisEklendiMi = zeka.matrisKonumunaEkle(xoxMatrisi, oyunSirasi, satir, sutun);
                if (matrisEklendiMi) {
                    zeka.tamamlandiMi(xoxMatrisi);
                    button.setText(gecerliOyuncuTexti);
                    if (!YapayZeka.xoxBulunduMu) {
                        zeka.defansAtakKontrolu(xoxMatrisi, digerSira);
                        zeka.tamamlandiMi(xoxMatrisi);
                        if (YapayZeka.xoxBulunduMu) {
                            Toast.makeText(mContext, siradakiOyuncuTexti + " oyunu kazandı", Toast.LENGTH_SHORT).show();
                        }
                        if (!zeka.matrisBosMu(xoxMatrisi)) {
                            Toast.makeText(mContext, "Oyun berabere bitti", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, gecerliOyuncuTexti + " oyunu kazandı", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            Toast.makeText(mContext, "Oyun bitti", Toast.LENGTH_SHORT).show();
        }
    }

    public void matrisiBosalt(int[][] xoxMatrisi, Button[][] buttons) {
        for (int i = 0; i < xoxMatrisi.length; i++) {
            for (int j = 0; j < xoxMatrisi.length; j++) {
                xoxMatrisi[i][j] = OyunKey.BOS.getDeger();
                buttons[i][j].setText("");
            }
        }
    }

    public void matrisiDoldur(int[][] xoxMatrisi) {
        for (int i = 0; i < 3; i++) {
            xoxMatrisi[i][0] = OyunKey.BOS.getDeger();
            xoxMatrisi[i][1] = OyunKey.BOS.getDeger();
            xoxMatrisi[i][2] = OyunKey.BOS.getDeger();
        }
    }
}
