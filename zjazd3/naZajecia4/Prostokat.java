package com.company;

public class Prostokat implements MaWlasnosciRownolegloboku, MoznaWyznaczycPrzekatna, MoznaWyznaczycWielokrotnoscPrzekatnej, MoznaWyznaczycWysokosc {
    int bok;
    int podstawa;

    public Prostokat() {
        bok = 0;
        podstawa = 0;
    }

    public Prostokat(int i, int i1) {
        bok = i;
        podstawa = i1;
    }

    public double bok() {
        return bok;
    }

    public double podstawa() {
        return podstawa;
    }

    public double wysokosc() {
        return bok;
    }

    public double katNachylenia() {
        return Math.PI / 2;
    }

    public double przekatna() {
        return Math.sqrt(Math.pow(bok, 2) + Math.pow(podstawa, 2));
    }

    public int wielokrotnoscPrzekatnej(double v) {
        return (int) (v * przekatna());
    }

    public String toString() {
        return "prostokat";
    }

    /* public boolean equals(MaWlasnosciRownolegloboku mwr) {
        if (this == mwr)
            return true;
        else
            return false;
    } */
}
