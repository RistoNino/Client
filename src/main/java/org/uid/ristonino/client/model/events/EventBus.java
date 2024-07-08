package org.uid.ristonino.client.model.events;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private static EventBus instance;
    private final Map<EventType<?>, List<EventHandler<? extends Event>>> handlers;

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
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    public synchronized <T extends Event> void fireEvent(T event) {
        List<EventHandler<? extends Event>> eventHandlers = handlers.get(event.getEventType());
        if (eventHandlers != null) {
            for (EventHandler<? extends Event> handler : eventHandlers) {
                @SuppressWarnings("unchecked")
                EventHandler<T> eventHandler = (EventHandler<T>) handler;
                Platform.runLater(() -> eventHandler.handle(event));
            }
        }
    }

}
