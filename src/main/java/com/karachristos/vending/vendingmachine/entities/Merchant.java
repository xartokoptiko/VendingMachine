package com.karachristos.vending.vendingmachine.entities;


import com.karachristos.vending.vendingmachine.VendingMachineApplication;
import com.karachristos.vending.vendingmachine.entities.subentities.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.Date;
import java.util.Objects;

public class Merchant extends ImageView {

    private String name;
    private String brand;

    private String summary;

    private Dimensions dimensions;

    private Date exparation_date ;

    private String volume ;

    private String url ;

    private String position;

    private double cost;

    public Merchant() {
    }

    public Merchant(String name, String brand, String sammary, Dimensions dimensions, Date exparation_date, String volume, String url) {
        this.name = name;
        this.brand = brand;
        this.summary = sammary;
        this.dimensions = dimensions;
        this.exparation_date = exparation_date;
        this.volume = volume;
        setImage(new Image(url));
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getSummary() {
        return summary;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public Date getExparation_date() {
        return exparation_date;
    }

    public String getVolume() {
        return volume;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public void setExparation_date(Date exparation_date) {
        this.exparation_date = exparation_date;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        setImage(new Image(Objects.requireNonNull(VendingMachineApplication.class.getResourceAsStream(url))));
    }

    @Override
    public String toString() {
        return(this.brand + " " + this.name+"\n" +
                " " + this.summary + "\n" +
                "Volume " + this.volume + "\n" +
                "Dimensions" + this.dimensions.toString());
    }
}