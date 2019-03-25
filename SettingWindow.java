package ru.geekbrains.java1.TicTackToe;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.geekbrains.java1.TicTackToe.GameWindow.map;

public class SettingWindow extends JFrame {
    private final int wWidth = 250;
    private final int wHeight = 180;
    private static int xField = 10;
    private static int yField = 10;
    private static int win = 10;

    public SettingWindow(Component component) throws HeadlessException {
        setTitle("Setting Window");
        int x = (int)(component.getX());
        int y = (int)(component.getY());
        setResizable(false);
        setBounds(x + wWidth,y + 20,wWidth,wHeight);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(4,2));
        JRadioButton jRadioButton1 = new JRadioButton("Human vs Human",false);
        JRadioButton jRadioButton2 = new JRadioButton("Human vs AI",true);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        panel.add(jRadioButton1);
        panel.add(jRadioButton2);

        JLabel xFieldSize = new JLabel("Field Width - " + xField);
        panel.add(xFieldSize);
        JSlider xSlider = new JSlider(3,xField,xField);

        JLabel yFieldSize = new JLabel("Field Height - " + yField);
        panel.add(yFieldSize);
        JSlider ySlider = new JSlider(3,yField,yField);

        JLabel winSize = new JLabel("Win Size - " + win);
        JSlider wSlider = new JSlider(3,win,win);
        ySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                yField = ySlider.getValue();
                yFieldSize.setText("Field Height - " + yField);
                wSlider.setMaximum(xField > yField ? yField : xField);
            }
        });
        xSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                xField = xSlider.getValue();
                xFieldSize.setText("Field Width - " + xField);
                wSlider.setMaximum(xField > yField ? yField : xField);
            }
        });
        wSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                win = wSlider.getValue();
                winSize.setText("Win Length - " + win);
            }
        });
        panel.add(xSlider);
        panel.add(yFieldSize);
        panel.add(ySlider);
        panel.add(winSize);
        panel.add(wSlider);
        JButton start = new JButton("Start Game");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.setHumanVsAi(jRadioButton2.isSelected());
                map.setxField(xField);
                map.setyField(yField);
                map.setWin(win);
                map.setMap(new char[yField][xField]);
                map.setStep();
                setVisible(false);
                map.game();
            }
        });
        JPanel panel2 = new JPanel(new GridLayout(1,2));

        panel2.add(start);
        JButton contin = new JButton("Continue");
        contin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panel2.add(contin);
        add(panel,BorderLayout.NORTH);
        add(panel2,BorderLayout.SOUTH);
    }
}

