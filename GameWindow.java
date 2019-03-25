package ru.geekbrains.java1.TicTackToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private SettingWindow settingWindow;
    private final int wWidth = 400;
    private final int wHeight = 500;
    protected static Map map;

    public static void main(String[] args) {
        GameWindow windowGame = new GameWindow();
    }

    public GameWindow() throws HeadlessException {
        setTitle("TicTackToe");
        map = new Map();

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            setResizable(false);
            setBounds((int)(toolkit.getScreenSize().getWidth() - wWidth) / 2,
                    (int) (toolkit.getScreenSize().getHeight() - wHeight) / 2,
                    wWidth,wHeight);
            settingWindow = new SettingWindow(this);
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(1,2));
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


            JButton gameSettings = new JButton("Settings");
            gameSettings.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settingWindow.setVisible(true);
                }
            });
            jPanel.add(gameSettings);
            jPanel.add(exitButton);
            add(jPanel,BorderLayout.SOUTH);
            add(map);
            setVisible(true);
    }
}
