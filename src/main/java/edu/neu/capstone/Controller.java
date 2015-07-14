package edu.neu.capstone;

import edu.neu.capstone.drone.DroneController;
import edu.neu.capstone.drone.QuadcopterController;
import edu.neu.capstone.drone.SimulatorController;
import edu.neu.capstone.drone.event.DroneControlEvent;
import edu.neu.capstone.leap.LeapHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Adam on 5/28/15.
 */
public class Controller {
    private static Logger LOG = LoggerFactory.getLogger(Controller.class);

    private LeapHandler leapHandler;
    private DroneController droneController;

    public static void main(String[] args) {
        Controller controller = new Controller();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LOG.info("Starting controller...");
        LOG.info("Press 'Q' to quit");

        try {
            while (!br.readLine().replaceAll("\\r|\\n", "").equals("Q")) {
                continue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOG.info("Stopping controller");

    }

    public Controller() {
        leapHandler = new LeapHandler();
        droneController = new SimulatorController();
    }

}
