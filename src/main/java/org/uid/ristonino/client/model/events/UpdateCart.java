package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;

public class UpdateCart extends Event {
    public static final EventType<UpdateCart> EVENT_TYPE = new EventType<>(Event.ANY, "UPDATED_CART");

    public UpdateCart() {
        super(EVENT_TYPE);
    }
}
