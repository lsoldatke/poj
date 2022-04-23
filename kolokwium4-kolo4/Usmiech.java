package com.company;

public class Usmiech extends Buzka implements Skalowalny {
    String usmiech;

    public Usmiech() {
        super();
        usmiech = ":-)";
    }

    public void draw() {
        System.out.println(usmiech);
    }

    public void draw(int i) {
        System.out.println(usmiech);
    }

    public boolean czySieUsmiecha() {
        return true;
    }

    public void przestanSieUsmiechac() {
    }

    public String toString() {
        return usmiech;
    }
}
