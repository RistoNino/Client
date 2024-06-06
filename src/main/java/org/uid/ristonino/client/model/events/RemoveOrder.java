package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;

public class RemoveOrder extends Event {
    public static final EventType<RemoveOrder> EVENT_TYPE = new EventType<>(Event.ANY, "REMOVE_ORDER");

    private final String id;

    public RemoveOrder(String orderId) {
        super(EVENT_TYPE);
        this.id = orderId;
    }

    public String getId() {
        return id;
    }
}
