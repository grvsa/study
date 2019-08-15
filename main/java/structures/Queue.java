package structures;

public interface Queue<T> {
    static final int DEFAULT_SIZE = 10;

    boolean isEmpty();

    boolean isFull();

    boolean insert(T item);

    T remove();

    T peekFront();

    void clear();
}
