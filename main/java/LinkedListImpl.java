import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedListImpl<T> implements ICollection<T>, Iterable<T> {
    private int size;
    protected Node<T> first;
    protected Node<T> last;
    private int modifications;


    public LinkedListImpl() {
        size = 0;
        first = null;
        last = null;
        modifications = 0;
    }

    public LinkedListImpl(T... items) {
        this();
        for (T item :
                items) {
            insertLast(item);
        }
        ;
    }

    public void insertFirst(T item) {
        modifications++;
        if (size++ == 0) {
            first = new Node<T>(item);
            last = first;
        } else {
            Node<T> newFirst = new Node<T>(item);
            newFirst.setNext(first);
            first.setPrevious(newFirst);
            first = newFirst;
        }
    }

    public void insertLast(T item) {
        modifications++;

        if (size == 0) {
            insertFirst(item);
        } else {
            Node<T> newLast = new Node<T>(item);
            newLast.setPrevious(last);
            last.setNext(newLast);
            last = newLast;
            size++;
        }
    }

    public T peekFirst() {
        return isEmpty() ? null : first.getValue();
    }

    public T peekLast() {
        return isEmpty() ? null : last.getValue();
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        modifications++;

        T result = null;
        if (!isEmpty()) {
            result = first.getValue();
            if (--size == 0) {
                clear();
            } else {
                first = first.getNext();
                first.setPrevious(null);
            }
        }
        return result;
    }

    public T removeLast() {
        modifications++;

        T result = null;
        if (!isEmpty()) {
            result = last.getValue();
            if (--size == 0) {
                clear();
            } else {
                last = last.getPrevious();
                last.setNext(null);
            }
        }
        return result;
    }

    public T remove(T item) {
        modifications++;

        Node<T> nodeToRemove = find(item);
        T result = null;
        if (nodeToRemove != null) {
            result = nodeToRemove.getValue();
            if (size == 1) {
                clear();
            } else if (nodeToRemove.getPrevious() == null) {
                removeFirst();
            } else if (nodeToRemove.getNext() == null) {
                removeLast();
            } else {
                nodeToRemove.getNext().setPrevious(nodeToRemove.getPrevious());
                nodeToRemove.getPrevious().setNext(nodeToRemove.getNext());
                size--;
            }
        }
        return result;
    }

    public boolean insertBefore(T item, T value) {
        modifications++;

        Node<T> result = find(item);
        if (result != null) {
            if (result.getPrevious() == null) {
                insertFirst(value);
            } else {
                Node<T> newNode = new Node<T>(value);
                result.getPrevious().setNext(newNode);
                newNode.setPrevious(result.getPrevious());
                newNode.setNext(result);
                result.setPrevious(newNode);
                size++;
            }
            return true;
        }
        return false;
    }

    public boolean insertAfter(T item, T value) {
        modifications++;

        Node<T> result = find(item);
        if (result != null) {
            if (result.getNext() == null) {
                insertLast(value);
            } else {
                Node<T> newNode = new Node<T>(value);
                newNode.setNext(result.getNext());
                result.getNext().setPrevious(newNode);
                newNode.setPrevious(result);
                result.setNext(newNode);
                size++;
            }
            return true;
        }
        return false;
    }


    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        if (!isEmpty()) {
            Node<T> current = first;
            int count = 0;
            do {
                array[count++] = current.getValue();
                current = current.getNext();
            } while (current != null);
        }
        return array;
    }

    private Node<T> find(T item) {
        Node<T> result = null;
        if (!isEmpty()) {
            result = first;
            while (result != null && !result.getValue().equals(item)) {
                result = result.getNext();
            }
        }
        return result;
    }

    public boolean contains(T item) {
        return find(item) != null;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public String toString() {
        return "[" + (size != 0 ? first : "") + "]";
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator<T>();
    }

    class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> previous;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return value.toString() + (next != null ? ", " + next : "");
        }
    }

    class LinkedListIterator<U extends T> implements ListIterator<T> {
        private int nextIndex;
        private int previousIndex;

        private Node<T> nextNode;
        private Node<T> previousNode;
        private Node<T> currentNode;
        private final int mods;
        private Boolean operation;

        public LinkedListIterator() {
            previousIndex = -1;
            nextIndex = 0;
            previousNode = null;
            currentNode = null;
            nextNode = first;
            mods = modifications;
        }

        public boolean hasNext() {
            concurentModifications();
            return nextNode != null;
        }

        public T next() {
            concurentModifications();

            if (nextIndex == size) {
                throw new NoSuchElementException();
            }
            currentNode = nextNode;
            previousNode = nextNode;
            nextNode = nextNode.getNext();
            nextIndex++;
            previousIndex++;
            operation = Boolean.TRUE;
            return currentNode.getValue();
        }

        public boolean hasPrevious() {
            concurentModifications();

            return previousNode != null;
        }

        public T previous() {
            concurentModifications();

            if (previousIndex == -1) {
                throw new NoSuchElementException();
            }
            currentNode = previousNode;
            previousNode = previousNode.getPrevious();
            nextNode = currentNode;
            previousIndex--;
            nextIndex--;
            operation = Boolean.FALSE;
            return currentNode.getValue();
        }

        public int nextIndex() {
            concurentModifications();

            return nextIndex;
        }

        public int previousIndex() {
            concurentModifications();

            return previousIndex;
        }

        public void remove() {
            concurentModifications();

            if (currentNode == null || operation == null) {
                throw new IllegalStateException();
            }
            if (currentNode.getPrevious() == null) {
                first = first.getNext();
                if (first != null) {
                    first.setPrevious(null);
                    if (operation) {
                        nextIndex--;
                        previousIndex--;
                    }
                    nextNode = currentNode.getNext();
                    previousNode = null;
                } else {
                    clear();
                    previousIndex = -1;
                    nextIndex = 0;
                    nextNode = null;
                    previousNode = null;
                }
            } else if (currentNode.getNext() == null) {
                last = last.getPrevious();
                if (last != null) {
                    last.setNext(null);
                    if (operation) {
                        nextIndex--;
                        previousIndex--;
                    }
                    nextNode = null;
                    previousNode = currentNode.getPrevious();
                } else {
                    previousIndex = -1;
                    nextIndex = 0;
                    nextNode = null;
                    previousNode = null;
                }
            } else {
                currentNode.getPrevious().setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                if (operation) {
                    previousNode = currentNode.getPrevious();
                    nextIndex--;
                } else {
                    nextNode = currentNode.getNext();
                }
            }
            operation = null;
            size--;
            currentNode = null;
        }

        public void set(T t) {
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            currentNode.setValue(t);
        }

        public void add(T t) {
            concurentModifications();

            Node<T> newNode = new Node<T>(t);
            if (size == 0) {
                first = newNode;
                last = newNode;
                newNode = null;
                previousNode = newNode;
                nextIndex = 1;
                previousIndex = 0;
            }else if (currentNode == null){
                throw new IllegalStateException();
            }else{
                if (operation){
                    newNode.setNext(nextNode);
                    newNode.setPrevious(currentNode);
                    currentNode.setNext(newNode);
                    if (nextNode != null){
                        nextNode.setPrevious(newNode);
                    }else{
                        last = newNode;
                    }
                    nextIndex++;
                    previousIndex++;

                    previousNode = newNode;
                }else {
                    newNode.setNext(currentNode);
                    currentNode.setPrevious(newNode);
                    newNode.setPrevious(previousNode);
                    if (previousNode != null){
                        previousNode.setNext(newNode);
                    }else{
                        first = newNode;
                    }
                    nextNode = newNode;
                }
            }
            size++;

        }

        private void concurentModifications() throws ConcurrentModificationException {
            if (mods != modifications) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
