package ru.geekbrains.java3.lesson1hw;

import ru.geekbrains.java3.lesson1hw.fruits.Apple;
import ru.geekbrains.java3.lesson1hw.fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1. Написать метод, который меняет два элемента массива местами.
(массив может быть любого ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты,
коробки условно сортируются по типу фрукта,
поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки,
зная количество фруктов и вес одного фрукта
(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare,
который позволяет сравнить текущую коробку с той,
которую подадут в compare в качестве параметра,
true - если их веса равны, false в противном случае
(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в
другую коробку(помним про сортировку фруктов,
нельзя яблоки высыпать в коробку с апельсинами),
соответственно в текущей коробке фруктов не остается,
а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
 */
public class Main {
    public static final String WEIGHT_INFO = "Вес коробки %s составляет %.2f%n";
    public static void main(String[] args) {
        // 1. - Task 1 test
        String[] aString = {"First","Second","Third"};
        System.out.println(Arrays.toString(aString));
        swap(0,1,aString);
        System.out.println(Arrays.toString(aString));
        // 2. - Task 2 test
        List<String> arrayList = arrayToList("Fisrt","Second","Third");
        List<Integer> list = arrayToList(1,2,4,Integer.parseInt("1"),7);
        // 3. - Task 3 test
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> appleBox1 = new Box<>();

        appleBox.add(new Apple(1),new Apple(1.2f),new Apple(1.3f));
        appleBox1.add(new Apple(1),new Apple(1.2f),new Apple(1.3f));

        orangeBox.add(new Orange(1f),new Orange(1.2f),new Orange(1.3f));
        // d. - getWeight
        System.out.printf(WEIGHT_INFO,"orangeBox",orangeBox.getWeight());
        orangeBox.add(new Orange(1.2f));
        System.out.printf(WEIGHT_INFO,"orangeBox",orangeBox.getWeight());
        // e. - compare test
        System.out.println(orangeBox.compare(appleBox));
        System.out.println(appleBox.compare(appleBox1));
        // f. - move test
        System.out.printf(WEIGHT_INFO,"appleBox",appleBox.getWeight());
        System.out.printf(WEIGHT_INFO,"appleBox1",appleBox1.getWeight());
        System.out.println("Moving appleBox1 to appleBox");
        appleBox1.move(appleBox);
//        orangeBox.move(appleBox); - can not cast
        System.out.printf(WEIGHT_INFO,"appleBox",appleBox.getWeight());
        System.out.printf(WEIGHT_INFO,"appleBox1",appleBox1.getWeight());
    }

    public static <T> List<T> arrayToList(T...array){
        return new ArrayList<T>(Arrays.asList(array));
    }

    public static <T> void swap(int first, int second, T...array){
        T t = array[first];
        array[first] = array[second];
        array[second] = t;
    }
}
