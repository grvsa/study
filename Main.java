package ru.geekbrains.algjava.lesson8;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        // write your code here
        HashList hashList = new HashList(10);
        hashList.add("String 1");
        hashList.add("String 2");
        hashList.add("String 3");
        hashList.add("String 4");
        hashList.add("String 1");
        hashList.add("String 5");
        hashList.add("String 6");
        hashList.add("String 7");
        hashList.add("String 8");
        System.out.println(hashList);
        Iterator iterator = hashList.iterator();
        System.out.println(iterator.next());
        iterator.remove();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(hashList);
//        System.out.println(iterator.next());
//        iterator.remove();
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        iterator.remove();
//        System.out.println(iterator.next());
//        iterator.remove();
//        System.out.println(hashList);
//        System.out.println(iterator.next()); // 4 out
//        iterator.remove(); // 4 remove
//        System.out.println(hashList);
//        System.out.println(iterator.next()); // 5 out
//        iterator.remove(); // 5 remove
//        System.out.println(hashList);
//        System.out.println(iterator.next()); // 6 out
//        System.out.println(iterator.next()); // 7 out
//        iterator.remove(); // 7 remove
//        System.out.println(hashList);
//        System.out.println(iterator.next()); // 8 out
//        System.out.println(iterator.next());
//        iterator.remove();
//        System.out.println(hashList);
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
    }
}
