package com.trainings;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.border.TitledBorder;
/**
 * Created by IntelliJ IDEA.
 * User: Tigra
 * Date: 16.02.12
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class MouseDragAndDrop extends JFrame {
    Canvas1 canvas;
    JLabel label;

    public MouseDragAndDrop() {
        super();
        Container container = getContentPane();
        canvas = new Canvas1();
        container.add(canvas);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        label = new JLabel("");
     //   panel.add(label);         don't need
        container.add(panel, BorderLayout.SOUTH);
        setSize(600,300);
        setVisible(true);
    }
    public static void main(String arg[]) {
        new MouseDragAndDrop();
    }

    class Canvas1 extends JPanel {
        double p, q, width, height;
        int p1, q1, p2, q2;
        Rectangle2D rect1,rect2;
        Rectangle2D rectangle;
        Cursor cursor;

        public Canvas1() {
            p = 25;
            q = 25;
            width = 90;
            height = 70;
            addMouseListener(new EventMouseListener());
            addMouseMotionListener(new EventMouseMotionListener());
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            rect1 = new Rectangle2D.Double(p, q, width, height);
            g2.draw(rect1);
           /* if (rectangle != null) {
                drawSquares(g2, rectangle);
            }
            if (cursor != null)
                setCursor(cursor);              una locura,no la necesito    */
        }
       /* public void drawSquares(Graphics2D g2, Rectangle2D rect) {
            double p = rect.getX();
            double q = rect.getY();
            double width = rect.getWidth();
            double height = rect.getHeight();
            g2.setColor(Color.black);                                   ??? la otra mierda
        }*/
        class EventMouseListener extends MouseAdapter {
            public void mousePressed(MouseEvent event) {
               // rectangle = rect1;                            //no hace nada
              //  rectangle = rect1.getBounds2D();
              //  display(rectangle);                        y eso
                p1 =event.getX();
                q1 =event.getY();

            }
            public void mouseReleased(MouseEvent event) {
               // rectangle = rect1.getBounds2D();
               // rect2 = rect1;
                //display(rectangle);
            }
            public void mouseClicked(MouseEvent event) {
                //rect2 = rect1;
                //rectangle = rect1.getBounds2D();
                //display(rectangle);
            }
        }


        class EventMouseMotionListener extends MouseMotionAdapter {
            public void mouseDragged(MouseEvent event) {
                if (rect1.contains(event.getX(), event.getY())) {
                   // rectangle = null;      y eso tambien para nada
                  //  rect2 = rect1;        joder,no sé para que está
                  //  rect1 = rectangle;
                  //  rect1 = null;
                    p2 = event.getX();
                    q2 = event.getY();
                    p = p + p2 - p1;
                    q = q + q2 - q1;
                    p1 = p2;
                    q1 = q2;

                    //rect1.setRect(p1,q1,width,height);
                }
                /*if (rect2 != null)
                    display(rect2);      no lo necesito por ahora */
                canvas.repaint();
            }
            public void mouseMoved(MouseEvent event) {
              //  cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);  qué es esto???
            }
        }
       /* public void display(Shape shape) {
            double p = rectangle.getX();
            double q = rectangle.getY();
            double width= rectangle.getWidth();
            double height = rectangle.getHeight();
            String st = "(" + Double.toString(p)+","+Double.toString(q) + ")";
            String sizeString = "(" + Double.toString(width) + ","+
                    Double.toString(height) + ")";
            label.setText(st);             no hace falta perder eso
        }*/
    }
}