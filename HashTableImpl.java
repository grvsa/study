public class HashTableImpl<K, V> implements HashTable<K, V> {

    private static class Node<K, V> implements Entry<K, V>{
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private Node<K, V>[] data;
    private int size;
    private int maxSize;

    public HashTableImpl(int maxSize) {
        this.maxSize = maxSize;
        data = new Node[maxSize * 2];
        size = 0;
    }

    private int hashFunc(K key){
        return key.hashCode() % data.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public boolean put(K key, V value) {
        if (isFull()){
            return false;
        }
        int index = hashFunc(key);
        int count = 0;
        while (data[index] != null){
            if (count == data.length){
                index++;
                count = 0;
            }
            if (data[index].getKey().equals(key)){
                data[index].setValue(value);
                return true;
            }
            index += getStep(key);
            index %= data.length;
            count++;
        }

        data[index] = new Node<K, V>(key,value);
        size++;
        return true;
    }

    public V get(K key) {
        int index = indexOf(key);
        return index == -1 ? null : data[index].getValue();
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    private int indexOf(K key){
        int index = hashFunc(key);
        int count = 0;
        while (data[index] != null){
            if (count == data.length){
                index++;
                count = 0;
            }
            if (data[index].getKey().equals(key)){
                return index;
            }
            index += getStep(key);
            index %= data.length;
            count++;
        }
        return -1;
    }

    public V remove(K key) {
        int index = indexOf(key);
        if (index != -1){
            V result = data[index].getValue();
            data[index] = null;
            size--;
            return result;
        }
        return null;
    }

    public void display() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(i + ":\t" + (data[i] == null ? "NULL" : data[i]));;
        }
    }

    protected int getStep(K key){
        return 1;
    }
}
