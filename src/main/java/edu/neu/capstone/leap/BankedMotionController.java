package edu.neu.capstone.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import edu.neu.capstone.drone.event.DroneControlEvent;
import edu.neu.capstone.events.EventDispatcher;
import edu.neu.capstone.leap.base.HandAxisHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Adam on 7/11/15.
 */
public class BankedMotionController extends Listener {
    private static final Logger LOG = LoggerFactory.getLogger(BankedMotionController.class);
    private static final Integer ROLLING_AVG_COUNT = 10;

    private HandAxisHelper rollAxisHelper;
    private HandAxisHelper pitchAxisHelper;

    public BankedMotionController() {
        rollAxisHelper = new HandAxisHelper(HandAxisHelper.Axis.ROLL, ROLLING_AVG_COUNT);
        pitchAxisHelper = new HandAxisHelper(HandAxisHelper.Axis.PITCH, ROLLING_AVG_COUNT);
    }

    private void update(Hand hand) {
        rollAxisHelper.addHandPosition(hand);
        pitchAxisHelper.addHandPosition(hand);

        DroneControlEvent droneControlEvent = new DroneControlEvent((int)Math.toDegrees(rollAxisHelper.average()), (int)Math.toDegrees(pitchAxisHelper.average()), null);
        EventDispatcher.getInstance().notify(droneControlEvent);
    }

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
                update(hand);
            }
        }

    }
}
