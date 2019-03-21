package ru.geekbrains.java1.lesson7moba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Lesson7hw extends JFrame {
    public static void main(String[] args) {
        Lesson7hw window = new Lesson7hw();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        window.setBounds((d.width - 400)/2,(d.height - 400)/2,400,400);

        JButton jButton2 = new JButton("Save");
        JTextField jTextField = new JTextField();
        JTextField jTextFieldFamily = new JTextField("Enter your Family Name ...");
        JTextField jTextFieldName = new JTextField("Enter your Name ...");
        JTextField jTextFieldSecondName = new JTextField("Enter your Second name ...");

        JButton jButton1 = new JButton("FIO");
        jButton1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lesson7hw window2 = new Lesson7hw();
                window2.setLayout(new BorderLayout());
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new GridLayout(3,1));
                jPanel.add(jTextFieldFamily);
                jPanel.add(jTextFieldName);
                jPanel.add(jTextFieldSecondName);

                jButton2.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jTextField.setText(jTextFieldFamily.getText() + " " + jTextFieldName.getText() + " " + jTextFieldSecondName.getText());
                        window2.dispose();
                    }
                });

                window2.add(jButton2,BorderLayout.SOUTH);
                window2.add(jPanel, BorderLayout.NORTH);
                window2.setBounds((d.width - 400)/2 + 50,(d.height - 400)/2 + 50,400,400);
                window2.setVisible(true);
            }
        });



        window.setLayout(new BorderLayout());
        window.add(jButton1,BorderLayout.SOUTH);
        window.add(jTextField,BorderLayout.NORTH);
        window.setVisible(true);
    }
}
