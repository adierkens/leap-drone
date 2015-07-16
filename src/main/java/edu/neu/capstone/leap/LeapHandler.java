package edu.neu.capstone.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class LeapHandler extends Listener implements AutoCloseable {
    private static final Class<? extends Listener> MotionControllerClass = BankedMotionController.class;
    private static final Logger LOG = LoggerFactory.getLogger(LeapHandler.class);

    private Controller controller;
    private Listener listener;
    private PlotListener plotListener;

    public LeapHandler() {
        try {
            listener = MotionControllerClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        plotListener = new PlotListener();

        controller = new Controller();
        controller.addListener(listener);
        controller.addListener(plotListener);
    }

    public void close() throws Exception {

    }
}
