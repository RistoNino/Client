package org.uid.ristonino.client.model.events;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.HashMap;
import java.util.Map;

// Ringraziamo internet

public class EventBus {
    private static EventBus instance;
    private final Map<EventType<?>, EventHandler<? extends Event>> handlers;

    private EventBus() {
        handlers = new HashMap<>();
    }

    public static synchronized EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public synchronized <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<? super T> handler) {
        handlers.put(eventType, handler);
    }

    public synchronized <T extends Event> void fireEvent(T event) {
        @SuppressWarnings("unchecked")
        EventHandler<T> handler = (EventHandler<T>) handlers.get(event.getEventType());
        if (handler != null) {
            handler.handle(event);
        }
    }
}