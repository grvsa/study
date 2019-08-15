package structures;

public class DequeImpl<T> extends QueueImpl<T> implements Deque<T> {

    public DequeImpl(int size) {
        super(size);
    }

    public DequeImpl() {
    }

    public boolean insertFirst(T item) {
        if (isEmpty()){
            return insert(item);
        }
        if (!isFull()){
            array[tail] = item;
            if (tail == 0){
                tail = array.length - 1;
            }else {
                tail--;
            }
            size++;
            return true;
        }
        return false;
    }

    public T peekFirst() {
        return peekFront();
    }

    public T removeFirst() {
        return remove();
    }

    public boolean insertLast(T item) {
        return insert(item);
    }

    public T peekLast() {
        return isEmpty() ? null : array[head];
    }

    public T removeLast() {
        if (!isEmpty()){
            T temp = array[head];
            if (head == 0){
                head = array.length - 1;
            }else{
                head--;
            }
            if (--size == 0){
                clear();
            }
            return temp;
        }
        return null;
    }
}
