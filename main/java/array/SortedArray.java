package array;

public class SortedArray<T extends Object & Comparable> extends Array<T> {
    private boolean isSorted = false;

    @Override
    public boolean add(T item) {
        isSorted = false;
        return super.add(item);
    }

    @Override
    public boolean add(int index, T item) {
        isSorted = false;
        return super.add(index, item);
    }

    @Override
    public boolean addAll(T... items) {
        isSorted = false;
        return super.addAll(items);
    }

    @Override
    public T remove(T item) {
        if (!isSorted) {
            return super.remove(item);
        }else{
            int result = binarySearch(0,size,item);
            if (result != -1) {
                return remove(result);
            }else{
                throw new RuntimeException(String.format(OBJECT_NOT_FOUND,item == null ? "Null" : item.toString()));
            }
        }
    }

    @Override
    public boolean contains(T item) {
        if (!isSorted) {
            return super.contains(item);
        }else{
            int result = binarySearch(0,size,item);
            return result != -1;
        }
    }

    @Override
    public int indexOf(T item) {
        if (!isSorted) {
            return super.indexOf(item);
        }else{
            return binarySearch(0,size,item);
        }
    }


    public void bubbleSort(){
        int a = size - 1;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < a; j++) {
                if (array[j].compareTo(array[j + 1]) > 0){
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            a--;
        }
        isSorted = true;
    }

    public void insertionSort(){
        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if (array[j].compareTo(array[j + 1]) > 0){
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        isSorted = true;
    }

    public void selectionSort(){
        for (int i = 0; i < size - 1; i++) {
            T minObject = array[i];
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (minObject.compareTo(array[j]) > 0){
                    minObject = array[j];
                    index = j;
                }
            }
            if (index != i){
                array[index] = array[i];
                array[i] = minObject;
            }
        }
        isSorted = true;
    }

    public int binarySearch(int start, int end, T item){
        if (isSorted){
            int result = -1;
            while (true) {
                int middle = (end + start) / 2;
                if (array[middle].compareTo(item) > 0){
                    if (end == middle){
                        break;
                    }else {
                        end = middle;
                    }
                }else if (array[middle].compareTo(item) < 0){
                    if (start == middle){
                        break;
                    }else {
                        start = middle;
                    }
                }else{
                    result = middle;
                    break;
                }
            }

            return result;
        }else{
            throw new RuntimeException("Not sorted array");
        }
    }

    public boolean checkSort(){
        for (int i = 0; i < size - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) return false;
        }
        return true;
    }
}
