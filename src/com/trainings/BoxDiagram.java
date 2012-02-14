package com.trainings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by IntelliJ IDEA.
 * User: Tigra
 * Date: 13.02.12
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class BoxDiagram implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JTextField input;
    private JTextArea output;
    private JLabel label;
    private JButton addButton, removeButton, clearButton;
    // Constructor
    public BoxDiagram() {
        // Create the frame and container.
        frame = new JFrame("BoxDiagram");
        panel = new JPanel();
        //panel.setLayout(new GridLayout(2, 2));
        // Add the widgets.
        addWidgets();
        // Add the panel to the frame.
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        // Exit when the window is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Show the converter.
        frame.pack();
        frame.setVisible(true);
    }
    // Create and add the widgets for converter.
    private void addWidgets() {
        // Create widgets.
        label = new JLabel("Program", SwingConstants.LEFT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        input = new JTextField(10);
        input.requestFocus();

        output = new JTextArea(25, 50);
        output.setEditable(false);

        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        clearButton = new JButton("Clear");

        // Listen to events from Convert addButton.
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        clearButton.addActionListener(this);
        // Add widgets to container.
        panel.add(output);
        panel.add(label);
        panel.add(input);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(clearButton);
    }

    // Implementation of ActionListener interface.
    public void actionPerformed(ActionEvent event) {
        String inputString = input.getText();
        output.append(inputString + "\n");
        input.setText("");
    }
    // main method
    public static void main(String[] args) {
        // set the look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
        }
        BoxDiagram converter = new BoxDiagram();
    }

}
