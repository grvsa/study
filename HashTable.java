public interface HashTable<K, V> {

    int size();

    boolean isEmpty();

    boolean isFull();

    boolean put(K key, V value);

    V get(K key);

    boolean contains(K key);

    V remove(K key);

    void display();

    interface Entry<K, V>{

        K getKey();

        V getValue();

        void setKey(K key);

        void setValue(V value);

    }
}
