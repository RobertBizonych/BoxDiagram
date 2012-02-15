package com.trainings;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.program.Program;

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
public class BoxDiagram implements ActionListener{
    private JTextField input;
    //Создаем классовую переменную в которой храним canvas
    private MyCanvas canvas;

    class MyCanvas extends GraphicsProgram implements ActionListener {
        GCanvas gCanvas;
        MyCanvas() {
            gCanvas = new GCanvas();
           /* int width = gCanvas.getWidth()/2;
            int height = gCanvas.getHeight()/2;*/
           // gCanvas.setBackground(Color.green);
            gCanvas.setVisible(true);
            GRect rect = new GRect(50, 50, 50, 50);
            GLabel gLabel = new GLabel("Test", 60,70);
            gCanvas.add(rect);
            gCanvas.add(gLabel);
        }

        public void clearCanvas(){
            //Будешь вызывать для очистки канваса
        }
        public GCanvas getGCanvas(){
            return gCanvas;
        }
        public void addElement(MyRectangle rect) {
            //псевдо метод add. Посмотри как в ACM добавлять элемент к GCanvas
            // Разберись каким образом можно запихнуть GRectangle в GCanvas
            GRectangle realRectangle = rect.getGRectangle();
            gCanvas.add(rect);
        }
    }

    class MyRectangle extends Canvas {
        GRectangle gRectangle;
        MyRectangle(){
            //Создаешь GRectangle, GLable, GCompound
           gRectangle = new GRectangle(70,70);
            // и т.д.
        }
        public GRectangle getGRectangle(){
            return gRectangle;
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
        canvas = new MyCanvas();
        panel.add(canvas.getGCanvas());

        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setBackground(Color.white);
        contentPane.add(panel, BorderLayout.CENTER);
    }

    // Запускаешь метод когда пользователь нажимает на кнопку
    public void drawRectangle(){
        MyRectangle rectangle = new MyRectangle();
        canvas.add(rectangle);
    }

    public void createBoxPanel(Container contentPane) {
        JLabel label = new JLabel("Program");
        JTextField input = new JTextField(10);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton clearButton = new JButton("Clear");
        input.addActionListener(this);
        addButton.addActionListener(this);
        /*    @Override
            public void actionPerformed(ActionEvent e) {
                JDialog d = new JDialog(, "Hello", true);
                d.setLocationRelativeTo(frame);
                d.setVisible(true);
            }
        });*/
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
    public void actionPerformed (ActionEvent event) {
        /*GRect rect = new GRect(30,30,30,30);
        rect.setFilled(true);
        rect.setVisible(true);*/
        String inputString = input.getText();
        if (event.getActionCommand().equals("Add")){
            canvas.gCanvas.add(new GRect(70,70,70,70));
            canvas.gCanvas.add(new GLabel(inputString,80,80));
            input.setText("");
        }
    }

    // main method
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
        }
        BoxDiagram converter = new BoxDiagram();
    }
}
