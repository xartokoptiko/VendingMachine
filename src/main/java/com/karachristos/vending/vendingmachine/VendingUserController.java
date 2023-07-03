package com.karachristos.vending.vendingmachine;

import com.karachristos.vending.vendingmachine.entities.Machine;
import com.karachristos.vending.vendingmachine.entities.Merchant;
import com.karachristos.vending.vendingmachine.entities.Position;
import com.karachristos.vending.vendingmachine.utils.PopupUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class VendingUserController implements Initializable {

    private final Machine machine_to_create = new Data().getMachine();

    private double yesterdays = machine_to_create.getYesterdays();
    private double todays = machine_to_create.getTodays();

    @FXML
    ImageView one, two, three, four, five, six,
            seven, eight, nine, zero, back, ok,
            fifty_cent, five_cent, one_cent, one_euro,
            ten_cent, twenty_cent, two_cent, two_euro;

    @FXML
    TextField number_text;

    @FXML
    Label information_label, todays_earnings, yesterdays_earnings;

    @FXML
    Pane image_cont, control_pane;

    @FXML
    Button save, reset;

    private boolean isChechingPayment = false ;

    private Optional<Position> positionToChange;

    private double toPay ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //INITIALIZING THE MACHINE

        Image backgroundImageVe = new Image(getClass().getResource("assets/machine/bg-ve.png").toExternalForm());
        Image backgroundImageUs = new Image(getClass().getResource("assets/machine/bg-us.jpg").toExternalForm());

        // Set the background image
        image_cont.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(backgroundImageVe,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.DEFAULT,
                        new javafx.scene.layout.BackgroundSize(
                                100, // Width
                                100, // Height
                                true, // Width proportional
                                true, // Height proportional
                                true, // Resize to fit
                                false) // No repeat
                )
        ));

        control_pane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundImage(backgroundImageUs,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
                        javafx.scene.layout.BackgroundPosition.DEFAULT,
                        new javafx.scene.layout.BackgroundSize(
                                100, // Width
                                100, // Height
                                true, // Width proportional
                                true, // Height proportional
                                true, // Resize to fit
                                false) // No repeat
                )
        ));

        double gap = 0.0;
        if (!isSameDay(machine_to_create.getLastUpdated(), LocalDateTime.now())) {
            yesterdays = todays;
            todays = 0.0;
            PopupUtils.showPopup("Day changed !", Alert.AlertType.INFORMATION);
        }
        yesterdays_earnings.setText(String.valueOf(yesterdays));
        todays_earnings.setText(String.valueOf(todays));

        for (Position position : machine_to_create.getPositions()) {

            double x = position.getCordinates().getX();
            double y = position.getCordinates().getY();

            position.setX(x);
            position.setY(y);

            position.setFitHeight(32.0);
            position.setFitWidth(32.0);

            position.setImage(new Image(getClass().getResource("assets/machine/hawk.png").toExternalForm()));
            image_cont.getChildren().add(position);

            System.out.println("[Info]Rendered position -> " + position.getPosition_name());

            for (Merchant merchant : position.getMerchants()) {
                merchant.setX(x);
                merchant.setY(y);

                image_cont.getChildren().add(merchant);
            }

        }

        makeTheBorder(machine_to_create.getRows(), machine_to_create.getColumns());

        // Set images for buttons
        one.setImage(new Image(getClass().getResource("assets/machine/one.png").toExternalForm()));
        two.setImage(new Image(getClass().getResource("assets/machine/two.png").toExternalForm()));
        three.setImage(new Image(getClass().getResource("assets/machine/three.png").toExternalForm()));
        four.setImage(new Image(getClass().getResource("assets/machine/four.png").toExternalForm()));
        five.setImage(new Image(getClass().getResource("assets/machine/five.png").toExternalForm()));
        six.setImage(new Image(getClass().getResource("assets/machine/six.png").toExternalForm()));
        seven.setImage(new Image(getClass().getResource("assets/machine/seven.png").toExternalForm()));
        eight.setImage(new Image(getClass().getResource("assets/machine/eight.png").toExternalForm()));
        nine.setImage(new Image(getClass().getResource("assets/machine/nine.png").toExternalForm()));
        zero.setImage(new Image(getClass().getResource("assets/machine/zero.png").toExternalForm()));

        ok.setImage(new Image(getClass().getResource("assets/machine/ok.png").toExternalForm()));
        back.setImage(new Image(getClass().getResource("assets/machine/back.png").toExternalForm()));

        fifty_cent.setImage(new Image(getClass().getResource("assets/machine/money/fifty-cent.png").toExternalForm()));
        five_cent.setImage(new Image(getClass().getResource("assets/machine/money/five-cent.png").toExternalForm()));
        one_cent.setImage(new Image(getClass().getResource("assets/machine/money/one-cent.png").toExternalForm()));
        one_euro.setImage(new Image(getClass().getResource("assets/machine/money/one-euro.png").toExternalForm()));
        ten_cent.setImage(new Image(getClass().getResource("assets/machine/money/ten-cent.png").toExternalForm()));
        twenty_cent.setImage(new Image(getClass().getResource("assets/machine/money/twenty-cent.png").toExternalForm()));
        two_cent.setImage(new Image(getClass().getResource("assets/machine/money/two-cent.png").toExternalForm()));
        two_euro.setImage(new Image(getClass().getResource("assets/machine/money/two-euro.png").toExternalForm()));

        //SETTING THE ON ACTION

        one.setOnMouseClicked(event -> appendNumber("1"));
        two.setOnMouseClicked(event -> appendNumber("2"));
        three.setOnMouseClicked(event -> appendNumber("3"));
        four.setOnMouseClicked(event -> appendNumber("4"));
        five.setOnMouseClicked(event -> appendNumber("5"));
        six.setOnMouseClicked(event -> appendNumber("6"));
        seven.setOnMouseClicked(event -> appendNumber("7"));
        eight.setOnMouseClicked(event -> appendNumber("8"));
        nine.setOnMouseClicked(event -> appendNumber("9"));
        zero.setOnMouseClicked(event -> appendNumber("0"));

        fifty_cent.setOnMouseClicked((MouseEvent event) -> appendPayment(0.5));
        five_cent.setOnMouseClicked((MouseEvent event) -> appendPayment(0.05));
        one_cent.setOnMouseClicked((MouseEvent event) -> appendPayment(0.01));
        one_euro.setOnMouseClicked((MouseEvent event) -> appendPayment(1.0));
        ten_cent.setOnMouseClicked((MouseEvent event) -> appendPayment(0.10));
        twenty_cent.setOnMouseClicked((MouseEvent event) -> appendPayment(0.20));
        two_cent.setOnMouseClicked((MouseEvent event) -> appendPayment(0.02));
        two_euro.setOnMouseClicked((MouseEvent event) -> appendPayment(2.0));

        disablePaymentCoin();

        // Set onAction listener for back button
        back.setOnMouseClicked(event -> removeLastNumber());

        ok.setOnMouseClicked(event -> {
            if (isChechingPayment) {
                double money = Double.parseDouble(number_text.getText());
                System.out.println("[DEBUG] Inside payment cheching. toPay:"+toPay + " user money:" + money);
                if(toPay <= money){
                    if(generateWithChance()){
                        System.out.println("[DEBUG] Prompt inside the chance generator");
                        positionToChange.ifPresent(item -> {
                            if (item.getMerchants().size() > 0) {

                                if (item.getMerchants().size() == 1) {
                                    double x = item.getCordinates().getX();
                                    double y = item.getCordinates().getY();

                                    ImageView hawk = new ImageView(new Image(getClass().getResource("assets/machine/hawk.png").toExternalForm()));

                                    hawk.setX(x);
                                    hawk.setY(y);

                                    hawk.setFitHeight(32.0);
                                    hawk.setFitWidth(32.0);

                                    image_cont.getChildren().add(hawk);
                                }
                                if(money > toPay){
                                    double change = money - toPay ;
                                    information_label.setText("You picked a " + item.getMerchants().get(0).getName() + " Your change : " + change);
                                    System.out.println("[Info] prompt -> " + "You picked a " + item.getMerchants().get(0).getName() + " With change !");
                                }else {
                                    information_label.setText("You picked a " + item.getMerchants().get(0).getName());
                                    System.out.println("[Info] prompt -> " + "You picked a " + item.getMerchants().get(0).getName());
                                }
                                todays = todays + item.getMerchants().get(0).getCost();
                                todays_earnings.setText(String.valueOf(todays_earnings));
                                item.getMerchants().remove(item.getMerchants().size() - 1);
                                disablePaymentCoin();
                                number_text.setText("");
                                isChechingPayment = false ;
                            } else {
                                information_label.setText("Nothing to pick there :(");
                                System.out.println("[Debug] Merchant size smaller than 0 ");
                                System.out.println("[Info] prompt->  :(");
                                isChechingPayment = false ;
                                number_text.setText("");
                                disablePaymentCoin();
                            }
                        });
                    }else {
                        information_label.setText("Payment failed. Cant accept coins ! :(");
                        isChechingPayment = false ;
                        number_text.setText("");
                        disablePaymentCoin();
                    }
                }else{
                    information_label.setText("Payment failed. Not enough money ! :(");
                    System.out.println("[Debug] Prompt -> Not enough money ");
                    isChechingPayment = false ;
                    number_text.setText("");
                    disablePaymentCoin();
                }
            }else {
                String number = number_text.getText();

                Optional<Position> optionalItem = machine_to_create.getPositions().stream()
                        .filter(item -> item.getPosition_name().equals(number))
                        .findFirst();

                if (optionalItem.isPresent()) {
                    if(optionalItem.get().getMerchants().size() > 0) {
                        System.out.println("[Debug] Found the position : " + optionalItem.get().getPosition_name());
                        positionToChange = optionalItem;
                        isChechingPayment = true;
                        toPay = optionalItem.get().getMerchants().get(0).getCost();
                        information_label.setText("It will cost you -> " + toPay + "$");
                        number_text.setText("");
                        enablePaymentCoin();
                    }else {
                        System.out.println("[Debug] Did not find the position");
                        information_label.setText("Nothing to pick there :(");
                        System.out.println("[Info] prompt-> Nothing to pick there :(");
                        number_text.setText("");
                    }
                } else {
                    System.out.println("[Debug] Did not find the position");
                    information_label.setText("Nothing to pick there :(");
                    System.out.println("[Info] prompt-> Nothing to pick there :(");
                    number_text.setText("");
                }
            }
        });

        save.setOnMouseClicked(event -> {
            saveData();
        });

        reset.setOnMouseClicked(this::resetData);

    }

    public void makeTheBorder(int rows, int columns) {
        double gap = 0.0;
        double count = 0.0;

        for (int i = 0; i < 2; i++) {
            // Loop to make the vertical borders
            for (int x = 0; x < columns; x++) {
                if (x == 0) {
                    gap = 32.0;
                }
                if (i == 1) {
                    count = rows + 1;
                }

                // Create and configure the vertical border image
                ImageView border = new ImageView(new Image(getClass().getResource("assets/machine/border.png").toExternalForm()));
                border.setFitWidth(32.0);
                border.setFitHeight(32.0);
                border.setRotate(90.0);

                border.setX(32 * count);
                border.setY(32.0 * x + gap);

                // Add the border image to the container
                image_cont.getChildren().add(border);
            }

            // Loop to make the horizontal borders
            for (int x = 0; x < rows; x++) {
                if (x == 0) {
                    gap = 32.0;
                }
                if (i == 1) {
                    count = columns + 1;
                }

                // Create and configure the horizontal border image
                ImageView border = new ImageView(new Image(getClass().getResource("assets/machine/border.png").toExternalForm()));
                border.setFitWidth(32.0);
                border.setFitHeight(32.0);

                border.setX(32.0 * x + gap);
                border.setY(32 * count);

                // Add the border image to the container
                image_cont.getChildren().add(border);
            }

            // Create and configure the corner borders
            ImageView border_tl = new ImageView(new Image(getClass().getResource("assets/machine/border-corner.png").toExternalForm()));
            border_tl.setRotate(270);

            ImageView border_tr = new ImageView(new Image(getClass().getResource("assets/machine/border-corner.png").toExternalForm()));

            ImageView border_bl = new ImageView(new Image(getClass().getResource("assets/machine/border-corner.png").toExternalForm()));
            border_bl.setRotate(180);

            ImageView border_br = new ImageView(new Image(getClass().getResource("assets/machine/border-corner.png").toExternalForm()));
            border_br.setRotate(90);

            border_tl.setY(0);
            border_bl.setX(0);

            border_tr.setY(0);
            border_tr.setX(32 * rows + 32);

            border_bl.setX(0);
            border_bl.setY(32 * columns + 32);

            border_br.setX(32 * rows + 32);
            border_br.setY(32 * columns + 32);

            // Add the corner borders to the container
            image_cont.getChildren().addAll(border_tl, border_tr, border_bl, border_br);
        }
    }

    private void appendNumber(String number) {
        String currentText = number_text.getText();
        number_text.setText(currentText + number);
    }

    private void removeLastNumber() {
        String currentText = number_text.getText();
        if (!currentText.isEmpty()) {
            number_text.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void saveData() {
        machine_to_create.setYesterdays(yesterdays);
        machine_to_create.setTodays(todays);

        new Data().writeMachine(machine_to_create);

        PopupUtils.showPopup("Data saved successfully", Alert.AlertType.INFORMATION);

        System.out.println("[DEBUG] Machine -> " + machine_to_create);

    }

    private void resetData(MouseEvent event) {
        Machine reset = new Machine();
        reset.setLastUpdated(LocalDateTime.now());
        reset.setTodays(0.0);
        reset.setYesterdays(0.0);
        reset.setRows(0);
        reset.setColumns(0);
        reset.setFirst(true);
        new Data().writeMachine(reset);
        PopupUtils.showPopup("Data Reset successfully", Alert.AlertType.INFORMATION);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VendingMachineApplication.class.getResource("vending_master.fxml"));
            Parent root = loader.load();

            VendingMasterController targetController = loader.getController();

            Scene currentScene = ((Node) event.getSource()).getScene();
            Stage stage = (Stage) currentScene.getWindow();

            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSameDay(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        LocalDate date1 = dateTime1.toLocalDate();
        LocalDate date2 = dateTime2.toLocalDate();
        return date1.isEqual(date2);
    }

    public static boolean generateWithChance() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);  // Generate a random number between 0 and 99

        return randomNumber >= 20;  // Returns true for numbers 20-99 (80% chance), false for numbers 0-19 (20% chance)
    }

    public void appendPayment(double coin){
        if(number_text.getText().equals("")){
            number_text.setText(String.valueOf(coin));
        }else{
            double sum = Double.parseDouble(number_text.getText()) + coin ;
            number_text.setText(String.valueOf(sum));
        }
    }

    public void disablePaymentCoin(){
        fifty_cent.setDisable(true);
        five_cent.setDisable(true);
        one_cent.setDisable(true);
        one_euro.setDisable(true);
        ten_cent.setDisable(true);
        twenty_cent.setDisable(true);
        two_cent.setDisable(true);
        two_euro.setDisable(true);
    }

    public void enablePaymentCoin(){
        fifty_cent.setDisable(false);
        five_cent.setDisable(false);
        one_cent.setDisable(false);
        one_euro.setDisable(false);
        ten_cent.setDisable(false);
        twenty_cent.setDisable(false);
        two_cent.setDisable(false);
        two_euro.setDisable(false);
    }

    public static Boolean checkExpiration(LocalDateTime expirationDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Boolean toReturn = true ;

        if (currentDateTime.isAfter(expirationDateTime)) {
            toReturn = false ;
        }

        return toReturn ;
    }

}
