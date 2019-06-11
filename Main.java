package ru.geekbrains.java2.lesson3;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        /*
            1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
            Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
            Посчитать, сколько раз встречается каждое слово.
            2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
            В этот телефонный справочник с помощью метода add() можно добавлять записи.
            С помощью метода get() искать номер телефона по фамилии.
            Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
            тогда при запросе такой фамилии должны выводиться все телефоны.

            Желательно как можно меньше добавлять своего, чего нет в задании
            (т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес),
            делать взаимодействие с пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner),
            тестировать просто из метода main(), прописывая add() и get().

        */

        // Task1
        String words = "a a a b b c d e f f f f f f f k";
        task1(words.split(" "));

        // Task2
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("11-11-11","Alex");
        phoneBook.add("11-11-12","Alex");
        phoneBook.add("11-11-13","Alex");

        phoneBook.add("22-22-22","Bob");
        phoneBook.add("22-22-23","Bob");

        phoneBook.add("33-33-33","Petr");

        System.out.println(phoneBook.get("Alex"));
        System.out.println(phoneBook.get("Bob"));
        System.out.println(phoneBook.get("Petr"));

        System.out.println(phoneBook.get("Fedor"));
    }

    public static void task1(String...words){
        Map<String,Integer> wordsMap = new HashMap<>();
        for (String s :
                words) {
            wordsMap.put(s, wordsMap.getOrDefault(s, 0) + 1);
        }
        System.out.println(wordsMap.toString());
    }
}
