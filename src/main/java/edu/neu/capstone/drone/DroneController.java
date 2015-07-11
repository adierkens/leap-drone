package edu.neu.capstone.drone;

/**
 * Abstract controller for a Drone
 * Implementations will be automatically registered to receive event notifications
 */
public abstract class DroneController {

    public DroneController() {

    }

    public abstract void setRoll(Integer roll);
    public abstract void setPitch(Integer pitch);
    public abstract void setYaw(Integer yaw);
    public abstract void setThrottle(Integer throttle);

}
