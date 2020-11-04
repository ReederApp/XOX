package com.reederapp.xox.OyunIslemleri;

import android.os.Handler;
import android.widget.Button;

import com.reederapp.xox.enums.OyunKey;
import com.reederapp.xox.interfaces.OyunInterfaces;

public class MatrisIslemleri {

    private final YapayZeka zeka;
    private final OyunInterfaces oyunInterfaces;
    private final Handler handler;

    public MatrisIslemleri(YapayZeka zeka, OyunInterfaces oyunInterfaces) {
        this.zeka = zeka;
        this.oyunInterfaces = oyunInterfaces;
        handler = new Handler();
    }

    public void hamleDurdur() {
        handler.removeCallbacks(null);
    }

    public void hamleYap(int[][] xoxMatrisi, int gecerliOyuncu, int satir, int sutun, Button button) {

        boolean matrisBosMu = zeka.matrisBosMu(xoxMatrisi);
        if (matrisBosMu) {
            String gecerliOyuncuTexti, siradakiOyuncuTexti;
            int siradakiOyuncu;
            if (gecerliOyuncu == OyunKey.X.getDeger()) {
                gecerliOyuncuTexti = "X";
                siradakiOyuncuTexti = "O";
                siradakiOyuncu = OyunKey.O.getDeger();
            } else {
                gecerliOyuncuTexti = "O";
                siradakiOyuncuTexti = "X";
                siradakiOyuncu = OyunKey.X.getDeger();
            }
            if (!YapayZeka.xoxBulunduMu) {
                oyunInterfaces.oyunSirasi(siradakiOyuncuTexti);
                oyunInterfaces.tiklamaKontrolu(false);
                boolean matrisEklendiMi = zeka.matrisKonumunaEkle(xoxMatrisi, gecerliOyuncu, satir, sutun);
                if (matrisEklendiMi) {
                    zeka.tamamlandiMi(xoxMatrisi);
                    button.setText(gecerliOyuncuTexti);
                    if (!YapayZeka.xoxBulunduMu) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                oyunInterfaces.oyunSirasi(gecerliOyuncuTexti);
                                zeka.defansAtakKontrolu(xoxMatrisi, siradakiOyuncu);
                                zeka.tamamlandiMi(xoxMatrisi);
                                if (YapayZeka.xoxBulunduMu) {
                                    oyunInterfaces.oyunuKazanan(siradakiOyuncu);
                                }
                                if (!zeka.matrisBosMu(xoxMatrisi)) {
                                    oyunInterfaces.oyunuKazanan(OyunKey.BOS.getDeger());
                                }
                                oyunInterfaces.tiklamaKontrolu(true);
                            }
                        }, 600);
                    } else {
                        oyunInterfaces.oyunuKazanan(gecerliOyuncu);
                    }
                }
            } else {
                oyunInterfaces.oyunuKazanan(OyunKey.BOS.getDeger());
            }
        }
    }

    public void matrisiBosalt(int[][] xoxMatrisi) {
        for (int i = 0; i < xoxMatrisi.length; i++) {
            for (int j = 0; j < xoxMatrisi.length; j++) {
                xoxMatrisi[i][j] = OyunKey.BOS.getDeger();
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
