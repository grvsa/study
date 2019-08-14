package array;

public interface IArray<T> {
    public static final int DEFAULT_SIZE = 10;

    boolean add(T item);
    boolean add(int index, T item);
    boolean addAll(T...items);
    int size();
    T remove(T item);
    T remove(int index);
    boolean contains(T item);
    T get(int index);
    int indexOf(T item);
    boolean set(int index, T item);
    IArray<T> subArray(int start, int end);
    void clear();
}
