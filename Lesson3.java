package ru.geekbrains.java1.lesson3;
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

        String test = " -5 + ((1 + 8) -( -31*-18)*(217 + 1))";
        System.out.println(test);
        System.out.println(calculator(test));

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

    public static int calculation(String s){
//      Вычисление простой формулы (без скобок)
        String temp = s.replaceAll(" ","");
//      В этом блоке строка разбирается на 2 массива - числа valueArray и
//      действия indexArray (содержит индексы операций в формуле
        char[] tempArray = temp.toCharArray();
        int count = 0;
        int[] indexArray = new int[tempArray.length];
        for (int i = 0; i < tempArray.length; i++){
            if (tempArray[i] == '-' && i == 0){
                continue;
            }else if (tempArray[i] == '-' && (tempArray[i-1] == '-' || tempArray[i-1] == '+' || tempArray[i-1] == '*' || tempArray[i-1] == '/')){
                continue;
            }else if (tempArray[i] == '-' || tempArray[i] == '+' || tempArray[i] == '*' || tempArray[i] == '/'){
                count++;
                indexArray[count] = i;
            }
        }
        int[] valueArray = new int[count+1];
        for (int i = 0; i < count; i++){
            if (i == 0) {
                valueArray[i] = Integer.parseInt(temp.substring(indexArray[i], indexArray[i + 1]));
            }else{
                valueArray[i] = Integer.parseInt(temp.substring(indexArray[i]+1, indexArray[i + 1]));
            }
        }

        valueArray[count] = Integer.parseInt(temp.substring(indexArray[count]+1,temp.length()));
//      Этот блок производит вычисление результата.
//      В зависимости от приоритета операции ищем её в indexArray и на основании этого
//      выбираем 2 операнда из valueArray. После этого массивы уменьшаются на одну операцию.
//      Когда в массиве valueArray остается один элемент - это и есть решение.

        String op = "*/-+";
        for (int i = 0; i < op.length(); i++) {
            for (int j = 1; j < valueArray.length; j++) {
                if (temp.charAt(indexArray[j]) == op.charAt(i)) {
                    switch(op.charAt(i)) {
                        case '*':
                            valueArray[j - 1] = valueArray[j - 1] * valueArray[j];
                            break;
                        case '/':
                            valueArray[j - 1] = (int) (valueArray[j - 1] / valueArray[j]);
                            break;
                        case '-':
                            valueArray[j - 1] = valueArray[j - 1] - valueArray[j];
                            break;
                        case '+':
                            valueArray[j - 1] = valueArray[j - 1] + valueArray[j];
                            break;
                    }
                    int[] copyValueArray = new int[valueArray.length - 1];
                    int[] copyIndexArray = new int[indexArray.length];
                    for (int y = 0; y < copyValueArray.length; y++) {
                        if (y >= j) {
                            copyValueArray[y] = valueArray[y + 1];
                            copyIndexArray[y] = indexArray[y + 1];
                        } else {
                            copyValueArray[y] = valueArray[y];
                            copyIndexArray[y] = indexArray[y];
                        }
                    }
                    valueArray = copyValueArray;
                    indexArray = copyIndexArray;
                    j = 1;
                }
            }
         }
        return valueArray[0];
    }

    public static boolean validation(String s){
//        Проверка строки на ошибки
        boolean result = true;
        s = s.replaceAll(" ","");
        int countBr = 0;
        for (int i = 0; i < s.length(); i++){
            if (!((s.charAt(i) >= '0'
                    && s.charAt(i) <= '9')
                    ||s.charAt(i) == '*'
                    || s.charAt(i) == '/'
                    || s.charAt(i) == '+'
                    || s.charAt(i) == '-'
                    || s.charAt(i) == '('
                    || s.charAt(i) == ')'
            )){
                result = false;
            }else if(s.contains("---")
                        || s.contains("++")
                        || s.contains("//")
                        || s.contains("**")
                        || s.contains("+*")
                        || s.contains("+/")
                        || s.contains("-*")
                        || s.contains("-/")
                        || s.contains("-+")
                        || s.contains("*+")
                        || s.contains("*/")
                        || s.contains("/*")
                        || s.contains("/+")
                        || s.contains("(+")
                        || s.contains("(/")
                        || s.contains("(*")
                        || s.contains("()")
                        || s.contains(")(")
                        || s.contains("0(")
                        || s.contains("1(")
                        || s.contains("2(")
                        || s.contains("3(")
                        || s.contains("4(")
                        || s.contains("5(")
                        || s.contains("6(")
                        || s.contains("7(")
                        || s.contains("8(")
                        || s.contains("9(")
                        || s.contains("0(")
                        || s.contains(")1")
                        || s.contains(")2")
                        || s.contains(")3")
                        || s.contains(")4")
                        || s.contains(")5")
                        || s.contains(")6")
                        || s.contains(")7")
                        || s.contains(")8")
                        || s.contains(")9")
                        || s.contains("---")){
                result = false;
            }else if (countBr < 0){
                result = false;
            }
            if (s.charAt(i) == '(')
                countBr++;
            if (s.charAt(i) == ')')
                countBr--;

        }
        if (countBr != 0)
            result = false;
        return result;
    }

    public static String calculator(String s){
        String result =  "Invalid String !";
        if (validation(s)){
            while (s.contains(")")){
                int start = s.indexOf(")");
                int end = s.indexOf(")");
                while (s.charAt(start) != '(')
                {
                    start--;
                }
                int value = calculation(s.substring(start + 1,end));
                s = s.replace(s.substring(start,end + 1), "" + value);
                System.out.println(s);
            }
            result = "Ответ:" + calculation(s);
        }
        return result;
    }
}