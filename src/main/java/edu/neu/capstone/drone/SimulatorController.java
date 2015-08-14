package edu.neu.capstone.drone;

import edu.neu.capstone.Utils;
import edu.neu.capstone.drone.event.DroneControlEvent;
import edu.neu.capstone.drone.simulator.QuadFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * Drone controller responsible for controlling the simulation
 */
public class SimulatorController extends DroneController {
    private static final Logger LOG = LoggerFactory.getLogger(SimulatorController.class);
    private static final Integer MAX_STEP_SIZE = 10;

    private QuadFrame quadFrame;

    public SimulatorController() {
        quadFrame = new QuadFrame();
        quadFrame.setVisible(true);
    }

    @Override
    public void setRoll(Integer roll) {
        LOG.info(String.format("roll: %s", roll));
        double diff = MAX_STEP_SIZE * (roll/(Utils.MAX_ROLL_DEGREES*1.0));
        move(diff, 0);
    }

    @Override
    public void setPitch(Integer pitch) {
        double diff = MAX_STEP_SIZE * (pitch/(Utils.MAX_ROLL_DEGREES*1.0));
        move(0, diff);
    }

    @Override
    public void setYaw(Integer yaw) {

    }

    @Override
    public void setThrottle(Integer throttle) {

    }

    private void move(double deltaX, double deltaY) {
        Point curPoint = quadFrame.getLocation();
        LOG.info(String.format("X: %s - Y: %s", curPoint.x, curPoint.y));
        curPoint.setLocation(curPoint.x + deltaX, curPoint.y + deltaY);
        LOG.info(String.format("delta X: %s - Y: %s", deltaX, deltaY));
        LOG.info(String.format("X: %s - Y: %s", curPoint.x, curPoint.y));

        quadFrame.setLocation(curPoint);
    }


}
