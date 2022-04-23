package com.company;

public class Buzka implements PrzedstawiaEmocje, Rysowalny {
    String buzka;

    public Buzka(char c) {
        buzka = ":-" + c;
    }

    public Buzka() {
        buzka = ":-";
    }

    public void draw() {
        System.out.println(buzka);
    }

    public boolean czySieUsmiecha() {
        if (buzka.equals(":-)")) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void przestanSieUsmiechac() {
        buzka = ":-(";
    }

    public String toString() {
        return buzka;
    }
}
