package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateOrders extends Event {
    public static final EventType<UpdateOrders> EVENT_TYPE = new EventType<>(Event.ANY, "UPDATE_ORDERS");

    public UpdateOrders() {
        super(EVENT_TYPE);
    }
}
