package structures;

public class QueueImpl<T> implements Queue<T> {
    protected int size;
    protected T[] array;

    protected int head;
    protected int tail;

    public QueueImpl(int size) {
        this.size = 0;
        array = (T[]) new Object[size];
        head = 0;
        tail = 0;
    }

    public QueueImpl() {
        this(DEFAULT_SIZE);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean insert(T item) {
        if (!isFull()){
            size++;
            array[++head == array.length ? head = 0: head] = item;
        }
        return false;
    }

    public T remove() {
        if (!isEmpty()){
            size--;
            return array[++tail == array.length ? tail = 0 : tail];
        }
        return null;
    }

    public T peekFront() {
        if (!isEmpty()){
            return array[(tail + 1) % array.length];
        }
        return null;
    }

    public void clear() {
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int count = tail;
        do{
            count++;
            sb.append(", " + array[count % array.length]);
        }while ((count % array.length) != head % array.length);

        return "[" + sb.substring(2) + "]";
    }
}
