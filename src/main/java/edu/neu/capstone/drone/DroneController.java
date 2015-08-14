package edu.neu.capstone.drone;

import edu.neu.capstone.drone.event.DroneControlEvent;
import edu.neu.capstone.events.EventDispatcher;
import edu.neu.capstone.events.EventListener;

/**
 * Abstract controller for a Drone
 * Implementations will be automatically registered to receive event notifications
 */
public abstract class DroneController implements EventListener<DroneControlEvent> {

    public DroneController() {
        EventDispatcher ed = EventDispatcher.getInstance();
        ed.listen(DroneControlEvent.class, this);
    }

    public abstract void setRoll(Integer roll);
    public abstract void setPitch(Integer pitch);
    public abstract void setYaw(Integer yaw);
    public abstract void setThrottle(Integer throttle);

    public void onEvent(DroneControlEvent event) {
        if (event != null) {

            if (event.getPitch() != null) {
                setPitch(event.getPitch());
            }

            if (event.getRoll() != null) {
                setRoll(event.getRoll());
            }

            if (event.getYaw() != null) {
                setYaw(event.getYaw());
            }
        }
    }
}
