package com.reederapp.xox.enums;

public enum OyunKey {
    BOS(0), X(1), O(2);
    final int deger;

    private OyunKey(int deger) {
        this.deger = deger;
    }

    public int getDeger() {
        return deger;
    }
}
