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
    private JTextField input;

    class MyCanvas extends JComponent {

        public void paint(Graphics g) {
            g.drawRect(10, 10, 200, 200);
        }

    }

    public BoxDiagram() {
        JFrame frame = new JFrame("BoxDiagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createCanvasPanel(frame.getContentPane());
        createBoxPanel(frame.getContentPane());

        frame.pack();
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public void createCanvasPanel(Container contentPane) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new MyCanvas());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setBackground(Color.white);
        contentPane.add(panel, BorderLayout.CENTER);
    }

    public void createBoxPanel(Container contentPane) {
        JLabel label = new JLabel("Program");
        JTextField input = new JTextField(10);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton clearButton = new JButton("Clear");

//        addButton.addActionListener(this);
//        removeButton.addActionListener(this);
//        clearButton.addActionListener(this);

        Box box = Box.createHorizontalBox();
        box.add(label);
        box.add(input);
        box.add(addButton);
        box.add(removeButton);
        box.add(clearButton);
        contentPane.add(box, BorderLayout.PAGE_END);
    }

    // Implementation of ActionListener interface.
    public void actionPerformed(ActionEvent event) {
        String inputString = input.getText();

        input.setText("");
    }

    // main method
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
        BoxDiagram converter = new BoxDiagram();
    }

}

