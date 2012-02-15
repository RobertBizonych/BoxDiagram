package com.trainings;

/**
 * Created by IntelliJ IDEA.
 * User: Tigra
 * Date: 2/15/12
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class BoxLayOut implements ItemListener {

    protected static int NUM_COMPONENTS = 3;
    protected static float[] xAlignment = {
            Component.LEFT_ALIGNMENT,
            Component.CENTER_ALIGNMENT,
            Component.RIGHT_ALIGNMENT};
    protected static float[] hue = {0.0f, 0.33f, 0.67f};
    protected static boolean restrictSize = true;
    protected static boolean sizeIsRandom = false;
    protected static BLDComponent[] bldComponent =
            new BLDComponent[NUM_COMPONENTS];

    public void populateContentPane(Container contentPane) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //Create the rectangles.
        int shortSideSize = 15;
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            if (sizeIsRandom) {
                shortSideSize = (int)(30.0 * Math.random()) + 30;
            } else {
                shortSideSize += 10;
            }
            bldComponent[i] = new BLDComponent(xAlignment[i], hue[i],
                    shortSideSize,
                    restrictSize,
                    sizeIsRandom,
                    String.valueOf(i));
            panel.add(bldComponent[i]);
        }

        //Create the instructions.
        JLabel label = new JLabel("Click a rectangle to "
                + "change its X alignment.");
        JCheckBox cb = new JCheckBox("Restrict maximum rectangle size.");
        cb.setSelected(restrictSize);
        cb.addItemListener(this);

        panel.setBorder(BorderFactory.createLineBorder(Color.red));

        Box box = Box.createVerticalBox();
        box.add(label);
        box.add(cb);

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(box, BorderLayout.PAGE_END);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            restrictSize = true;
        } else {
            restrictSize = false;
        }
        notifyBLDComponents();
    }

    static public void notifyBLDComponents() {
        for (int i = 0; i < NUM_COMPONENTS; i++) {
            bldComponent[i].setSizeRestriction(restrictSize);
        }
        bldComponent[0].revalidate();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("BoxLayoutDemo2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        BoxLayOut demo = new BoxLayOut();
        demo.populateContentPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
