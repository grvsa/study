import items.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Возведение в степень: " + pow(5,4));
        System.out.println("-----------------------------------------------");
        Item[] array = {new Food(), new Book(), new Cloth()};

        anagrams(array,0, array.length - 1, 11);

        System.out.println("-----------------------------------------------");
        List<ArrayList<Item>> result = new ArrayList<ArrayList<Item>>();
        bagpack(new ArrayList<Item>(Arrays.asList(array)),11,result);
        Collections.sort(result, new Comparator<ArrayList<Item>>() {
            public int compare(ArrayList<Item> o1, ArrayList<Item> o2) {
                int priceO1 = 0;
                for (int i = 0; i < o1.size(); i++) {
                    priceO1 += o1.get(i).getValue();
                }
                int priceO2 = 0;
                for (int i = 0; i < o2.size(); i++) {
                    priceO2 += o2.get(i).getValue();
                }
                return priceO2 - priceO1;
            }
        });

        for (ArrayList<Item> list :
                result) {
            int weight = 0;
            int price = 0;
            for (int i = 0; i < list.size(); i++) {
                weight += list.get(i).getWeight();
                price += list.get(i).getValue();
            }
            System.out.println(list + " Total weight: " + weight + " Total price: " + price);
        }
    }

    public static int pow(int number, int pow){
        if (pow < 0) {
            throw new IllegalArgumentException("Степень меньще 0: " + pow);
        }
       return pow == 0 ? 1 : number * pow(number,--pow);
    }

    public static void shift(Item[] array, int start, int end){
        Item temp = array[start];
        for (int i = start; i < end; i++) {
            array[i] = array[i + 1];
        }
        array[end] = temp;
    }

    public static void anagrams(Item[] array, int start, int end, int maxWeight){
        if (start == end){
            int weight = 0;
            int price = 0;
            for (int i = 0; i < array.length; i++) {
                weight += array[i].getWeight();
                if (weight <= maxWeight){
                    price += array[i].getValue();
                    System.out.print(array[i] + " ");
                }else{
                    break;
                }
            }
            System.out.println(" Total price: " + price);
        }
        for (int i = start; i <= end; i++) {
            anagrams(array, start + 1, end, maxWeight);
            shift(array,start,end);
        }
    }

    public static void bagpack(ArrayList<Item> source, int weight, List<ArrayList<Item>> result){
        int currentWeight = 0;
        for (int i = 0; i < source.size(); i++) {
            currentWeight += source.get(i).getWeight();
        }
        if (currentWeight <= weight){
            result.add((ArrayList<Item>) source.clone());
        }else {
            for (int i = 0; i < source.size(); i++) {
                Item temp = source.remove(i);
                bagpack(source, weight, result);
                source.add(i,temp);
            }
        }
    }
}
