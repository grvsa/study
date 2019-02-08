package ru.geekbrains.catch_the_drop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
// Try with GIT features
//Try GIT branches

public class GameWindow extends JFrame {

    private static GameWindow game_window;
    private static long last_frame_time;
    private static Image background;
    private static Image game_over;
    private static Image drop;
    private static float drop_left = 200;
    private static float drop_top = -100;
    private static float drop_v = 100;
    private static int score = 0;

    public static void main(String[] args) throws IOException {
	game_window = new GameWindow();
	background = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
	game_over = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
	drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
	game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	game_window.setLocation(200,100);
	game_window.setSize(906,478);
    game_window.setResizable(false);
    last_frame_time = System.nanoTime();
    GameField game_field = new GameField();
    game_field.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
//            super.mousePressed(e);
            int x = e.getX();
            int y = e.getY();
            if (x >= drop_left && x <= drop_left + drop.getWidth(null) && y >= drop_top && y <= drop_top + drop.getHeight(null)){
                drop_left = (float) Math.random() * (game_field.getWidth() - drop.getWidth(null));
                drop_top = -100;
                score++;
            }
            drop_v += 50;
        }

    });
    game_window.add(game_field);
    game_window.setVisible(true);

    }
    private static void onRepaint(Graphics g){
        long current_time = System.nanoTime();
        float delta_time = (current_time-last_frame_time) *0.000000001f;
        last_frame_time = current_time;
        drop_top += drop_v * delta_time;
//        if ((int) drop_top > 478) drop_top = -100;
        g.drawImage(background, 0, 0, null);
        g.drawImage(drop,(int) drop_left,(int)drop_top,null);
        game_window.setTitle(" " + score);
        if(drop_top > game_window.getHeight()) g.drawImage(game_over,280,120,null);
    }

    private static class GameField extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
