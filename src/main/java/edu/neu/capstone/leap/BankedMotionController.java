package edu.neu.capstone.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
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

        Frame currFrame = controller.frame();

        if (currFrame.hands().count() == 0) {
            // Ignore frames without hands
            return;
        }

        for (Hand hand : currFrame.hands()) {
            if (hand.isRight()) {

                float pitch = hand.direction().pitch();
                float yaw = hand.direction().yaw();
                float roll = hand.direction().roll();

                LOG.debug(String.format("Roll: %s, Pitch: %s, Yaw: %s", roll, pitch, yaw));

            }
        }

    }
}
