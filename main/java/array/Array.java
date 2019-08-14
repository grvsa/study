package array;

import java.util.Arrays;

public class Array<T> implements IArray<T> {
    protected static final String INDEX_ERROR = "Index out of bounds. Array size: %s. Requested index: %s";
    protected static final String OBJECT_NOT_FOUND = "Requested object missing: %s";
    protected int size;
    protected T[] array;

    public Array(int size) {
        array = (T[]) new Object[size];
        this.size = 0;
    }

    public Array() {
        this(DEFAULT_SIZE);
    }

    private void shift(int index , boolean left){
        if (left){
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[--size] = null;
        }else{
            for (int i = size; i > index ; i--) {
                array[i] = array[i - 1];
            }
        }
    }

    private int checkSize(){
        int result = -1;
        if (size / 1.0 / array.length > 0.75){
            result = array.length * 4 / 3;
        }
        return result;
    }

    private void increaseSize(int newSize){
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array,0,newArray,0,array.length);
        array = newArray;

    }

    public boolean add(T item) {
        int available = checkSize();
        if (available != -1){
            increaseSize(available);
        }
        array[size++] = item;
        return true;
    }

    public boolean add(int index, T item) {
        if (index < 0 || index >= size){
            throw new RuntimeException(String.format(INDEX_ERROR,size,index));
        }

        int available = checkSize();
        if (available != -1){
            increaseSize(available);
        }
        shift(index,false);
        array[index] = item;
        size++;
        return true;
    }

    public boolean addAll(T...items) {
        boolean result = true;
        for (T item :
                items) {
            if (!add(item)) result = false;
        }
        return result;
    }

    public int size() {
        return size;
    }

    public T remove(T item) {
        int result = indexOf(item);
        if (result != -1) {
            return remove(result);
        }else{
            throw new RuntimeException(String.format(OBJECT_NOT_FOUND,item == null ? "Null" : item.toString()));
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= size){
            throw new RuntimeException(String.format(INDEX_ERROR,size,index));
        }
        T object = array[index];
        shift(index,true);
        return object;
    }

    public boolean contains(T item) {
        boolean result = false;
        if (item == null){
            for (int i = 0; i < size; i++) {
                if (array[i] == null){
                    i = size;
                    result = true;
                }
            }
        }else{
            for (int i = 0; i < size; i++) {
                if (array[i].equals(item)){
                    i = size;
                    result = true;
                }
            }
        }
        return result;
    }

    public T get(int index) {
        if (index < 0 || index >= size){
            throw new RuntimeException(String.format(INDEX_ERROR,size,index));
        }
        return array[index];
    }

    public int indexOf(T item) {
        int result = -1;
        if (item == null){
            for (int i = 0; i < size; i++) {
                if (array[i] == null){
                    result = i;
                    i = size;
                }
            }
        }else{
            for (int i = 0; i < size; i++) {
                if (array[i].equals(item)){
                    result = i;
                    i = size;
                }
            }
        }
        return result;
    }

    public boolean set(int index, T item) {
        if (index < 0 || index >= size){
            throw new RuntimeException(String.format(INDEX_ERROR,size,index));
        }
        array[index] = item;
        return true;
    }

    public IArray subArray(int start, int end) {
        if (start < 0 || start >= size){
            throw new RuntimeException(String.format(INDEX_ERROR,size,start));
        }else if (end < 0 || end > size){
            throw new RuntimeException(String.format(INDEX_ERROR,size,end));
        }
        IArray<T> subArray = null;
        try {
            subArray = this.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (int i = start; i < end; i++) {
            subArray.add(array[i]);
        }
        return subArray;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array,0,size));
    }


}
