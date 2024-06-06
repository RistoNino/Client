package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.RemoveOrder;

public class OrderController {
    @FXML private Label orderName;
    @FXML private Label orderPrice;
    @FXML private Label orderIngredients;
    @FXML private Label orderNotes;
    @FXML private Label orderQuantity;
    @FXML private Label orderStatus;
    @FXML private Button removeOrderButton;
    @FXML private HBox orderHeader;

    private int quantity = 0;

    private String orderId;

    private RemoveOrder removeOrderEvent;

    @FXML
    public void initialize(String id, String name, double price, String removeIngredients, String notes, int quantity) {
        if (id.contains("custom-")) {
            removeOrderButton.setVisible(true);
            removeOrderButton.setDisable(false);
            removeOrderEvent = new RemoveOrder(id);
        } else {
            orderHeader.getChildren().removeLast();
        }
        orderId = id;
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
    public void setOrderStatus(String status) {
        orderStatus.setText(status);
    }

    public void removeButtonOfRemove() {
        if (orderId.contains("custom-")) {
            orderHeader.getChildren().removeLast();
        }
    }

    @FXML
    public void removeOrder() {
        EventBus.getInstance().fireEvent(removeOrderEvent);
    }
}
