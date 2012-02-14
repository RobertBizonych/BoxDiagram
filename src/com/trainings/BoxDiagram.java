package com.trainings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
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

    public BoxDiagram() {
        frame = new JFrame("BoxDiagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        addWidgets();

        frame.add(panel);
        frame.setSize(600,400);
        frame.setVisible(true);
    }

    private void addWidgets() {
        label = new JLabel("Program");
        input = new JTextField(10);
        input.requestFocus();

        output = new JTextArea();
        output.setEditable(false);

        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        clearButton = new JButton("Clear");

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        clearButton.addActionListener(this);

        label.setBounds(0, 285, 100, 50);
        input.setBounds(110, 300, 200, 25);
        addButton.setBounds(320, 300, 75, 25);
        removeButton.setBounds(400, 300, 95, 25);
        clearButton.setBounds(500, 300, 75, 25);
        output.setBounds(0, 0, 600, 250);

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

