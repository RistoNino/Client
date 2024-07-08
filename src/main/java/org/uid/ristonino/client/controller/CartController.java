package org.uid.ristonino.client.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.uid.ristonino.client.model.events.EventBus;
import org.uid.ristonino.client.model.events.UpdateCart;
import org.uid.ristonino.client.model.events.UpdateOrders;
import org.uid.ristonino.client.model.types.Cart;

public class CartController {
    @FXML private Label totalPrice;
    private Cart cart = Cart.getInstance();

    @FXML
    public void initialize() {

        EventBus.getInstance().addEventHandler(UpdateCart.EVENT_TYPE,event -> {
            double total = cart.getTotal();
            updateLabelWithAnimation(totalPrice, String.format("%.2f", total));
            //totalPrice.setText(String.format("%.2f", total));
        });
    }

    private void updateLabelWithAnimation(Label label, String newValue) {
        // Create a fade-out transition
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), label);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Create a scale transition (pop effect)
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(250), label);
        scaleUp.setToX(1.5);
        scaleUp.setToY(1.5);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(250), label);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        // Create a fade-in transition
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), label);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        // Change the label text after fade out
        fadeOut.setOnFinished(event -> label.setText(newValue));

        // Sequential transition (fade out -> change text -> scale up -> scale down -> fade in)
        SequentialTransition sequentialTransition = new SequentialTransition(fadeOut, scaleUp, scaleDown, fadeIn);
        sequentialTransition.play();
    }

    public void sendOrders() {
        EventBus.getInstance().fireEvent(new UpdateOrders());
    }
}
