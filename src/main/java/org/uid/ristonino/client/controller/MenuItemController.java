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
import javafx.util.Duration;
import org.uid.ristonino.client.model.Debug;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.events.*;
import org.uid.ristonino.client.model.types.Flag;
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
    @FXML private HBox flagsImage;

    private int itemQuantity = 0;

    private Timeline timelineCenter;
    private Timeline timelineTop;

    private FadeTransition fadeInRemove;
    private FadeTransition fadeOutRemove;
    private double itemPrice = 0.00;

    private AddOrder addOrder;
    private Order ordine;
    private Image image;


    @FXML
    public void initialize(int itemId, String itemName, String itemDescription, List<String> itemIngredients, Double priceItem, List<Flag> flags, Image itemImage) {
        name.setText(itemName);
        description.setText(itemDescription);
        ingredients.setText(String.join(", ", itemIngredients));
        itemPrice = priceItem;
        price.setText("€" + String.format("%.2f", itemPrice));
        itemView.setMaxHeight(60);
        if (Debug.IS_ACTIVE || itemImage == null) {

            image = new Image(Settings.DEFAULT_IMAGE);
        } else {
            image = itemImage;
        }
        imageItem.setImage(image);

        if (!flags.isEmpty()) {
            flagsImage.setVisible(true);
            flagsImage.setManaged(true);
            for (Flag flag : flags) {
                Image image;
                if (flag.flagImage() != null) {
                    image = flag.flagImage();
                } else {
                    image = new Image(Settings.DEFAULT_IMAGE_FLAG);
                }
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(16);
                imageView.setFitWidth(16);
                flagsImage.getChildren().add(imageView);
            }
        }

        ordine = new Order(itemId, itemName, 0, itemPrice, new ArrayList<>(), "");
        addOrder = new AddOrder("item-" + itemId, ordine);
        doAnimation();
        itemView.setOnMouseClicked(event -> EventBus.getInstance().fireEvent(
                new CreateCustomItem(itemId, itemName, itemDescription, itemIngredients, priceItem, itemImage)));
        EventBus.getInstance().addEventHandler(UpdateOrders.EVENT_TYPE, event -> resetItem());
    }

    @FXML
    private void addItem() {
        itemQuantity++;
        ordine.setQuantity(itemQuantity);
        EventBus.getInstance().fireEvent(addOrder);
        quantity.setText(itemQuantity + "x");
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


    private void resetItem() {
        if (itemQuantity > 0) {
            itemQuantity = 0;
            ordine.setQuantity(itemQuantity);
            timelineCenter.play();
            itemView.getStyleClass().remove("addedItem");
            fadeOutRemove.play();
            quantity.setVisible(false);
            remove.setVisible(false);
        }
    }

}
