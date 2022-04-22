package com.company;

public class Kwadrat extends Prostokat {
    int bok;

    public Kwadrat(int i) {
        bok = i;
    }

    public Kwadrat(int i, int i1) {
        bok = i;
    }

    @Override
    public double podstawa() {
        return bok;
    }

    @Override
    public double przekatna() {
        return bok * Math.sqrt(2);
    }

    public String toString() {
        return "kwadrat";
    }

    /* public boolean equals(Prostokat p) {
        if (this == p)
            return true;
        else
            return false;
    } */
}
