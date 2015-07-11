package edu.neu.capstone.events;

/**
 * Created by Adam on 7/10/15.
 */
public interface EventListener<T> {
    void onEvent(T event);
}