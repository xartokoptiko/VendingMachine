package com.karachristos.vending.vendingmachine.entities.subentities;

public class Dimensions {
    private Double x;
    private Double y;

    private Double z;

    public Dimensions(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Dimensions() {
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;

    }

    @Override
    public String toString() {
        return ("[X]"+ this.x +"\n" +
                "[Y]"+ this.y + "\n" +
                "[Z]"+ this.z +"\n");
    }
}
