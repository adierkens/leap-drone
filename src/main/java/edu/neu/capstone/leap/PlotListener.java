package edu.neu.capstone.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
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
    private TimeSeries rollSeries;
    private TimeSeries pitchSeries;
    private TimeSeries yawSeries;

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

                float pitch = hand.direction().pitch();
                float yaw = hand.direction().yaw();
                float roll = hand.direction().roll();

                RegularTimePeriod mili = new Millisecond();

                rollSeries.addOrUpdate(mili, roll);
                pitchSeries.addOrUpdate(mili, pitch);
                yawSeries.addOrUpdate(mili, yaw);
            }
        }

    }
}
