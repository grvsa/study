package structures;

import java.util.Arrays;

public class StackImpl<T> implements Stack<T> {
    private int size;
    private T[] array;

    public StackImpl(int size) {
        this.size = 0;
        array = (T[]) new Object[size];
    }

    public StackImpl() {
        this(DEFAULT_SIZE);
    }

    public boolean push(T item) {
        if (isFull()){
            return false;
        }
        array[size++] = item;
        return true;
    }

    public T pop() {
        return isEmpty() ? null : array[--size];
    }

    public T peek() {
        return isEmpty() ? null : array[size - 1];
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(array,0,size)));
    }

    public void clear() {
        size = 0;
    }
}
