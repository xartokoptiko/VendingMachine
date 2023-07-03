package com.karachristos.vending.vendingmachine.entities.subentities;

public class Cordinates {
    private Double x ;
    private Double y;

    public Cordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Cordinates() {
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
