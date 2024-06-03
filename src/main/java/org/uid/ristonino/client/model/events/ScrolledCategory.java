package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ScrolledCategory extends Event {
    public static final EventType<ScrolledCategory> EVENT_TYPE = new EventType<>(Event.ANY, "SCROLLED_CATEGORY");

    private final String categoryName;

    public ScrolledCategory(String category) {
        super(EVENT_TYPE);
        this.categoryName = category;
    }
    public String getCategoryName() {
        return categoryName;
    }
}
