package structures;

public interface Stack<T> {
    static final int DEFAULT_SIZE = 10;
    boolean push(T item);
    T pop();
    T peek();
    boolean isFull();
    boolean isEmpty();
    void display();
    void clear();
}
