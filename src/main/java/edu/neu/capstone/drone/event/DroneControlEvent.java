package edu.neu.capstone.drone.event;

import edu.neu.capstone.drone.DroneController;
import edu.neu.capstone.events.Event;

/**
 * Created by Adam on 7/11/15.
 */
public class DroneControlEvent extends Event {

    private Integer roll;
    private Integer pitch;
    private Integer yaw;

    public DroneControlEvent(Integer r, Integer p, Integer y) {
        this.roll = r;
        this.pitch = p;
        this.yaw = y;
    }

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public Integer getPitch() {
        return pitch;
    }

    public void setPitch(Integer pitch) {
        this.pitch = pitch;
    }

    public Integer getYaw() {
        return yaw;
    }

    public void setYaw(Integer yaw) {
        this.yaw = yaw;
    }
}
