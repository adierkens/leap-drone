package edu.neu.capstone.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam on 7/10/15.
 */
public class EventDispatcher {

    private static EventDispatcher instance;
    private final Map<Class<? extends Event>, List<EventListener>> eventListenerMap = new HashMap<Class<? extends Event>, List<EventListener>>();

    /**
     * Hide the constructor
     */
    private EventDispatcher() {

    }

    public static EventDispatcher getInstance() {
        if (instance == null) {
            instance = new EventDispatcher();
        }
        return instance;
    }

    public <T extends Event> void listen(Class<T> event, EventListener<T> listener) {
        final List<EventListener> listeners = getListeners(event);
        synchronized (listeners) {
            if (!listeners.contains(listener)) {
                listeners.add(listener);
            }
        }
    }

    public <T extends Event> void notify(final T event) {
        for (EventListener<T> listener : getListeners(event.getClass())) {
            listener.onEvent(event);
        }
    }

    private List<EventListener> getListeners(Class<? extends Event> event) {
        synchronized (eventListenerMap) {
            if (!eventListenerMap.containsKey(event)) {
                List<EventListener> empty = new ArrayList<EventListener>();
                eventListenerMap.put(event, empty);
            }
            return eventListenerMap.get(event);
        }
    }

}
