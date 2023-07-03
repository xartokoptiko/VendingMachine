package com.karachristos.vending.vendingmachine.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PopupUtils {
    public static void showPopup(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

