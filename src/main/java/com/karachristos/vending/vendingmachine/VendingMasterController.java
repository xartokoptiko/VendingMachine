package com.karachristos.vending.vendingmachine;

import com.karachristos.vending.vendingmachine.entities.Machine;
import com.karachristos.vending.vendingmachine.entities.Merchant;
import com.karachristos.vending.vendingmachine.entities.Position;
import com.karachristos.vending.vendingmachine.entities.subentities.Cordinates;
import com.karachristos.vending.vendingmachine.entities.subentities.Dimensions;
import com.karachristos.vending.vendingmachine.utils.CustomListCell;
import com.karachristos.vending.vendingmachine.utils.PopupUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class VendingMasterController implements Initializable {

    public Pane initialize_panel, root_panel, product_details_panel;

    public TextField rows_of_shelf, number_of_shelves, max_x_dimension, max_y_dimension, max_z_dimension, number_of_products, cost;
    public Button machine_submit, submit_product, machine_create;

    public Label position_product, product_see;

    public ComboBox<Merchant> product_choose;

    public DatePicker experation_date;

    public ListView<Position> position_listview;

    private ObservableList<Merchant> allMerchants = FXCollections.observableArrayList(new Data().getMerchants());

    @FXML
    private void handleKeyTyped(KeyEvent event) {
        if (!event.getCharacter().matches("[0-9]")) {
            event.consume();
        }
    }

    //Data for the machine initialization
    private int rows, columns;
    private double max_x, max_z, max_y;

    //Data for the product initialization
    private int number_products;
    private LocalDate experationDate;
    private Merchant product_choosen;
    private double cost_of_product;

    private ObservableList<Position> positions = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        product_details_panel.setDisable(true);
        machine_create.setDisable(true);
        position_listview.setCellFactory(param -> new CustomListCell(product_details_panel, position_product));
        product_choose.setCellFactory(param -> new ListCell<Merchant>() {
            @Override
            protected void updateItem(Merchant item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        product_choose.setItems(allMerchants);


        machine_submit.setOnAction(event -> {
            try {
                rows = Integer.parseInt(rows_of_shelf.getText());
                columns = Integer.parseInt(number_of_shelves.getText());

                max_x = Double.parseDouble(max_x_dimension.getText());
                max_y = Double.parseDouble(max_y_dimension.getText());
                max_z = Double.parseDouble(max_z_dimension.getText());

                if (rows == 0 || columns == 0 || max_x == 0.0 || max_y == 0.0 || max_z == 0.0 || rows > 10 || columns > 10) {
                    PopupUtils.showPopup("None of this values can be zero ! Also you cant use more than 10 shelves or rows !", Alert.AlertType.ERROR);
                } else {
                    System.out.println("[INFO] Values stored successfully");

                    initialize_panel.setDisable(true);

                    double gap = 0;

                    System.out.println("######DEBUG INFO######");
                    for (int x = 1; x <= columns; x++) {
                        for (int i = 1; i <= rows; i++) {

                            if (i - 1 == 0) {
                                gap = 32.0;
                            }

                            double cordinate_x = ((32.0 * (i - 1)) + gap);
                            double cordinate_y = ((32.0 * (x - 1)) + gap);

                            String position_name = x + "" + i;
                            System.out.println("[DEBUG]Name : " + position_name + " Cordinates -> x:" + cordinate_x + " y:" + cordinate_y + gap);


                            positions.add(new Position(position_name, new Dimensions(max_x, max_y, max_z), new Cordinates(cordinate_x, cordinate_y)));

                        }
                    }
                    System.out.println("[DEBUG]Position count :" + positions.size());
                    System.out.println("###### END OF DEBUG INFO######");

                    position_listview.setItems(positions);
                    machine_create.setDisable(false);
                }
            }catch (Exception e){
                PopupUtils.showPopup("Something Went wrong check again the values", Alert.AlertType.ERROR);
            }
        });

        submit_product.setOnAction(event -> {
            try {
                number_products = Integer.parseInt(number_of_products.getText());
                experationDate = experation_date.getValue();
                product_choosen = product_choose.getValue();
                cost_of_product = Double.parseDouble(cost.getText());
                int selectedIndex = position_listview.getSelectionModel().getSelectedIndex();

                ArrayList<Merchant> merchantsToAdd = new ArrayList<>();

                Optional<Position> optionalItem = positions.stream()
                        .filter(item -> item.getPosition_name().equals(position_product.getText().toString()))
                        .findFirst();

                System.out.println("###### Debug info ######");
                optionalItem.ifPresent(item -> {

                    if (number_products == 0 || !checkExpiration(experation_date.getValue().atStartOfDay()) ||
                            product_choose == null || cost_of_product == 0 || !item.checkDimensions()) {
                        PopupUtils.showPopup("Failed to Submit Product ! Check the dimesnions or thge experation date or the cost or even you could have forgot to choose one :(", Alert.AlertType.WARNING);
                    } else {
                        product_choosen.setCost(cost_of_product);
                        product_choosen.setPosition(item.getPosition_name());

                        for (int x = 0; x < number_products; x++) {
                            merchantsToAdd.add(product_choosen);
                        }

                        item.setMerchants(merchantsToAdd);
                        System.out.println("[DEBUG]Added " + number_products + " Merchants in position " + item.getPosition_name());
                        product_details_panel.setDisable(true);
                    }
                });

                System.out.println("######End Debug info ######");
            }catch (Exception e){
                PopupUtils.showPopup("Something Went wrong check again the values", Alert.AlertType.ERROR);
            }
        });

        machine_create.setOnAction(event -> {
            Machine machine = new Machine(positions, rows, columns);
            machine.setFirst(false);
            machine.setLastUpdated(LocalDateTime.now());
            new Data().writeMachine(machine);
            switchToTargetController(event);
        });

        product_see.setOnMouseClicked(event -> {
            if (product_choose.getValue() != null) {
                PopupUtils.showPopup(product_choose.getValue().toString(), Alert.AlertType.INFORMATION);
            }else{
                PopupUtils.showPopup("No product", Alert.AlertType.WARNING);
            }
        });

    }

    private void switchToTargetController(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VendingMachineApplication.class.getResource("vending_user.fxml"));
            Parent root = loader.load();

            VendingUserController targetController = loader.getController();

            Scene currentScene = ((Node) event.getSource()).getScene();
            Stage stage = (Stage) currentScene.getWindow();

            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean checkExpiration(LocalDateTime expirationDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Boolean toReturn = true;

        if (currentDateTime.isAfter(expirationDateTime)) {
            toReturn = false;
        }

        return toReturn;
    }

}
