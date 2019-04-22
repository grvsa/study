package ru.geekbrains.algjava.lesson5;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        // write your code here

        // Степень через рекурсию
        System.out.println(pow(5,2));

        // Задача про рюкзак
        Item[] array = new Item[5];
        // Список уникальных вещей
        array[0] = new Item("Спички",3,2);
        array[1] = new Item("Еда",7,5);
        array[2] = new Item("Одежда",6,4);
        array[3] = new Item("Топор",6,4);
        array[4] = new Item("Вода",3,3);
        bagPackTask(array,12);
    }

    /*
    В общем виде задачу можно сформулировать так:
    из заданного множества предметов со свойствами «стоимость» и «вес»
    требуется отобрать подмножество с максимальной полной стоимостью,
    соблюдая при этом ограничение на суммарный вес.
     */
    public static void bagPackTask(Item[] array, int weightLimit){
        // Подготовка всеъ данных для перебора.
        // Считаем что каждой вещи может быть много.
        ArrayList<Item> source = new ArrayList<>();
        for (Item i:
             array) {
            for (int j = 0; j < weightLimit / i.weight; j++) {
                source.add(i);
            }
        }
        ArrayList<ArrayList<Item>> result = new ArrayList<>();

        // source - источник данных result - все комбинации удовлетворяющие условию веса

        bruteForce(source,source.size(),result,0,weightLimit);

        // вывод всех посчитаных вариантов

        for (ArrayList<Item> iList :
                result) {
            int resultWeight = 0;
            int resultValuse = 0;
            for (Item i:
                 iList) {
                resultWeight += i.weight;
                resultValuse += i.value;
                System.out.println(i);
            }
            System.out.printf("Вес: %d \t Стоимость: %d%n",resultWeight,resultValuse);
        }
        System.out.println("------------------------------------------------------------------------------");

        // подсчет и вывод вариантов с максимальной стоимостью при соблюденни условия веса.

        int maxValue = 0;
        for (ArrayList<Item> items:
        result){
            int tempValue = 0;
            for (Item i :
                    items) {
                tempValue += i.value;
            }
            if (maxValue < tempValue){
                maxValue = tempValue;
            }
        }
        for (int i = 0; i < result.size(); i++) {
            int tempValue = 0;
            for (Item j :
                    result.get(i)) {
                tempValue += j.value;
            }
            if (tempValue != maxValue){
                result.remove(i);
                i--;
            }
        }
        for (ArrayList<Item> iList :
                result) {
            int resultWeight = 0;
            int resultValuse = 0;
            for (Item i:
                    iList) {
                resultWeight += i.weight;
                resultValuse += i.value;
                System.out.println(i);
            }
            System.out.printf("Вес: %d \t Стоимость: %d%n",resultWeight,resultValuse);
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void bruteForce(ArrayList<Item> source, int size, ArrayList<ArrayList<Item>> result,int weight, int weightLimit){
        if (size == 1 || weight > weightLimit){
            ArrayList<Item> tempList = new ArrayList<Item>();
            for (int i = 0; i < source.size() - size - 1; i++) {
                tempList.add(source.get(i));
            }
            Collections.sort(tempList);
            if (!result.contains(tempList)){
                result.add(tempList);
            }
            return;
        }
        for (int i = 0; i < size; i++) {
            bruteForce(source,size - 1,result, weight + source.get(source.size() - size).weight, weightLimit);
            Item temp = source.remove(source.size() - size);
            source.add(temp);
        }
    }

    public static int pow(int number, int pow){
        return number * (pow > 1 ? pow(number, --pow) : 1);
    }

    public static void quickSort(int[] array,int first, int last){
        boolean direction = true;
        while(first != last){
            System.out.println(Arrays.toString(array));
            if (direction){
                if (array[first] > array[last]){
                    int temp = array[first];
                    array[first] = array[last];
                    array[last] = temp;
                    temp = first;
                    first = last;
                    last = temp;
                    last++;
                    direction = false;
                }else {
                    last--;
                }
            }else{
                if (array[first] <= array[last]){
                    int temp = array[first];
                    array[first] = array[last];
                    array[last] = temp;
                    direction = true;
                    temp = first;
                    first = last;
                    last = temp;
                    last--;
                }else {
                    last++;
                }
            }
        }
    }

    public static class Item implements Comparable {
        String name;
        int value;
        int weight;

        public Item(String name, int value, int weight) {
            this.name = name;
            this.value = value;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return value == item.value &&
                    weight == item.weight &&
                    Objects.equals(name, item.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, value, weight);
        }

        @Override
        public int compareTo(Object o) {
            int cName = name.compareTo(((Item) o).name);
            int cWeight = weight - ((Item) o).weight;
            int cValue = value - ((Item) o).value;
            if (cName != 0){
                return cName;
            }else if (cWeight != 0){
                return cWeight;
            }else{
                return cValue;
            }
        }
    }
}
