module com.karachristos.vending.vendingmachine {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.karachristos.vending.vendingmachine to javafx.fxml;
    opens com.karachristos.vending.vendingmachine.entities to com.google.gson;
    opens com.karachristos.vending.vendingmachine.entities.subentities to com.google.gson;



    exports com.karachristos.vending.vendingmachine;
    exports com.karachristos.vending.vendingmachine.utils;
    opens com.karachristos.vending.vendingmachine.utils to javafx.fxml;
}