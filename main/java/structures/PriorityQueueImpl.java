package structures;

public class PriorityQueueImpl<T extends Object&Comparable> extends QueueImpl<T>{

    public PriorityQueueImpl(int size) {
        super(size);
    }

    public PriorityQueueImpl() {
        super();
    }

    @Override
    public boolean insert(T item) {
        if (!isFull()){
            if (isEmpty() || array[head].compareTo(item) <= 0){
                super.insert(item);
            }else{
                int tempTail = tail;

                for (int i = 0; i < size; i++) {
                    if (array[++tempTail % array.length].compareTo(item) < 0){
                        array[(tempTail - 1) % array.length] = array[tempTail % array.length];
                    }else{
                        break;
                    }
                }
                array[(tempTail - 1) % array.length] = item;
                size++;
                if (tail == 0) {
                    tail = array.length - 1;
                }else {
                    tail--;
                }
            }
        }
        return false;
    }
}
