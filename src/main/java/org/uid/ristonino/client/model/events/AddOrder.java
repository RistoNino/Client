package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;

public class AddOrder extends Event {
    public static final EventType<AddOrder> ORDER_ADDED = new EventType<>(Event.ANY, "ADD_ORDER_LIST");

    private final String orderName;
    private final String ingredients;

    public AddOrder(String orderName, String ingredients) {
        super(ORDER_ADDED);
        this.orderName = orderName;
        this.ingredients = ingredients;
    }
    public String getOrderName() {
        return orderName;
    }
    public String getIngredients() {
        return ingredients;
    }
}
