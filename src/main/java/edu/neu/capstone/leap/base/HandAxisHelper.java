package edu.neu.capstone.leap.base;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Hand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Adam on 8/13/15.
 */



public class HandAxisHelper {
    private static final Logger LOG = LoggerFactory.getLogger(HandAxisHelper.class);

    public enum Axis {ROLL, PITCH, YAW};

    private Axis axis;
    private RollingAverage rollingAverage;

    public HandAxisHelper(Axis axis, Integer averageCount) {
        this.axis = axis;
        rollingAverage = new RollingAverage(averageCount);
    }

    public void addHandPosition(Hand hand) {
        double val = 0;
        if (axis.equals(Axis.ROLL)) {
            val = calculateRoll(hand);
        }
        rollingAverage.add(val);
    }

    public Double average() {
        return rollingAverage.average();
    }

    public double calculateRoll(Hand hand) {

        double avgDeltaX = 0, avgDeltaY = 0;

        Finger lastFinger = null;

        for (Finger finger : hand.fingers()) {

            if (finger.equals(hand.fingers().leftmost()) || finger.equals(hand.fingers().rightmost()))
                continue;

            if (lastFinger == null) {
                lastFinger = finger;
                continue;
            }

            avgDeltaX += finger.tipPosition().getX() - lastFinger.tipPosition().getX();
            avgDeltaY += finger.tipPosition().getY() - lastFinger.tipPosition().getY();

            lastFinger = finger;
        }

        avgDeltaX = avgDeltaX/hand.fingers().count();
        avgDeltaY = avgDeltaY/hand.fingers().count();

        LOG.info(String.format("X: %s - Y: %s", avgDeltaX, avgDeltaY));

        return Math.atan(avgDeltaY/avgDeltaX);

    }
}
