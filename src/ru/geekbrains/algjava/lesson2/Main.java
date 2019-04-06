package ru.geekbrains.algjava.lesson2;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Arr aArray = new Arr();

//        aArray.test();
//        System.out.println(aArray.toString());
//        for (int i = 0; i < 9; i++) {
//            aArray.add(i);
//        }
//        System.out.println(aArray.toString());
//        aArray.add(1);
//        aArray.add(2);
//        aArray.add(3);
//        aArray.test();
//        System.out.println(aArray.toString());
//        aArray.set(1,299);
//        System.out.println(aArray.toString());
//        aArray.remove();
//        System.out.println(aArray.toString());
//        aArray.remove(5);
//        System.out.println(aArray.toString());
//        aArray.insert(3,10);
//        System.out.println(aArray.toString());
//        System.out.println(aArray.find(15));
//        aArray.set(7,4);
//        System.out.println(aArray.findNext());
//        aArray.remove();
//        System.out.println(aArray.toString());
//        aArray.test();
        Random r = new Random(1000000);
//        for (int i = 0; i < 1000000; i++) {
//            aArray.add(r.nextInt() + 1);
//        }
//        aArray.test();
//
//        System.out.println(System.nanoTime());
//        System.out.println(System.nanoTime());
        Arr bArray = new Arr();
        Arr cArray = new Arr();

        for (int i = 0; i < 1000000; i++) {
            int temp = r.nextInt() + 1;
            aArray.add(temp);
            bArray.add(temp);
            cArray.add(temp);
        }
        long boubleTime = System.nanoTime();
        aArray.sortBouble();
        boubleTime = System.nanoTime() - boubleTime;
        boubleTime = boubleTime / 1000 / 1000;
        System.out.println(boubleTime);
        long selectionTime = System.nanoTime();
        bArray.sortSelection();
        selectionTime = System.nanoTime() - selectionTime;


        long insertionTime = System.nanoTime();
        cArray.sortInsertion();
        insertionTime = System.nanoTime() - insertionTime;

        System.out.printf("Bouble sort: %,30d%nSelection sort: %,30d%nInsertion sort: %,30d%n",boubleTime,selectionTime,insertionTime);
    }
}
