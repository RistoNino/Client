package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;
import org.uid.ristonino.client.model.types.Cart;

public class UpdateCart extends Event {
    public static final EventType<UpdateCart> EVENT_TYPE = new EventType<>(Event.ANY, "UPDATED_CART");

    private final Cart cart;

    public UpdateCart(Cart cart) {
        super(EVENT_TYPE);
        this.cart = cart;
    }
    public double getTotal() {
        return cart.getTotal();
    }
}
