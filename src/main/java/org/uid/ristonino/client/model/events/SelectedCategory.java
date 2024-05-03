package org.uid.ristonino.client.model.events;

import javafx.event.Event;
import javafx.event.EventType;

public class SelectedCategory extends Event {
    public static final EventType<SelectedCategory> EVENT_TYPE = new EventType<>(Event.ANY, "SELECTED_CATEGORY");

    private final String categoryName;

    public SelectedCategory(String category) {
        super(EVENT_TYPE);
        this.categoryName = category;
    }
    public String getCategoryName() {
        return categoryName;
    }
}
