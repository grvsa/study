import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedListImpl<Integer> linkedList = new LinkedListImpl<Integer>();
        System.out.println(linkedList);
        linkedList.insertLast(1);
        linkedList.insertFirst(2);
        linkedList.insertFirst(3);
        System.out.println(linkedList);
        linkedList.insertLast(2);
        linkedList.insertLast(3);
        linkedList.insertLast(4);
        System.out.println(linkedList + " " + linkedList.size());
        System.out.println(Arrays.toString(linkedList.toArray()));
        System.out.println("Contains 1: " + linkedList.contains(1));
        System.out.println("Contains 3: " + linkedList.contains(3));
        System.out.println("Contains 9: " + linkedList.contains(9));

        System.out.println("----------------------------------------------");
        System.out.println(linkedList.peekFirst());
        System.out.println(linkedList.peekLast());
        System.out.println(linkedList.removeLast());
        linkedList.insertLast(4);
        System.out.println(linkedList + " " + linkedList.size());

        System.out.println("Remove 3: " + linkedList.remove(3));
        System.out.println(linkedList + " " + linkedList.size());

        System.out.println(linkedList.insertAfter(1,9) + " " + linkedList + " " + linkedList.size());
        System.out.println(linkedList.insertBefore(1,7) + " " + linkedList + " " + linkedList.size());
        System.out.println(linkedList.insertBefore(2,6) + " " + linkedList + " " + linkedList.size());
        System.out.println(linkedList.insertAfter(4,8) + " " + linkedList + " " + linkedList.size());
        System.out.println(linkedList.insertAfter(0,8) + " " + linkedList + " " + linkedList.size());

//        System.out.println("Remove 2: " + linkedList.remove(2));
//        System.out.println(linkedList + " " + linkedList.size());
//
//        System.out.println("Remove 5: " + linkedList.remove(5));
//        System.out.println(linkedList + " " + linkedList.size());
//
//        System.out.println("Remove 2: " + linkedList.remove(2));
//        System.out.println(linkedList + " " + linkedList.size());
//
//        System.out.println("Remove 3: " + linkedList.remove(3));
//        System.out.println(linkedList + " " + linkedList.size());
//
//        System.out.println("Remove 3: " + linkedList.remove(3));
//        System.out.println(linkedList + " " + linkedList.size());



//        System.out.println(linkedList.removeFirst());
//        System.out.println(linkedList + " " + linkedList.size());
//        while (!linkedList.isEmpty()){
//            System.out.println(linkedList.removeLast() + " " + linkedList + " " + linkedList.size());
//        }

        System.out.println("--------------------------------------------------------------------");
        LinkedListImpl<Integer> newList = new LinkedListImpl<Integer>(1,2,3,4,5,6);
        System.out.println(newList + " " + newList.size());

        ListIterator<Integer> iterator = (ListIterator<Integer>) newList.iterator();
        System.out.println(iterator.nextIndex());
        System.out.println(iterator.previousIndex());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasPrevious());

        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasPrevious());
        System.out.println(iterator.previous());

        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasPrevious());
        while (iterator.hasNext()){
            System.out.println("Next: " + iterator.next() + " Next index: " + iterator.nextIndex());
        }
        while (iterator.hasPrevious()){
            System.out.println("Previous: " + iterator.previous() + " Previous index: " + iterator.previousIndex());
        }

        System.out.println(newList);
        while (iterator.hasNext()){
            iterator.next();
        }
        System.out.println("---------------------------------------------------------");
        System.out.println(newList);
        iterator.add(7);
        System.out.println(newList);

        while (iterator.hasPrevious()){
            System.out.println("Previous : " + iterator.previousIndex() + " " + iterator.previous());
        }
        iterator.add(0);
        System.out.println(newList);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        iterator.add(9);
        System.out.println(newList);
        System.out.println(iterator.previous());
        System.out.println(iterator.previous());
        iterator.add(8);
        System.out.println(newList + " " + newList.size());
        while (iterator.hasPrevious()){
            System.out.println("Previous: " + iterator.previousIndex() + " Value: " + iterator.previous());
        }

        while (iterator.hasNext()){
            System.out.println("Next: " + iterator.nextIndex() + " Value: " + iterator.next());
        }

        for (Integer i :
                newList) {
            System.out.println("ForEach: " + i);
        }
    }
}
