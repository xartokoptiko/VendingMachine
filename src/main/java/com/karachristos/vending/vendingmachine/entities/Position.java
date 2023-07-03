package com.karachristos.vending.vendingmachine.entities;



import com.karachristos.vending.vendingmachine.VendingMachineApplication;
import com.karachristos.vending.vendingmachine.entities.subentities.Cordinates;
import com.karachristos.vending.vendingmachine.entities.subentities.Dimensions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Position extends ImageView {

    private String position_name ;
    private ArrayList<Merchant> merchants = new ArrayList<>() ;
    private Dimensions dimensions_of_position ;
    private Cordinates cordinates ;

    private final String url = VendingMachineApplication.class.getResource("assets/machine/hawk.png").toExternalForm();


    public Position(String position_name,ArrayList<Merchant> merchants, Dimensions dimensions_of_position,int count, Cordinates cordinates) {
        setImage(new Image(url));
        this.dimensions_of_position = dimensions_of_position ;
        this.position_name = position_name;
        this.cordinates = cordinates ;

        if(this.merchants.size() > count){
            System.out.println("[WARNING]Too many products. Machine left behind" + (merchants.size()-count)+ " products.");
            this.merchants.subList(count, this.merchants.size()-1);
        }

        System.out.println("[INFO] " + this.merchants.size() + "Added to the position >>>"+position_name+"<<<");

    }

    public Position(String position_name, Dimensions dimensions_of_position, Cordinates cordinates) {
        setImage(new Image(url));
        this.position_name = position_name;
        this.dimensions_of_position = dimensions_of_position;
        this.cordinates = cordinates;
    }

    public Position() {
        setImage(new Image(url));
    }

    public void setMerchants(ArrayList<Merchant> merchants) {
        this.merchants = merchants;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public void setDimensions_of_position(Dimensions dimensions_of_position) {
        this.dimensions_of_position = dimensions_of_position;
    }

    public Cordinates getCordinates() {
        return cordinates;
    }

    public void setCordinates(Cordinates cordinates) {
        this.cordinates = cordinates;
    }

    public String getPosition_name() {
        return position_name;
    }

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public Dimensions getDimensions_of_position() {
        return dimensions_of_position;
    }

    @Override
    public String toString() {
        return "Position{" +
                "position_name='" + position_name + '\'' +
                ", merchants=" + merchants +
                ", dimensions_of_position=" + dimensions_of_position +
                ", cordinates=" + cordinates +
                '}';
    }

    public Boolean checkDimensions(){
        boolean toReturn = true;
        for(Merchant merchant : merchants){
            if(merchant.getDimensions().getX() <= dimensions_of_position.getX() &&
                    merchant.getDimensions().getY() <= dimensions_of_position.getY()&&
                    merchant.getDimensions().getZ() <= dimensions_of_position.getZ())
            {
                toReturn = false;
                break;
            }
        }

        return toReturn;

    }
}