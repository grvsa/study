import array.SortedArray;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        SortedArray<Integer> array1 = new SortedArray<Integer>();
        SortedArray<Integer> array2 = new SortedArray<Integer>();
        SortedArray<Integer> array3 = new SortedArray<Integer>();

        Random random = new Random();

        for (int i = 1000000; i > 0 ; i--) {
            int number = random.nextInt(2000000);
            array1.add(number);
            array2.add(number);
            array3.add(number);
        }

        long start = System.currentTimeMillis();
        array1.bubbleSort();
        System.out.println("Bubble sort time: " + (System.currentTimeMillis() - start) + " Check sort condition: " + array1.checkSort());

        start = System.currentTimeMillis();
        array2.insertionSort();
        System.out.println("Insertion sort time: " + (System.currentTimeMillis() - start) + " Check sort condition: " + array2.checkSort());

        start = System.currentTimeMillis();
        array3.selectionSort();
        System.out.println("Selection sort time: " + (System.currentTimeMillis() - start) + " Check sort condition: " + array3.checkSort());

    }
}
