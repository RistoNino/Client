package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class CustomItemController {
    @FXML
    private VBox ingredientsBox;
    @FXML
    private VBox extraIngredientsBox;

    private List<String> ingredients;

    public void initialize(List<String> ingredients) {
        this.ingredients = ingredients;
        for (String ingredient : ingredients) {
            CheckBox checkBox = new CheckBox(ingredient);
            ingredientsBox.getChildren().add(checkBox);
        }
//        for (String extraIng : extraIngredients) {
//
//        }
    }

}
