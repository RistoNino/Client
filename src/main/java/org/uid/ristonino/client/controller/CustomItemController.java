package org.uid.ristonino.client.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.events.AddOrder;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.RemoveModal;
import org.uid.ristonino.client.model.types.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomItemController {
    @FXML private StackPane customItemContainer;
    @FXML private Button buttonCloseModal;
    @FXML private ImageView customItemImage;
    @FXML private Label customItemName;
    @FXML private Label customItemDescription;
    @FXML private Label customItemPrice;
    @FXML private VBox ingredientsBox;
    @FXML private Button removeCustomItem;
    @FXML private Button addCustomItem;
    @FXML private Label customItemQuantity;
    @FXML private Button addNote;
    @FXML private TextField customNotes;
    @FXML private Button orderCustomItem;

    private static int idCustomItem = 0;

    private int itemQuantity = 1;

    private int id;

    private AddOrder addOrder;
    private Order ordine;
    private String notes = "";
    private String temp;
    private double price;

    @FXML
    public void initialize(int id, String itemName, String itemDescription, List<String> itemIngredients, double itemPrice) {
        idCustomItem++;
        ordine = new Order(id, itemName, 1, itemPrice, new ArrayList<>(), notes);
        addOrder = new AddOrder("custom-" + String.valueOf(idCustomItem), ordine);
        price = itemPrice;

        customItemContainer.setOnMouseClicked(event -> {
            Node node = (Node) event.getTarget();
            if (node.getId() != null && node.getId().equals("customItemContainer")) {
                closeModal();
            }
        });

        Image image = new Image(getClass().getResource(Settings.SCENE_PATH + "images/background-login.png").toExternalForm());
        customItemImage.setImage(image);
        customItemName.setText(itemName);
        customItemDescription.setText(itemDescription);
        customItemPrice.setText("€"+String.format("%.2f", itemPrice));

        for (String ingredient : itemIngredients) {
            CheckBox checkBox = new CheckBox(ingredient);
            checkBox.setSelected(true);
            checkBox.setOnAction(event -> {
                getNotSelectedIngredients();
            });
            ingredientsBox.getChildren().add(checkBox);
        }

    }

    @FXML
    private void closeModal() {
        EventBus.getInstance().fireEvent(new RemoveModal());
    }

    @FXML
    private void addCItem() {
        itemQuantity++;
        ordine.setQuantity(itemQuantity);
        customItemQuantity.setText(String.valueOf(itemQuantity) + "x");
        customItemPrice.setText("€ " + String.format("%.2f", price*itemQuantity));
        if (orderCustomItem.isDisable()) {
            orderCustomItem.setDisable(false);
        }
    }

    @FXML
    private void removeCItem() {
        if (itemQuantity > 0) {
            itemQuantity--;
            ordine.setQuantity(itemQuantity);
            customItemPrice.setText("€ " + String.format("%.2f", price*itemQuantity));
        }

        if (itemQuantity == 0) {
            orderCustomItem.setDisable(true);
            customItemQuantity.setText("0x");
            customItemPrice.setText("€ " + String.format("%.2f", price));

        } else {
            customItemQuantity.setText(itemQuantity + "x");
        }
    }

    private void getNotSelectedIngredients() {
        List<String> notSelectedIngredients = new ArrayList<>();
        for (int i = 0; i < ingredientsBox.getChildren().size(); i++) {
            CheckBox checkBox = (CheckBox) ingredientsBox.getChildren().get(i);
            if (!checkBox.isSelected()) {
               notSelectedIngredients.add(checkBox.getText());
            }
        }
        ordine.setRemovedIngredients(notSelectedIngredients);
    }

    @FXML
    private void showNotes() {
        customNotes.setVisible(!customNotes.isVisible());
        if (customNotes.isVisible()) {
            //stackOverflow
            customNotes.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    //focus in
                    if (newValue) {
                        temp = customNotes.getText();
                    }

                    //focus out
                    if (oldValue) {
                        if (!(temp.equals(customNotes.getText()))) {
                            ordine.setNotes(customNotes.getText());
                        }
                    }

                }
            });
            addNote.setText("");
        } else {
            addNote.setText("Aggiungi Nota");
        }
    }

    @FXML
    private void sendOrder() {
        EventBus.getInstance().fireEvent(addOrder);
        closeModal();

    }

}
