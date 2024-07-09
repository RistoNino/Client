package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.uid.ristonino.client.model.Settings;
import org.uid.ristonino.client.model.events.AddOrder;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.RemoveModal;
import org.uid.ristonino.client.model.types.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomItemController {
    @FXML private StackPane customItemContainer;
    @FXML private ImageView customItemImage;
    @FXML private Label customItemName;
    @FXML private Label customItemDescription;
    @FXML private Label customItemPrice;
    @FXML private VBox ingredientsBox;
    @FXML private Label customItemQuantity;
    @FXML private Button addNote;
    @FXML private TextField customNotes;
    @FXML private Button orderCustomItem;

    private static int idCustomItem = 0;

    private int itemQuantity = 1;

    private AddOrder addOrder;
    private Order ordine;
    private String temp;
    private double price;

    @FXML
    public void initialize(int id, String itemName, String itemDescription, List<String> itemIngredients, double itemPrice, Image itemImage) {
        idCustomItem++;
        String notes = "";
        ordine = new Order(id, itemName, 1, itemPrice, new ArrayList<>(), notes);
        addOrder = new AddOrder("custom-" + idCustomItem, ordine);
        price = itemPrice;

        customItemContainer.setOnMouseClicked(event -> {
            Node node = (Node) event.getTarget();
            if (node.getId() != null && node.getId().equals("customItemContainer")) {
                closeModal();
            }
        });
        Image image;
        image = Objects.requireNonNullElseGet(itemImage, () -> new Image(Settings.DEFAULT_IMAGE));


        customItemImage.setImage(image);
        customItemName.setText(itemName);
        customItemDescription.setText(itemDescription);
        customItemPrice.setText("€"+String.format("%.2f", itemPrice));

        for (String ingredient : itemIngredients) {
            CheckBox checkBox = new CheckBox(ingredient);
            checkBox.setSelected(true);
            checkBox.setOnAction(event -> getNotSelectedIngredients());
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
        customItemQuantity.setText(itemQuantity + "x");
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
            customNotes.focusedProperty().addListener((observable, oldValue, newValue) -> {

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
