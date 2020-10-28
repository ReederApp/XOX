package com.reederapp.xox;

import android.os.Bundle;
import android.util.Log;

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


        matrisiYazdir(xoxMatrisi);

        matrisKonumunaEkle(xoxMatrisi, OyunKey.O.getDeger(), 1, 0);
        matrisiYazdir(xoxMatrisi);

        matrisKonumunaEkle(xoxMatrisi, OyunKey.O.getDeger(), 1, 1);
        matrisiYazdir(xoxMatrisi);

        matrisKonumunaEkle(xoxMatrisi, OyunKey.O.getDeger(), 1, 2);
        matrisiYazdir(xoxMatrisi);
    }

    private void matrisiYazdir(int[][] matris) {
        Log.d(TAG, "---------------");
        for (int i = 0; i < matris.length; i++) {
            try {
                Thread.sleep(500);
                Log.d(TAG, matris[i][0] + "  " + matris[i][1] + "  " + matris[i][2]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

}