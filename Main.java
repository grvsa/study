package ru.geekbrains.java3.lesson1;

import ru.geekbrains.java3.lesson1.fruits.Apple;
import ru.geekbrains.java3.lesson1.fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    /*
        1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
        2. Написать метод, который преобразует массив в ArrayList;
        3. Большая задача:
        Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
        Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
        поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
        Для хранения фруктов внутри коробки можно использовать ArrayList;
        Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
        (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
        Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
        которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае
        (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
        Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
        (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
        Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты,
        которые были в этой коробке;
        Не забываем про метод добавления фрукта в коробку.

    */
    public static void main(String[] args) {
        // write your code here
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        appleBox1.add(new Apple(), new Apple(), new Apple());
        orangeBox1.add(new Orange(), new Orange());

        System.out.println("Apple box 1: " + appleBox1.getWeight());
        System.out.println("Orange box 1: " + orangeBox1.getWeight());
        System.out.println("Apple box 2: " + appleBox2.getWeight());
        System.out.println("Orange box 2: " + orangeBox2.getWeight());
        System.out.println(appleBox1.compare(orangeBox1));

        appleBox1.moveTo(appleBox2);
//        orangeBox1.moveTo(appleBox2); - не разрешает

        System.out.println("Apple box 1: " + appleBox1.getWeight());
        System.out.println("Orange box 1: " + orangeBox1.getWeight());
        System.out.println("Apple box 2: " + appleBox2.getWeight());
        System.out.println("Orange box 2: " + orangeBox2.getWeight());

//        appleBox1.add(new Orange()); - не разрешает

        orangeBox2.add(new Orange(1.2f), new Orange());

        System.out.println("Apple box 1: " + appleBox1.getWeight());
        System.out.println("Orange box 1: " + orangeBox1.getWeight());
        System.out.println("Apple box 2: " + appleBox2.getWeight());
        System.out.println("Orange box 2: " + orangeBox2.getWeight());

        System.out.println(orangeBox1.compare(orangeBox2));

    }
    
    // Task 1
    public static <T> void exchange(int first, int second, T[] array) {
        if (array == null || first < 0 || second >= array.length) throw new RuntimeException("Arguments exception");
        T tempElement = array[first];
        array[second] = array[first];
        array[first] = tempElement;
    }

    // Task 2
    public static <T> List<T> arrayToList(T[] array) {
        if (array == null) throw new RuntimeException("Arguments exception");
        List<T> result = new ArrayList<>();
        for (T t : array) {
            result.add(t);
        }
        return result;
    }
}
