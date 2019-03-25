package ru.geekbrains.java1.TicTackToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Map extends JPanel {
    private int xField = 3;
    private int yField = 3;
    private boolean humanVsAi = true;
    private char[][] map = new char[3][3];
    private int win = 3;
    private int[][] playerLines;
    private int[][] aiLines;
    private int step = 0;
    private int playerX = -1;
    private int playerY = -1;

    static char PLAYER_DOT = 'x';
    static char AI_DOT = 'o';
    static char EMPTY_DOT = ' ';

    static Random random = new Random();


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintField(g);
        repaint();
    }
    public void setStep(){
        step = 0;
    }
    public void paintField(Graphics g){
        int width = (int) getSize().getWidth();
        int height = (int) getSize().getHeight();
        int x = (int) getSize().getWidth()/xField;
        int y = (int) getSize().getHeight()/yField;
        for (int i = 0; i < xField; i++) {
            g.drawLine(x * i,0,x*i,height);
        }

        for (int i = 0; i < yField; i++) {
            g.drawLine(0, y * i, width, y * i);
        }
        if (map != null) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if(map[i][j] == 'x'){
                        g.drawLine(x * j + 10, y * i + 10,x * j + x - 10,y * i + y - 10);
                        g.drawLine(x * j + x - 10,y * i + 10,x * j + 10,y * i + y - 10);
                    }else if(map[i][j] == 'o'){
                        g.drawOval(x * j + 10,y*i + 10, x-20,y-20);
                    }
                }

            }
        }
    }
    public void game(){
        initLines();
//        while (true) {
//            repaint();
//            playerStep();
//            if(checkWin(PLAYER_DOT)) {
//                System.out.println("Player WIN!");
//                break;
//            }
//            if(isDraw()) {
//                System.out.println("DRAW");
//                break;
//            }
//
//            aiStep();
//            if(checkWin(AI_DOT)) {
//                System.out.println("SKYNET WIN!");
//                break;
//            }
//            if(isDraw()) {
//                System.out.println("DRAW");
//                break;
//            }
//
//        }
    }

    public void setxField(int xField) {
        this.xField = xField;
    }

    public void setyField(int yField) {
        this.yField = yField;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setHumanVsAi(boolean humanVsAi) {
        this.humanVsAi = humanVsAi;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public Map() {
        initLines();
            addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = (int) getSize().getWidth() / xField;
                int y = (int) getSize().getHeight() / yField;
                playerX = e.getX() / x;
                playerY = e.getY() / y;
                if(humanVsAi) {
                    if (!checkWin(PLAYER_DOT) && !checkWin(AI_DOT) && !isDraw()) {
                        if (step % 2 == 0) {
                            if (isCellValid(playerY, playerX)) {
                                updateLines(playerX, playerY, PLAYER_DOT);
                                setSym(playerY, playerX, PLAYER_DOT);
                                step++;
                                winner();
                                if (!checkWin(PLAYER_DOT) && !isDraw()) {
                                    aiStep();
                                    winner();
                                }
                            }
                        }
                    } else {
                        winner();
                    }
                }else{
                    if (!checkWin(PLAYER_DOT) && !checkWin(AI_DOT) && !isDraw()) {
                        if (step % 2 == 0) {
                            if (isCellValid(playerY, playerX)) {
                                updateLines(playerX, playerY, PLAYER_DOT);
                                setSym(playerY, playerX, PLAYER_DOT);
                                step++;
                                winner();
                            }
                        }else{
                            if (isCellValid(playerY, playerX)) {
                                updateLines(playerX, playerY, AI_DOT);
                                setSym(playerY, playerX, AI_DOT);
                                step++;
                                winner();
                            }
                        }
                    } else {
                        winner();
                    }
                }
                repaint();
            }
        });
        setBackground(Color.ORANGE);
    }


    // метод для установки символа
    private void winner(){
        JFrame jFrame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        jFrame.setBounds((int) toolkit.getScreenSize().getWidth()/2,(int)toolkit.getScreenSize().getHeight()/2,400,100);
        if(isDraw()){
            jFrame.setTitle("DRAW !!!");
            jFrame.setVisible(true);
        }
        if(checkWin(PLAYER_DOT)){
            jFrame.setTitle("X - Player wins !!");
            jFrame.setVisible(true);
        }
        if(checkWin(AI_DOT)){
            jFrame.setTitle("O - Player wins !!");
            jFrame.setVisible(true);
        }
    }
    private void setSym(int y, int x, char sym) {
        map[y][x] = sym;
    }

    // проверка валидности ячейки
    private boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > xField - 1 || y > yField - 1) {
            return false;
        }
        return map[y][x] == 0;
    }

    // ход человека

    // ход ПК
    private void aiStep() {
        int x;
        int y;
        if (step < 3) {
            do {
                x = random.nextInt(xField);
                y = random.nextInt(yField);
            } while (!isCellValid(y, x));
            updateLines(x,y,AI_DOT);
            setSym(y, x, AI_DOT);
            step++;
        }else {
            for (int i = 0; i < aiLines.length; i++){
                int value = 0;
                if (aiLines[i] != null){
                    for (int j = 0; j < win; j++){
                        if (aiLines[i][j] < 0){
                            value++;
                        }
                    }

                }
                if (value == win-1){
                    for (int j = 0; j < win; j++){
                        if (aiLines[i][j] >=0){
                            x = aiLines[i][j] % xField;
                            y = (int) aiLines[i][j] / yField;
                            updateLines(x, y, AI_DOT);
                            setSym(y, x, AI_DOT);
                            step++;
                            return;
                        }
                    }
                }
            }
            int[][] array = maxPlayerLine();
            for (int i = 0; i < array.length; i++){
                for (int j = 0; j < win;j++){
                    if (array[i][j] == -1){
                        continue;
                    }else{
                        int temp = array[i][j];

                        for (int k = 0; k < aiLines.length; k ++){
                            if (aiLines[k] != null){
                                for (int m = 0; m < win; m++){
                                    if (aiLines[k][m] == temp){
                                        x = temp % xField;
                                        y = (int) temp / yField;
                                        updateLines(x, y, AI_DOT);
                                        setSym(y, x, AI_DOT);
                                        step++;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            do {
                x = random.nextInt(xField);
                y = random.nextInt(yField);
            } while (!isCellValid(y, x));
            updateLines(x, y, AI_DOT);
            setSym(y, x, AI_DOT);
            step++;
        }
    }

    /*
     * если не встретили пустую ячейку
     * это значит что все поле заполнено
     * */
    private boolean isDraw() {
        for (int i = 0; i < yField; i++) {
            for (int j = 0; j < xField; j++) {
                if(map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    // проверяем по всему полю field есть ли заполненые линии
    private boolean checkWin(char sym) {
        for (int i = 0; i < map.length;i++){
            for (int j = 0; j < map[0].length; j++){
                if (checkWinSmart(j,i,sym)){
                    return true;
                }
            }
        }
        return false;
    }
    // проверяем в текущей позиции есть ли заполненая линия
    private boolean checkWinSmart(int x,int y,char c){
        return  checkDirection(x,y,c,0,1) + checkDirection(x,y,c,0,-1) > win || // Vertical
                checkDirection(x,y,c,1,0) + checkDirection(x,y,c,-1,0) > win || // Horizontal
                checkDirection(x,y,c,-1,-1) + checkDirection(x,y,c,1,1) > win || // CW
                checkDirection(x,y,c,1,-1) + checkDirection(x,y,c,-1,1) > win // CCW
                ;
    }

    //    проверяем в заданом направлении
    private int checkDirection(int x, int y, char c, int dX, int dY){
        int result = 0;
        try{
            if (map[y][x] == c){
                result++;
            }
        }catch (Exception e){

        }
        return result == 0 ? result : result + checkDirection( x + dX, y + dY,c, dX, dY);
    }
    //    заполняем массив возможных линий.
    private void initLines(){
        int line = 0;
        int[][] temp = new int[xField * yField * win][win];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (j + win <= map[0].length){
                    for (int k = 0; k < win; k++){
                        temp[line][k] = i * map.length + j + k;
                    }
                    line++;
                }
            }
        }
        for (int i = 0; i < map[0].length; i++){
            for (int j = 0; j < map.length; j++){
                if (j + win <= map.length){
                    for (int k = 0; k < win; k++){
                        temp[line][k] = (j + k) * map.length + i;
                    }
                    line++;
                }
            }
        }
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (j + win <= map[0].length && i + win <= map.length){
                    for (int k = 0; k < win; k++){
                        temp[line][k] = (i + k) * map.length + j + k;
                    }
                    line++;
                }
            }
        }
        for (int i = 0; i < map.length; i++){
            for (int j = map[0].length - 1; j >= 0; j--){
                if (j - win + 1 >= 0 && i + win <= map.length){
                    for (int k = 0; k < win; k++){
                        temp[line][k] = (i + k) * map.length + j - k;
                    }
                    line++;
                }
            }
        }

        playerLines = new int[line][win];
        aiLines = new int[line][win];
        for (int i = 0 ; i < line; i++){
            for (int j = 0; j < win; j++) {

                playerLines[i][j] = temp[i][j];
                aiLines[i][j] = temp[i][j];
            }
        }
    }
    //    получить линии с самым близким концом
    private int[][] maxPlayerLine(){
        int max = 0;
        int count = 0;

        for (int i = 0; i < playerLines.length; i++){
            int temp = 0;
            if (playerLines[i] != null){
                for (int j = 0; j < win; j++) {
                    if (playerLines[i][j] < 0){
                        temp ++;
                    }
                }
            }
            if (max < temp){
                count = 1;
                max = temp;
            }else if (max == temp){
                count++;
            }
        }
        int[][] array = new int[count][win];
        int index = 0;
        for (int i = 0; i < playerLines.length; i++){
            int temp = 0;
            if (playerLines[i] != null) {
                for (int j = 0; j < win; j++) {
                    if (playerLines[i][j] < 0){
                        temp++;
                    }
                }
            }
            if (temp == max){
                for (int j = 0; j < win; j++){
                    array[index][j] = playerLines[i][j];
                }
                index++;
            }
        }
        return array;
    }

    private void updateLines(int x, int y, char sym){
        int check = y * map.length + x;
        for (int i = 0; i < playerLines.length; i++){
            if (sym == PLAYER_DOT){
                if (playerLines[i] != null){
                    for (int j = 0; j < win; j++){
                        if (playerLines[i][j] == check){
                            playerLines[i][j] = -1;
                        }
                    }
                }
                if (aiLines[i] != null){
                    for (int j = 0 ; j < win; j++){
                        if (aiLines[i][j] == check){
                            aiLines[i] = null;
                            j = win;
                        }
                    }
                }
            }else{
                if (aiLines[i] != null) {
                    for (int j = 0; j < win; j++) {
                        if (aiLines[i][j] == check) {
                            aiLines[i][j] = -1;
                        }
                    }
                    for (int j = 0; j < win; j++) {
                        if (playerLines[i] != null) {
                            if (playerLines[i][j] == check) {
                                playerLines[i] = null;
                            }
                        }
                    }
                }
            }
        }
    }
}
