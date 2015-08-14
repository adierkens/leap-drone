package edu.neu.capstone.leap;

import com.leapmotion.leap.*;
import edu.neu.capstone.leap.base.HandAxisHelper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Created by Adam on 7/15/15.
 */
public class PlotListener extends Listener {
    private static final Logger LOG = LoggerFactory.getLogger(PlotListener.class);
    private static final Integer ROLLING_AVG_COUNT = 10;
    private TimeSeries rollSeries;
    private TimeSeries pitchSeries;
    private TimeSeries yawSeries;
    private HandAxisHelper rollAxisHelper;
    private HandAxisHelper pitchAxisHelper;

    public PlotListener() {
        rollSeries = new TimeSeries("roll");
        pitchSeries = new TimeSeries("pitch");
        yawSeries = new TimeSeries("yaw");

        rollSeries.setMaximumItemCount(1000);
        pitchSeries.setMaximumItemCount(1000);
        yawSeries.setMaximumItemCount(1000);

        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        timeSeriesCollection.addSeries(rollSeries);
        timeSeriesCollection.addSeries(pitchSeries);
        timeSeriesCollection.addSeries(yawSeries);

        JFreeChart chart = ChartFactory.createTimeSeriesChart("Leap Motion Right Hand Graph", "Time", "Value", timeSeriesCollection, true, true, false);

        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel label = new ChartPanel(chart);
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);

        rollAxisHelper = new HandAxisHelper(HandAxisHelper.Axis.ROLL, ROLLING_AVG_COUNT);
        pitchAxisHelper = new HandAxisHelper(HandAxisHelper.Axis.PITCH, ROLLING_AVG_COUNT);

    }

    /**
     * Grab the frame and make a roll - pitch - yaw line graph
     * @param controller
     */
    public void onFrame(Controller controller) {

        Frame currFrame = controller.frame();

        if (currFrame.hands().count() == 0) {
            // Ignore frames without hands
            return;
        }

        for (Hand hand : currFrame.hands()) {
            if (hand.isRight()) {
                rollAxisHelper.addHandPosition(hand);
                pitchAxisHelper.addHandPosition(hand);
                RegularTimePeriod mili = new Millisecond();

                rollSeries.addOrUpdate(mili, rollAxisHelper.average());
                pitchSeries.addOrUpdate(mili, pitchAxisHelper.average());
                // yawSeries.addOrUpdate(mili, yaw);
            }
        }

    }
}
