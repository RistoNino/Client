package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.List;

public class MenuItemController {
    @FXML private ImageView image;
    @FXML private Button add;
    @FXML private Button remove;
    @FXML private Label quantity;
    @FXML private Label price;
    @FXML private Label total;
    @FXML private Label name;
    @FXML private Label description;
    @FXML private Label ingredients;

    @FXML
    public void initialize(String itemName, String category, String itemDescription, List<String> itemIngredients, Double itemPrice) {
        name.setText(itemName);
        description.setText(itemDescription);
        ingredients.setText(String.join(", ", itemIngredients));
        price.setText(String.format("%.2f", itemPrice));
    }
}
