package com.trainings;
import acm.graphics.*;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Created by IntelliJ IDEA.
 * User: Tigra
 * Date: 13.02.12
 * Time: 17:31
 * To change this template use File | Settings | File Templates.
 */
public class BoxDiagram implements ActionListener {
    private JTextField input;
    private MyCanvas canvas;
    private JPanel panel;

    class MyCanvas extends GraphicsProgram {
        private GCanvas myCanvas;
        private LinkedHashMap<String, MyRectangle> rectangles;

        MyCanvas() {
            myCanvas = new GCanvas();
            rectangles = new LinkedHashMap<String, MyRectangle>();
            myCanvas.setVisible(true);
        }

        public void clear() {
            myCanvas.removeAll();
            rectangles.clear();
        }

        public void removeElement(String rectangleLabel){
            MyRectangle rect = rectangles.get(rectangleLabel);
            if(rect != null){
                rectangles.remove(rectangleLabel);
                myCanvas.remove(rect.getCompound());
            }
        }

        public GCanvas getGCanvas() {
            return myCanvas;
        }

        public void addElement(MyRectangle myRectangle) {
            String label = myRectangle.getLabelText();
            boolean rectangleDefined = rectangles.keySet().contains(label);
            if(!rectangleDefined){
                rectangles.put(label, myRectangle);
                myCanvas.add(myRectangle.getCompound());
            }
        }

    }

    class MyRectangle extends GraphicsProgram implements MouseListener, MouseMotionListener {
        private GPoint last;
        private boolean drag;
        private GRect rectangle;
        private GLabel label;
        private GCompound compound;
        private final double BOX_WIDTH = 120;
        private final double BOX_HEIGHT = 50;
        private final double xLabelPosition = BOX_WIDTH / 3;
        private final double yLabelPosition = BOX_HEIGHT / 2;

        MyRectangle(String labelText, JPanel panel) {
            double xPosition = (panel.getWidth() - BOX_WIDTH)/2;
            double yPosition = (panel.getHeight() - BOX_HEIGHT)/2;
            double labelX = xPosition + xLabelPosition;
            double labelY = yPosition + yLabelPosition;

            rectangle = new GRect(xPosition, yPosition, BOX_WIDTH, BOX_HEIGHT);
            label = new GLabel(labelText, labelX, labelY);

            compound = new GCompound();
            compound.add(rectangle);
            compound.add(label);
            rectangle.addMouseListener(this);
            rectangle.addMouseMotionListener(this);
            compound.setVisible(true);
        }

        public String getLabelText() {
            return label.getLabel();
        }

        public GCompound getCompound() {
            return compound;
        }

        public void mousePressed(MouseEvent e) {
            drag = true;
            last = new GPoint(e.getPoint());
        }

        public void mouseReleased(MouseEvent e) {
            drag = false;
            System.out.println("rectXel" + (rectangle.getX()) + " rectYel " + (rectangle.getY()));
        }

        public void mouseDragged(MouseEvent e) {
            if (drag) {
                double currentX = last.getX();
                double currentY = last.getY();
                rectangle.setBounds(currentX, currentY, BOX_WIDTH, BOX_HEIGHT);

                double labelY = rectangle.getY() + yLabelPosition;
                double labelX = rectangle.getX() + xLabelPosition;
                label.setLocation(labelX, labelY);
                last = new GPoint(e.getPoint());
            }
        }

        public void mouseMoved(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
            if (!drag) rectangle.sendToFront();
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
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        canvas = new MyCanvas();
        panel.add(canvas.getGCanvas());

        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setBackground(Color.white);
        contentPane.add(panel, BorderLayout.CENTER);
    }

    public void createBoxPanel(Container contentPane) {
        JLabel label = new JLabel("Program");
        input = new JTextField(10);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton clearButton = new JButton("Clear");

        input.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        clearButton.addActionListener(this);

        Box box = Box.createHorizontalBox();
        box.add(label);
        box.add(input);
        box.add(addButton);
        box.add(removeButton);
        box.add(clearButton);
        contentPane.add(box, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent event) {
        String inputString = input.getText();

        if (event.getActionCommand().equals("Add")) {
            MyRectangle rectangle = new MyRectangle(inputString, panel);
            canvas.addElement(rectangle);
        }
        if (event.getActionCommand().equals("Remove")) {
            canvas.removeElement(inputString);
        }
        if (event.getActionCommand().equals("Clear")) {
            canvas.clear();
        }
        input.setText("");
        input.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
        BoxDiagram converter = new BoxDiagram();
    }
}
