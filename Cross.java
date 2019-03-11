package Lesson_4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cross {

    static int SIZE_X = 4;
    static int SIZE_Y = 4;
    // переменная условия победы
    static int WIN_COND = 3;
    static int step = 0;
    static int[][] playerLines;
    static int[][] aiLines;

    // поле состояния игры.
    static char[][] field = new char[SIZE_Y][SIZE_X];
    // поле для печати на экран. Ограничение в 9 клеток !!
    static char[][] printField = new char[SIZE_Y * 2 + 2][SIZE_X * 2 + 2];

    static char PLAYER_DOT = 'X';
    static char AI_DOT = 'O';
    static char EMPTY_DOT = ' ';

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // заполнить поле
    private static void initField() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }
    // инициализируем поле для печати. Рисуем табличку и номера ячеек.
    private static void initPrintField(){
        for (int i = 0; i < printField[0].length; i++){
            if (i > 1) {
                printField[0][i] = i % 2 == 0 ? (char) ('0' + (i)/2) : ' ';
            }else{
                printField[0][i] = ' ';
            }
        }
        for (int i = 0; i < printField.length; i++){
            if (i > 1) {
                printField[i][0] = i % 2 == 0 ? (char) ('0' + (i)/2) : ' ';
            }else{
                printField[i][0] = ' ';
            }
        }

        for (int i = 1; i < printField.length; i++){
            for (int j = 1; j < printField[0].length; j++){
                if ( i == 1 ){
                    printField[i][j] = j % 2 != 0 ? '╤' : '═';
                }else if (i == printField.length - 1){
                    printField[i][j] = j % 2 != 0 ? '╧' : '═';
                }else if(i % 2 != 0) {
                    printField[i][j] = j % 2 != 0 ? '┼' : '─';
                }else{
                    printField[i][j] = j % 2 != 0 ? '│' : ' ';
                }
                if (j == 1){
                    printField[i][j] = i % 2 != 0 ? '╟' : '║';
                }else if (j == printField[0].length - 1){
                    printField[i][j] = i % 2 != 0 ? '╢' : '║';
                }
            }
        }
        printField[1][1] = '╔';
        printField[printField.length-1][1] = '╚';
        printField[printField.length-1][printField[0].length - 1] = '╝';
        printField[1][printField[0].length - 1] = '╗';
    }
    // обновляем информацию о состоянии поля для игры в поле для печати.
    private static void updatePrintField(){
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[0].length; j++){
                printField[i*2 + 2][j*2+2] = field[i][j];
            }
        }
    }

    // печатаем вместо поля для игры поле для печати.
    private static void printField() {
        updatePrintField();
        for (int i = 0; i < printField.length; i++){
            for (int j = 0; j < printField[0].length; j++){
                System.out.print(printField[i][j]);
            }
            System.out.println();
        }
    }

    // метод для установки символа
    private static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    // проверка валидности ячейки
    private static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
            return false;
        }
        return field[y][x] == EMPTY_DOT;
    }

    // ход человека
    private static void playerStep() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y (1 - 3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y,x));
        updateLines(x,y,PLAYER_DOT);
        setSym(y, x, PLAYER_DOT);
        step++;
    }

    // ход ПК
    private static void aiStep() {
        int x;
        int y;
        if (step < 3) {
            do {
                x = random.nextInt(SIZE_X);
                y = random.nextInt(SIZE_Y);
            } while (!isCellValid(y, x));
            updateLines(x,y,AI_DOT);
            setSym(y, x, AI_DOT);
            step++;
        }else {
            for (int i = 0; i < aiLines.length; i++){
                int value = 0;
                if (aiLines[i] != null){
                    for (int j = 0; j < WIN_COND; j++){
                        if (aiLines[i][j] < 0){
                            value++;
                        }
                    }

                }
                if (value == WIN_COND-1){
                    for (int j = 0; j < WIN_COND; j++){
                        if (aiLines[i][j] >=0){
                            x = aiLines[i][j] % SIZE_Y;
                            y = (int) aiLines[i][j] / SIZE_Y;
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
                    for (int j = 0; j < WIN_COND;j++){
                        if (array[i][j] == -1){
                            continue;
                        }else{
                            int temp = array[i][j];

                            for (int k = 0; k < aiLines.length; k ++){
                                if (aiLines[k] != null){
                                    for (int m = 0; m < WIN_COND; m++){
                                        if (aiLines[k][m] == temp){
                                            x = temp % SIZE_X;
                                            y = (int) temp / SIZE_X;
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
                x = random.nextInt(SIZE_X);
                y = random.nextInt(SIZE_Y);
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
    private static boolean isDraw() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if(field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }
    // проверяем по всему полю field есть ли заполненые линии
    private static boolean checkWin(char sym) {
        for (int i = 0; i < field.length;i++){
            for (int j = 0; j < field[0].length; j++){
                if (checkWinSmart(j,i,sym)){
                    return true;
                }
            }
        }
        return false;
    }
    // проверяем в текущей позиции есть ли заполненая линия
    private static boolean checkWinSmart(int x,int y,char c){
        return  checkDirection(x,y,c,0,1) + checkDirection(x,y,c,0,-1) > WIN_COND || // Vertical
                checkDirection(x,y,c,1,0) + checkDirection(x,y,c,-1,0) > WIN_COND || // Horizontal
                checkDirection(x,y,c,-1,-1) + checkDirection(x,y,c,1,1) > WIN_COND || // CW
                checkDirection(x,y,c,1,-1) + checkDirection(x,y,c,-1,1) > WIN_COND // CCW
                ;
    }

//    проверяем в заданом направлении
    private static int checkDirection(int x, int y, char c, int dX, int dY){
        int result = 0;
        try{
            if (field[y][x] == c){
                result++;
            }
        }catch (Exception e){

        }
        return result == 0 ? result : result + checkDirection( x + dX, y + dY,c, dX, dY);
    }
//    заполняем массив возможных линий.
    private static void initLines(){
        int line = 0;
        int[][] temp = new int[SIZE_X * SIZE_Y * WIN_COND][WIN_COND];
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[0].length; j++){
                if (j + WIN_COND <= field[0].length){
                    for (int k = 0; k < WIN_COND; k++){
                       temp[line][k] = i * field.length + j + k;
                    }
                    line++;
                }
            }
        }
        for (int i = 0; i < field[0].length; i++){
            for (int j = 0; j < field.length; j++){
                if (j + WIN_COND <= field.length){
                    for (int k = 0; k < WIN_COND; k++){
                        temp[line][k] = (j + k) * field.length + i;
                    }
                    line++;
                }
            }
        }
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[0].length; j++){
                if (j + WIN_COND <= field[0].length && i + WIN_COND <= field.length){
                    for (int k = 0; k < WIN_COND; k++){
                        temp[line][k] = (i + k) * field.length + j + k;
                    }
                    line++;
                }
            }
        }
        for (int i = 0; i < field.length; i++){
            for (int j = field[0].length - 1; j >= 0; j--){
                if (j - WIN_COND + 1 >= 0 && i + WIN_COND <= field.length){
                    for (int k = 0; k < WIN_COND; k++){
                        temp[line][k] = (i + k) * field.length + j - k;
                    }
                    line++;
                }
            }
        }

        playerLines = new int[line][WIN_COND];
        aiLines = new int[line][WIN_COND];
        for (int i = 0 ; i < line; i++){
            for (int j = 0; j < WIN_COND; j++) {

                playerLines[i][j] = temp[i][j];
                aiLines[i][j] = temp[i][j];
            }
        }
    }
//    получить линии с самым близким концом
    private static int[][] maxPlayerLine(){
        int max = 0;
        int count = 0;

        for (int i = 0; i < playerLines.length; i++){
            int temp = 0;
            if (playerLines[i] != null){
                for (int j = 0; j < WIN_COND; j++) {
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
        int[][] array = new int[count][WIN_COND];
        int index = 0;
        for (int i = 0; i < playerLines.length; i++){
            int temp = 0;
            if (playerLines[i] != null) {
                for (int j = 0; j < WIN_COND; j++) {
                    if (playerLines[i][j] < 0){
                        temp++;
                    }
                }
            }
            if (temp == max){
                for (int j = 0; j < WIN_COND; j++){
                    array[index][j] = playerLines[i][j];
                }
                index++;
            }
        }
        return array;
    }

    private static void updateLines(int x, int y, char sym){
        int check = y * field.length + x;
        for (int i = 0; i < playerLines.length; i++){
            if (sym == PLAYER_DOT){
                if (playerLines[i] != null){
                   for (int j = 0; j < WIN_COND; j++){
                       if (playerLines[i][j] == check){
                           playerLines[i][j] = -1;
                       }
                   }
                }
                if (aiLines[i] != null){
                    for (int j = 0 ; j < WIN_COND; j++){
                        if (aiLines[i][j] == check){
                            aiLines[i] = null;
                            j = WIN_COND;
                        }
                    }
                }
            }else{
                if (aiLines[i] != null) {
                    for (int j = 0; j < WIN_COND; j++) {
                        if (aiLines[i][j] == check) {
                            aiLines[i][j] = -1;
                        }
                    }
                    for (int j = 0; j < WIN_COND; j++) {
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

    public static void main(String[] args) {
        initField();
        initPrintField();

        printField();
        initLines();
        while (true) {
            playerStep();
            printField();

            if(checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if(isDraw()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if(checkWin(AI_DOT)) {
                System.out.println("SKYNET WIN!");
                break;
            }
            if(isDraw()) {
                System.out.println("DRAW");
                break;
            }

        }
    }


}
