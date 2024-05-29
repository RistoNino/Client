package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderController {
    @FXML private Label orderName;
    @FXML private Label orderPrice;
    @FXML private Label orderIngredients;
    @FXML private Label orderNotes;
    @FXML private Label orderQuantity;

    private int quantity = 0;

    @FXML
    public void initialize(String name, double price, String removeIngredients, String notes, int quantity) {
        orderName.setText(name);
        orderPrice.setText(String.valueOf(price));
        orderIngredients.setText(removeIngredients);
        orderNotes.setText(notes);
        orderQuantity.setText(String.valueOf(quantity));
        this.quantity = quantity;
    }

    public void setNewQuantity(int quantity) {
        this.quantity = quantity;
        orderQuantity.setText(String.valueOf(quantity) + "x");
    }
    public void setNewNotes(String notes) {
        orderNotes.setText(notes);
    }
    public void setNewIngredients(String ingredients) {
        orderIngredients.setText(ingredients);
    }
    public int getQuantity() {
        return this.quantity;
    }
}
