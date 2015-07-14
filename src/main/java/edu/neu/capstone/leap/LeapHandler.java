package edu.neu.capstone.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;

/**
 *
 */
public class LeapHandler extends Listener implements AutoCloseable {

    private static final Class<? extends Listener> MotionControllerClass = BankedMotionController.class;

    private Controller controller;

    public LeapHandler() {
        controller = new Controller(this);

        try {
            Listener l = MotionControllerClass.newInstance();
            controller.addListener(l);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnect(Controller controller) {
        super.onConnect(controller);
    }

    @Override
    public void onFrame(Controller controller) {
        super.onFrame(controller);
    }

    public void close() throws Exception {
        controller.removeListener(this);
        controller.delete();
        controller = null;
    }
}
