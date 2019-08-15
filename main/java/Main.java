import structures.*;

import java.util.Random;


public class Main {
    public static void main(String[] args) {
//        Stack
        Stack<Integer> stack = new StackImpl<Integer>();
        for (int i = 0; i < 11; i++) {
            System.out.printf("Add element: %s Result: %s%n",i,stack.push(i));
        }
        System.out.println("Stack is full: " + stack.isFull());
        System.out.println("Last element: " + stack.peek());
        stack.display();
        while (!stack.isEmpty()){
            System.out.println("Pop from stack: " + stack.pop());
        }
        stack.display();

        System.out.println(reverse("reverse string"));

//        Queue
        QueueImpl<Integer> queue = new QueueImpl<Integer>();

        for (int i = 0; i < 11; i++) {
            queue.insert(i);
        }

        System.out.println(queue);

        System.out.println("Remove: " + queue.remove());
        System.out.println("Remove: " + queue.remove());
        System.out.println("Remove: " + queue.remove());
        System.out.println("Remove: " + queue.remove());
        System.out.println("Remove: " + queue.remove());

        for (int i = 0; i < 11; i++) {
            queue.insert(i);
        }

        System.out.println(queue);

        while (!queue.isEmpty()){
            queue.remove();
        }

        for (int i = 0; i < 5; i++) {
            queue.insert(i);
        }

        System.out.println(queue);

        System.out.println("----------------------------------");
        PriorityQueueImpl<Integer> priorityQueue = new PriorityQueueImpl<Integer>();

        Random random = new Random();
        for (int i = 10; i > 0 ; i--) {
            priorityQueue.insert(random.nextInt(100));
        }


        System.out.println(priorityQueue);

        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.remove());
        }

        System.out.println("------------------------------------");

        Deque<Integer> deque = new DequeImpl<Integer>();

        deque.insertLast(1);
        deque.insertLast(2);
        deque.insertLast(3);

        System.out.println(deque);
        deque.insertFirst(0);
        deque.insertLast(-1);

        System.out.println(deque);
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());

        System.out.println(deque.removeFirst());
        System.out.println(deque);
        System.out.println(deque.removeLast());
        System.out.println(deque);

        while (!deque.isFull()){
            System.out.println(deque.insertFirst(random.nextInt(100)));
        }

        System.out.println(deque);
    }

    public static String reverse(String str){
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new StackImpl<Character>(str.length());
        for (Character c :
                str.toCharArray()) {
            stack.push(c);
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
