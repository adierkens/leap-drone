package edu.neu.capstone.drone.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam on 8/13/15.
 */
public class QuadFrame extends JFrame {
    private static final Logger LOG = LoggerFactory.getLogger(QuadFrame.class);

    public static final Integer MAX_HEIGHT = 500;
    public static final Integer MAX_WIDTH = 500;

    private QuadPanel quadPanel;

    public QuadFrame() {
        super("Quad Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        quadPanel = new QuadPanel();
        add(quadPanel);

        pack();
    }

    public void setLocation(int x, int y) {
        if (quadPanel != null) {
            quadPanel.setLocation(x, y);
        }
    }

    public void setLocation(Point p) {
        if (quadPanel != null) {
            quadPanel.setLocation(p);
        }
    }

    public Point getLocation() {
        if (quadPanel != null) {
            return quadPanel.getLocation();
        }
        return null;
    }

    private static class QuadPanel extends JPanel {

        private Rectangle rectangle;

        public QuadPanel() {
            rectangle = new Rectangle(MAX_WIDTH/2, MAX_HEIGHT/2, 10, 10);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(rectangle);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(MAX_WIDTH, MAX_HEIGHT);
        }

        public Point getLocation() {
            return rectangle.getLocation();
        }

        public void setLocation(int x, int y) {
            rectangle.setLocation(x, y);
            revalidate();
            repaint();
        }

        public void setLocation(Point p) {
            rectangle.setLocation(p);
            revalidate();
            repaint();
        }
    }
}
