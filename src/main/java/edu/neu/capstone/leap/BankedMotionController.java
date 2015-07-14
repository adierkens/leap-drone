package edu.neu.capstone.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Adam on 7/11/15.
 */
public class BankedMotionController extends Listener {
    private static final Logger LOG = LoggerFactory.getLogger(BankedMotionController.class);

    @Override
    public void onConnect(Controller controller) {
        LOG.info("Connected");
    }

    public void onFrame(Controller controller) {
        LOG.info("New Frame");
    }
}
