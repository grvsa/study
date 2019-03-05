package ru.geekbrains.java1.lesson4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson3 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
	// write your code here
        task1();
        task2();
    }

    public static void task1(){
//        1. Написать программу, которая загадывает случайное число от 0 до 9,
//        и пользователю дается 3 попытки угадать это число.
//        При каждой попытке компьютер должен сообщить больше ли указанное пользователем
//        число чем загаданное, или меньше. После победы или проигрыша выводится запрос –
//        «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
        while (true) {
            int number = (int) (Math.random() * 10);
            for (int i = 0; i < 3; i++) {
                System.out.println("Введите число: ");
                int guessNumber = scanner.nextInt();
                if (guessNumber > number) {
                    System.out.println("Загаданое число меньше. Попробуйте еще раз.");
                } else if (guessNumber < number) {
                    System.out.println("Загаданое число больше. Попробуйте еще раз.");
                } else {
                    System.out.println("Вы угадали. Загаданое число " + number);
                    break;
                }
            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            int newGame = scanner.nextInt();
            if (newGame != 1) break;
        }
    }

    public static void task2(){
//        2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
//        При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
//        сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
//        Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
//          apple – загаданное
//          apricot - ответ игрока
//        ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
//        Для сравнения двух слов посимвольно, можно пользоваться:
//        String str = "apple";
//        str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
//        Играем до тех пор, пока игрок не отгадает слово
//        Используем только маленькие буквы

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
                          "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
                          "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                          "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        int word = (int) (Math.random() * words.length);
        while (true){
            System.out.println("Введите слово: ");
            String wordGuess = scanner.nextLine();
            char[] helpArray = {'#', '#','#','#','#','#','#','#','#','#','#','#','#','#','#'};
            boolean result = words[word].length() == wordGuess.length() ? true : false;
            for(int i = 0; i < (words[word].length() < wordGuess.length() ? words[word].length() : wordGuess.length()); i++){
                if(words[word].charAt(i) == wordGuess.charAt(i)){
                    helpArray[i] = wordGuess.charAt(i);
                }else{
                    result = false;
                }
            }
            System.out.println((result ? "Вы угадали ! Слово " + wordGuess : "Вы не угадали. Подсказка " + Arrays.toString(helpArray)));
            if (result) break;
        }
    }
}
