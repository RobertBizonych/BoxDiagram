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

    class MyRectangle extends GraphicsProgram {
        private GRect rectangle;
        private GLabel label;
        private GCompound compound;
        private double xPosition;
        private double yPosition;
        private static final double BOX_WIDTH = 120;
        private static final double BOX_HEIGHT = 50;
        private double xLabelPosition = BOX_WIDTH / 3;
        private double yLabelPosition = BOX_HEIGHT / 2;

        MyRectangle(String labelText, JPanel panel) {
            xPosition = (panel.getWidth() - BOX_WIDTH)/2;
            yPosition = (panel.getHeight() - BOX_HEIGHT)/2;
            xLabelPosition = xPosition + xLabelPosition;
            yLabelPosition = yPosition + yLabelPosition;

            rectangle = new GRect(xPosition, yPosition, BOX_WIDTH, BOX_HEIGHT);
            label = new GLabel(labelText, xLabelPosition, yLabelPosition);

            compound = new GCompound();
            compound.add(rectangle);
            compound.add(label);
            compound.setVisible(true);
        }

        public String getLabelText() {
            return label.getLabel();
        }

        public GCompound getCompound() {
            return compound;
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
  /*  class EventMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            MyRectangle grect = new MyRectangle(input.getText(),panel);
            rectangle = grect.getBounds2D();
            display(rect2);
            p1 = event.getX();
            q1 = event.getY();
        }
        public void mouseReleased(MouseEvent event) {
            rectangle = rect1.getBounds2D();
            rect2 = rect1;
            display(rect2);
        }
        public void mouseClicked(MouseEvent event) {
            rect2 = rect1;
            rectangle = rect1.getBounds2D();
            display(rect2);
        }
    }
    class EventMouseMotionListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent event) {
            if (rect1.contains(event.getX(), event.getY())) {
                rectangle = null;
                rect2 = rect1;
                p2 = event.getX();
                q2 = event.getY();
                p = p + p2 - p1;
                q = q + q2 - q1;
                p1 = p2;
                q1 = q2;
            }
            if (rect2 != null)
                display(rect2);
            canvas.repaint();
        }
        public void mouseMoved(MouseEvent event) {
            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        }
    }
  */  public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
        BoxDiagram converter = new BoxDiagram();
    }
}
