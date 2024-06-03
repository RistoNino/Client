package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;

public class RemoveModal extends Event{
    public static final EventType<RemoveModal> EVENT_TYPE = new EventType<>(Event.ANY, "REMOVE_MODAL");

    public RemoveModal() {
        super(EVENT_TYPE);
    }
}
