package com.trainings;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by IntelliJ IDEA.
 * User: Tigra
 * Date: 13.02.12
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */
public class TextFieldEx extends JFrame {
    private JTextField input = new JTextField(15);
    private JTextArea output = new JTextArea(5, 15);

    public void init() {
        input.addActionListener(new TextFieldListener());
        this.getContentPane().add(input);
    }
    public TextFieldEx() {
        TextFieldListener textFieldListener = new TextFieldListener();
        input.addActionListener(textFieldListener);
        output.setEditable(false);
        this.getContentPane().add(input);
        this.getContentPane().add(output);
        input.requestFocus();
    }

    private class TextFieldListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String inputString = input.getText();
            output.append(inputString + "\n");
            input.setText("");
        }
    }
    public static void main(String[] args) {
        TextFieldEx textFieldEx = new TextFieldEx();
        textFieldEx.init();
    }
}

