public interface ICollection<T> {
    boolean isFull();
    boolean isEmpty();
    boolean contains(T item);
    void clear();
    int size();
}
