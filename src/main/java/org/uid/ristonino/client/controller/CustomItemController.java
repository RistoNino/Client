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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.uid.ristonino.client.model.events.AddOrder;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.RemoveModal;
import org.uid.ristonino.client.model.types.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomItemController {
    @FXML private StackPane customItemContainer;
    @FXML private Button buttonCloseModal;
    @FXML private Label customItemName;
    @FXML private Label customItemDescription;
    @FXML private Label customItemPrice;
    @FXML private VBox ingredientsBox;
    @FXML private Button removeCustomItem;
    @FXML private Button addCustomItem;
    @FXML private Label customItemQuantity;
    @FXML private Button addNote;
    @FXML private TextField customNotes;

    private static int idCustomItem = 0;

    private int itemQuantity = 0;

    private final GaussianBlur blur = new GaussianBlur();
    private final FontIcon closeIcon = new FontIcon("mdal-close");
    private final FontIcon addIcon = new FontIcon("mdoal-add");
    private final FontIcon removeIcon = new FontIcon("mdomz-minus");
    private final FontIcon editIcon = new FontIcon("mdal-edit");

    private AddOrder addOrder;
    private Order ordine;
    private String notes = "";
    private String temp;

    @FXML
    public void initialize(String itemName, String itemDescription, List<String> itemIngredients, double itemPrice) {
        idCustomItem++;
        ordine = new Order(itemName, 0, itemPrice, new ArrayList<>(), notes);
        addOrder = new AddOrder("custom-" + String.valueOf(idCustomItem), ordine);

        blur.setRadius(100);
        closeIcon.setIconSize(20);
        addIcon.setIconSize(20);
        removeIcon.setIconSize(20);
        editIcon.setIconSize(20);
        buttonCloseModal.setGraphic(closeIcon);
        addCustomItem.setGraphic(addIcon);
        removeCustomItem.setGraphic(removeIcon);
        addNote.setGraphic(editIcon);
        customItemContainer.setEffect(blur);

        customItemContainer.setOnMouseClicked(event -> {
            Node node = (Node) event.getTarget();
            if (node.getId() != null && node.getId().equals("customItemContainer")) {
                closeModal();
            }
        });

        customItemName.setText(itemName);
        customItemDescription.setText(itemDescription);
        customItemPrice.setText("€"+String.format("%.2f", itemPrice));

        for (String ingredient : itemIngredients) {
            CheckBox checkBox = new CheckBox(ingredient);
            checkBox.setSelected(true);
            checkBox.setOnAction(event -> {
                getNotSelectedIngredients();
                EventBus.getInstance().fireEvent(addOrder);
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
        EventBus.getInstance().fireEvent(addOrder);
        customItemQuantity.setText(String.valueOf(itemQuantity) + "x");
        customItemQuantity.setVisible(true);
    }

    @FXML
    private void removeCItem() {
        if (itemQuantity > 0) {
            itemQuantity--;
            ordine.setQuantity(itemQuantity);
            EventBus.getInstance().fireEvent(addOrder);
        }

        if (itemQuantity == 0) {
            customItemQuantity.setVisible(false);
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
                            EventBus.getInstance().fireEvent(addOrder);
                        }
                    }

                }
            });
        }
    }

}
