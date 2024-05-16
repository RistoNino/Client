package org.uid.ristonino.client.controller;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

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
    @FXML private HBox itemView;

    private int itemQuantity = 0;
    private KeyValue keyValueCenter;
    private Timeline timelineCenter;
    private KeyValue keyValueTop;
    private Timeline timelineTop;

    private FadeTransition fadeInRemove;
    private FadeTransition fadeOutRemove;



    @FXML
    public void initialize(String itemName, String category, String itemDescription, List<String> itemIngredients, Double itemPrice) {
        name.setText(itemName);
        description.setText(itemDescription);
        ingredients.setText(String.join(", ", itemIngredients));
        price.setText(String.format("%.2f", itemPrice));
        itemView.setMaxHeight(60);

        // Animazioni
        fadeInRemove = new FadeTransition(Duration.millis(250), remove);
        fadeInRemove.setFromValue(0);
        fadeInRemove.setToValue(1.0);
        fadeOutRemove = new FadeTransition(Duration.millis(250), remove);
        fadeOutRemove.setFromValue(1.0);
        fadeOutRemove.setToValue(0);
        keyValueCenter = new KeyValue(add.translateYProperty(), 25);
        keyValueTop = new KeyValue(add.translateYProperty(), 0);
        // Sposta add al centro all'inizio
        KeyFrame keyFrameCenter = new KeyFrame(Duration.millis(250), keyValueCenter);
        KeyFrame keyFrameTop = new KeyFrame(Duration.millis(250), keyValueTop);
        timelineCenter = new Timeline(keyFrameCenter);
        timelineTop = new Timeline(keyFrameTop);
        timelineCenter.play();
    }

    @FXML
    private void addItem() {
        itemQuantity++;
        quantity.setText(String.valueOf(itemQuantity) + "x");
        if (itemQuantity == 1) {
            timelineTop.play();
            itemView.getStyleClass().add("addedItem");
            quantity.setVisible(true);
            fadeInRemove.play();
            remove.setVisible(true);
        }
    }

    @FXML
    private void removeItem() {
        if (itemQuantity > 0) {
            itemQuantity--;
        }
        quantity.setText(String.valueOf(itemQuantity) + "x");
        if (itemQuantity == 0) {
            timelineCenter.play();
            itemView.getStyleClass().remove("addedItem");
            fadeOutRemove.play();
            quantity.setVisible(false);
            remove.setVisible(false);
        }
    }
}
