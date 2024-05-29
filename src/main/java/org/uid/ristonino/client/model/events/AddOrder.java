package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;
import org.uid.ristonino.client.model.types.Order;

public class AddOrder extends Event {
    public static final EventType<AddOrder> ORDER_ADDED = new EventType<>(Event.ANY, "ADD_ORDER_LIST");

    private final String itemId;
    private final Order order;

    public AddOrder(String itemId, Order order) {
        super(ORDER_ADDED);
        this.itemId = itemId;
        this.order = order;
    }

    public String getItemId() {
        return itemId;
    }
    public Order getOrder() {
        return order;
    }
}
