package org.uid.ristonino.client.controller;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.events.AddOrder;
import org.uid.ristonino.client.model.events.CreateCustomItem;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.types.Order;
import java.util.ArrayList;
import java.util.List;

public class MenuItemController {
    @FXML private ImageView imageItem;
    @FXML private Button add;
    @FXML private Button remove;
    @FXML private Label quantity;
    @FXML private Label price;
    @FXML private Label name;
    @FXML private Label description;
    @FXML private Label ingredients;
    @FXML private HBox itemView;

    private int itemQuantity = 0;

    private final FontIcon addIcon = new FontIcon("mdoal-add");
    private final FontIcon removeIcon = new FontIcon("mdomz-minus");

    private Timeline timelineCenter;
    private Timeline timelineTop;

    private FadeTransition fadeInRemove;
    private FadeTransition fadeOutRemove;
    private double itemPrice = 0.00;

    private AddOrder addOrder;
    private Order ordine;


    @FXML
    public void initialize(int itemId, String itemName, String itemDescription, List<String> itemIngredients, Double priceItem) {
        name.setText(itemName);
        description.setText(itemDescription);
        ingredients.setText(String.join(", ", itemIngredients));
        itemPrice = priceItem;
        price.setText("€" + String.format("%.2f", itemPrice));
        itemView.setMaxHeight(60);
        addIcon.setIconColor(Color.WHITE);
        removeIcon.setIconColor(Color.WHITE);
        add.setGraphic(addIcon);
        remove.setGraphic(removeIcon);
        Image image = new Image(getClass().getResource(Settings.SCENE_PATH + "images/background-login.png").toExternalForm());
        imageItem.setImage(image);
        ordine = new Order(itemName, 0, itemPrice, new ArrayList<>(), "");
        addOrder = new AddOrder("item-" + itemId, ordine);
        doAnimation();
        itemView.setOnMouseClicked(event -> {
            EventBus.getInstance().fireEvent(new CreateCustomItem(itemName, itemDescription, itemIngredients, priceItem));
        });
    }

    @FXML
    private void addItem() {
        itemQuantity++;
        ordine.setQuantity(itemQuantity);
        EventBus.getInstance().fireEvent(addOrder);
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
            ordine.setQuantity(itemQuantity);
        }
        EventBus.getInstance().fireEvent(addOrder);

        if (itemQuantity == 0) {
            timelineCenter.play();
            itemView.getStyleClass().remove("addedItem");
            fadeOutRemove.play();
            quantity.setVisible(false);
            remove.setVisible(false);
        } else {
            quantity.setText(itemQuantity + "x");
        }
    }

    private void doAnimation() {
        fadeInRemove = new FadeTransition(Duration.millis(250), remove);
        fadeInRemove.setFromValue(0);
        fadeInRemove.setToValue(1.0);
        fadeOutRemove = new FadeTransition(Duration.millis(250), remove);
        fadeOutRemove.setFromValue(1.0);
        fadeOutRemove.setToValue(0);
        KeyValue keyValueCenter = new KeyValue(add.translateYProperty(), 25);
        KeyValue keyValueTop = new KeyValue(add.translateYProperty(), 0);
        // Sposta add al centro all'inizio
        KeyFrame keyFrameCenter = new KeyFrame(Duration.millis(250), keyValueCenter);
        KeyFrame keyFrameTop = new KeyFrame(Duration.millis(250), keyValueTop);
        timelineCenter = new Timeline(keyFrameCenter);
        timelineTop = new Timeline(keyFrameTop);
        timelineCenter.play();
    }

}
