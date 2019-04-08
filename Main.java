package ru.geekbrains.algjava.lesson3;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

//        Stack test

        StackHM stack = new StackHM(100);
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        for (int i = 0; i < 15; i++) {
            System.out.println(stack.peek());
        }

        for (int i = 0; i < 101; i++) {
            System.out.println(stack.pop());
        }

//        Quene test

        QueneHM quene = new QueneHM(10);

        for (int i = 0; i < 10; i++) {
            quene.insert(i);
        }
        System.out.println(quene.remove());
        quene.print();
        quene.insert(29);
        quene.print();
        System.out.println(quene.remove());
        quene.print();
        quene.insert(30);
        quene.print();
        for (int i = 0; i < 10; i++) {
            System.out.println(quene.remove());
        }
        quene.print();

//        Deque test

        DequeHM deque = new DequeHM(10);
        deque.print();
        deque.insertLeft(1);
        deque.insertRight(2);
        deque.print();
        deque.insertLeft(3);
        deque.insertRight(4);
        deque.print();
        deque.insertLeft(5);
        deque.insertRight(6);
        deque.print();
        deque.insertLeft(7);
        deque.insertRight(8);
        deque.print();
        deque.insertLeft(9);
        deque.insertRight(10);
        deque.print();
        System.out.println(deque.removeRight());
        deque.print();
        deque.insertLeft(11);
        deque.print();
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        deque.print();
        System.out.println(deque.removeLeft());
        deque.insertRight(75);
        deque.insertRight(76);
        deque.insertRight(77);
        deque.print();

        deque.insertLeft(35);
        deque.insertLeft(36);
        deque.insertLeft(37);
        deque.insertLeft(38);
        deque.print();


//        PriorityQuene Test
        PriorityQueneHM pQuene = new PriorityQueneHM(100);
        Random r = new Random();
        for (int i = 0; i < 89; i++) {
            pQuene.insert(i,r.nextInt(100));
        }
        pQuene.print();
        System.out.println(pQuene.remove());
        System.out.println(pQuene.remove());
        System.out.println(pQuene.remove());
        System.out.println(pQuene.remove());
        pQuene.print();

//        String rotate test

        reverse();
    }

    public static void reverse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input text: ");
        char[] cArray = scanner.nextLine().toCharArray();
        StackHM stack = new StackHM(cArray.length);
        for (char c: cArray
             ) {
            stack.push(c);
        }
        for (int i = 0; i < cArray.length; i++){
            System.out.print(stack.pop());
        }
    }
}
