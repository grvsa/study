import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
//        HashTableImpl<Integer,String> hashTable = new HashTableImpl<Integer, String>(10);
//        HashTableImpl<Integer,String> hashTable = new DoubleHashTableImpl<Integer, String>(10);
//        hashTable.display();

//        hashTable.put(10,"A10");
//        hashTable.put(53,"A330");
//        hashTable.put(23,"A23");
//        hashTable.put(21,"A21");
//        hashTable.put(41,"A41");
//        hashTable.put(42,"A42");
//
//        System.out.println(hashTable.size());
//        hashTable.display();
//
//        System.out.println(hashTable.contains(10));
//        System.out.println(hashTable.contains(53));
//        System.out.println(hashTable.contains(77));
//
//        System.out.println(hashTable.get(77));
//        System.out.println(hashTable.get(75));
//        System.out.println(hashTable.get(53));
//
//        System.out.println(hashTable.remove(10));
//        System.out.println(hashTable.remove(53));
//        System.out.println(hashTable.size());
//
//        hashTable.display();
//
//        System.out.println(hashTable.remove(42));
//
//        hashTable.display();
//        for (int i = 0; i < 50; i++) {
//            Integer value = i;
//            System.out.println(i + ":" + value.hashCode() + ": " + (value.hashCode() % 20));
//        }


        LinkedHashTableImpl<Integer, String> linkedHashTable = new LinkedHashTableImpl<Integer, String>(10);
        linkedHashTable.display();
        System.out.println("--------------------------------------------------------");
        linkedHashTable.put(1,"A1");
        linkedHashTable.put(7,"A7");
        linkedHashTable.put(12,"A12");
        linkedHashTable.put(17,"A72");
        linkedHashTable.put(27,"A727");
        linkedHashTable.put(35,"A35");
        linkedHashTable.display();

        System.out.println(linkedHashTable.size());
        System.out.println("--------------------------------------------------------");
        System.out.println(linkedHashTable.get(17));
        System.out.println(linkedHashTable.get(35));
        System.out.println(linkedHashTable.get(40));

        System.out.println(linkedHashTable.remove(1));
        System.out.println(linkedHashTable.remove(17));
        System.out.println(linkedHashTable.remove(0));

        linkedHashTable.display();
        System.out.println(linkedHashTable.size());

        System.out.println(linkedHashTable.contains(12));
        System.out.println(linkedHashTable.contains(27));

        for (Node<Integer, String> integerStringNode : linkedHashTable) {
            System.out.println(integerStringNode.getKey() + " " + integerStringNode.getValue());
        }

        Iterator iterator = linkedHashTable.iterator();
        System.out.println(iterator.hasNext());
//        iterator.remove(); - exception
        Node next = (Node) iterator.next();
        System.out.println(next.getKey() + " " + next.getValue());
        linkedHashTable.display();
        System.out.println("--------------------------------------------------------");
        iterator.remove();
        linkedHashTable.display();
//        iterator.remove(); - exception
        System.out.println("--------------------------------------------------------");
        System.out.println(iterator.hasNext());
        next = (Node) iterator.next();
        System.out.println(next.getKey() + " " + next.getValue());
    }
}
