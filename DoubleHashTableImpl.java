public class DoubleHashTableImpl<K, V> extends HashTableImpl<K, V> {
    private static final int DOUBLE_HASH_CONST = 5;

    public DoubleHashTableImpl(int maxSize) {
        super(maxSize);
    }

    private int hashDoubleFunc(K key){
        return DOUBLE_HASH_CONST - (key.hashCode() % DOUBLE_HASH_CONST);
    }

    @Override
    protected int getStep(K key) {
        return hashDoubleFunc(key);
    }
}
