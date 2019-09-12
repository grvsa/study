import java.util.Iterator;

public class LinkedHashTableImpl<K, V> implements HashTable<K, V>, Iterable<Node<K, V>> {
    private int size;
    private Node<K, V>[] array;
    private int maxSize;

    public LinkedHashTableImpl() {
        this(100);
    }

    public LinkedHashTableImpl(int maxSize) {
        this.maxSize = maxSize;
        array = new Node[maxSize];
    }

    public int hashFunc(K key){
        return key.hashCode() % array.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return false;
    }

    public boolean put(K key, V value) {
        int index = hashFunc(key);
        if (array[index] == null){
            array[index] = new Node<K, V>(key,value);
            size++;
        }else{
            Node<K, V> firstNode = array[index];
            while (firstNode.getNext() != null && !firstNode.getKey().equals(key)){
                firstNode = firstNode.getNext();
            }
            if (firstNode.getKey().equals(key)){
                firstNode.setValue(value);
            }else {
                firstNode.setNext(new Node<K, V>(key, value));
                size++;
            }
        }
        return true;
    }

    public V get(K key) {
        int index = hashFunc(key);
        if (array[index] != null){
            Node<K, V> firstNode = array[index];
            while (firstNode != null){
                if (firstNode.getKey().equals(key)){
                    return firstNode.getValue();
                }
                firstNode = firstNode.getNext();
            }
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V remove(K key) {
        int index = hashFunc(key);
        if (array[index] != null){
            if (array[index].getKey().equals(key)){
                V value = array[index].getValue();
                array[index] = array[index].getNext();
                size--;
                return value;
            }else{
                Node<K, V> firstNode = array[index];
                while (firstNode.getNext() != null && !firstNode.getNext().getKey().equals(key)){
                    firstNode = firstNode.getNext();
                }
                if (firstNode != null){
                    V value = firstNode.getNext().getValue();
                    firstNode.setNext(firstNode.getNext().getNext());
                    size--;
                    return value;
                }
            }
        }
        return null;
    }

    public void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(i + "\t = \t" + (array[i] == null ? "Node{\"NULL\"}" : array[i]));
        }
    }

    public Iterator<Node<K, V>> iterator() {
        return new IteratorHash();
    }

    class IteratorHash implements Iterator<Node<K,V>>{
        Node<K, V> current;
        Node<K, V> previous;

        int index;

        public IteratorHash() {
            if (size == 0){
                current = null;
            }else{
                index = 0;
                while (array[index] == null){
                    index++;
                }
                current = array[index];
            }
            previous = null;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Node<K, V> next() {
            if (current != null) {
                Node<K, V> result = current;
                previous = current;
                if (current.getNext() != null) {
                    current = current.getNext();
                } else if (++index == array.length) {
                    current = null;
                } else {
                    while (index < array.length && array[index] == null) {
                        index++;
                    }
                    if (index < array.length) {
                        current = array[index];
                    } else {
                        current = null;
                    }
                }
                return result;
            }
            previous = null;
            return null;
        }

        public void remove() {
            if (previous != null){
                LinkedHashTableImpl.this.remove(previous.getKey());
                previous = null;
            }else{
                throw new IllegalStateException("No iterated items");
            }
        }
    }


}
