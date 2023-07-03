package com.karachristos.vending.vendingmachine.utils;

import com.karachristos.vending.vendingmachine.entities.Position;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;

public class CustomListCell extends ListCell<Position> {
    public CustomListCell(Pane product_pane, Label product_name) {
        setOnMouseClicked(event -> {
            if (!isEmpty()) {
                Position item = getItem();
                product_pane.setDisable(false);
                product_name.setText(item.getPosition_name());
                System.out.println(item.toString());
            }
        });
    }

    public void changeName(int Index, String text){
        setText(text);
    }

    @Override
    protected void updateItem(Position item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getPosition_name());
        }
    }
}

