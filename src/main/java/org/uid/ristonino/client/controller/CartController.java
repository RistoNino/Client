package org.uid.ristonino.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.UpdateCart;
import org.uid.ristonino.client.model.events.UpdateOrders;

public class CartController {
    @FXML private Label totalPrice;

    @FXML
    public void initialize() {
        EventBus.getInstance().addEventHandler(UpdateCart.EVENT_TYPE,event -> {
            double total = ((UpdateCart) event).getTotal();
            totalPrice.setText(String.format("%.2f", total));
        });
    }

    public void sendOrders() {
        EventBus.getInstance().fireEvent(new UpdateOrders());
    }
}
