package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;

public class OpenAccessibilityModal extends Event {
    public static final EventType<OpenAccessibilityModal> EVENT_TYPE = new EventType<>(Event.ANY, "OPEN_ACCESSIBILITY_MODAL");

    public OpenAccessibilityModal() {
        super(EVENT_TYPE);
    }
}
