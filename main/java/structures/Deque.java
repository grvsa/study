package structures;

public interface Deque<T> {
    static final int DEFAULT_SIZE = 10;

    boolean insertFirst(T item);

    T peekFirst();

    T removeFirst();

    boolean insertLast(T item);

    T peekLast();

    T removeLast();

    boolean isEmpty();

    boolean isFull();
}
