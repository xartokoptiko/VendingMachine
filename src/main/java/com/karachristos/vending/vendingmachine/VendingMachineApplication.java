package com.karachristos.vending.vendingmachine;
import com.karachristos.vending.vendingmachine.entities.Machine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class VendingMachineApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        String fxml_file = "vending_user.fxml" ;
        Machine machine_data = new Data().getMachine();

        if(machine_data.isFirst()){
            fxml_file = "vending_master.fxml" ;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(VendingMachineApplication.class.getResource(fxml_file));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Vending Machine Simulator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("[DEBUG] Printing all the products");
        System.out.println(new Data().getMerchants());
        System.out.println("[DEBUG] Check if all the products of the Product.json are printed !");
        launch();
    }

}