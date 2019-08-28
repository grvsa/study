import java.util.NoSuchElementException;
import java.util.Stack;

public class TreeSetImpl<T extends Comparable> implements ICollection<T> {
    private Node<T> root;
    private int size;

    public TreeSetImpl() {
        root = null;
        size = 0;
    }

    public boolean isBalance(){
        if (root != null){
            int maxDepth = root.maxDepth();
            return Math.abs(root.maxDepth() - root.balance()) <= 1;
        }
        return true;
    }
    public T remove(T item){
        Node<T> removed = find(item);
        if (removed != null){
            removed.remove(item);
        }
        return removed.value;
    }

    public void print(){
        if (root != null){
            int max = root.maxDepth();
            for (int i = 1; i <= max ; i++) {
                System.out.println(root.print(i));
            }
        }
    }

    public T min() {
        return root == null ? null : root.min().value;
    }

    public T max() {
         return root == null ? null : root.max().value;
    }

    public boolean add(T item) {
        if (root == null) {
            root = new Node<T>(item, null);
            size++;
            return true;
        } else {
            return root.add(item);
        }
    }

    public boolean addWithLevel(T item, int maxLevel){
        if (maxLevel >= 1) {
            if (root == null) {
                root = new Node<T>(item, null);
                return true;
            }
            return root.addWithLevel(item, maxLevel);
        }else{
            return false;
        }
    }
    public Node<T> find(T item) {
        return root == null ? null : root.find(item);
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T item) {
        return find(item) != null;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    class Node<E extends T> {
        private E value;
        private Node<E> leftNode;
        private Node<E> rightNode;
        private Node<E> parent;

        public Node(E value, Node<E> parent) {
            this.parent = parent;
            this.value = value;
            leftNode = null;
            rightNode = null;
        }

        public boolean addWithLevel(E item, int maxLevel){
            if (value.compareTo(item) == 0 || depth() >= maxLevel){
                return false;
            }else if(value.compareTo(item) > 0){
                if (leftNode == null) {
                    leftNode = new Node<E>(item,this);
                    return true;
                }else{
                    return leftNode.addWithLevel(item, maxLevel);
                }
            }else{
                if (rightNode == null){
                    rightNode = new Node<E>(item,this);
                    return true;
                }else{
                    return rightNode.addWithLevel(item, maxLevel);
                }
            }
        }

        public int balance(){
            if (leftNode == null || rightNode == null){
                return depth();
            }else{
                return Math.min(leftNode == null ? 0 : leftNode.balance(), rightNode == null ? 0 : rightNode.balance());
            }
        }

        public void remove(E item){
            if (size == 1){
                clear();
            }else if (leftNode == null && rightNode == null){
                if (parent.leftNode == this){
                    parent.leftNode = null;
                }else{
                    parent.rightNode = null;
                }
                size--;
            }else {
                Node<E> toReplace = null;
                if (leftNode != null){
                    toReplace = leftNode.max();
                }else{
                    toReplace = rightNode.min();
                }

                if (toReplace.leftNode == null && toReplace.rightNode == null){
                    value = toReplace.value;
                    if (toReplace.parent.leftNode == toReplace){
                        toReplace.parent.leftNode = null;
                    }else{
                        toReplace.parent.rightNode = null;
                    }
                    size--;
                }else{
                    value = toReplace.value;
                    toReplace.remove(toReplace.value);

                }

            }
        }

        public String print(int level){
            int currentLevel = depth();

            return (leftNode == null ? "\t" : leftNode.print(level) + "\t") +
                    (currentLevel == level ? this + "\t" : "\t") +
                    (rightNode == null ? "\t" : rightNode.print(level) + "\t");
        }

        public void fullInfo(){
            System.out.println("Value: " + this +
                    " Parent: " + (parent == null ? "Null" : parent) +
                    " Left: " + (leftNode == null ? "Null" : leftNode) +
                    " Right: " + (rightNode == null ? "Null" : rightNode));
        }

        public int depth(){
            return 1 + (parent == null ? 0 : parent.depth());
        }

        public int maxDepth(){
            return 1 + Math.max(leftNode == null ? 0 : leftNode.maxDepth(), rightNode == null ? 0 : rightNode.maxDepth());
        }

        public int minDepth(){
            return 1 + Math.min(leftNode == null ? 0 : leftNode.maxDepth(), rightNode == null ? 0 : rightNode.maxDepth());
        }

        public Node<E> min() {
            return leftNode == null ? this : leftNode.min();
        }

        public Node<E> max() {
            return rightNode == null ? this : rightNode.max();
        }

        public Node<E> find(E item) {
            if (value.compareTo(item) == 0) {
                return this;
            } else if (value.compareTo(item) > 0) {
                return leftNode == null ? null : leftNode.find(item);
            } else {
                return rightNode == null ? null : rightNode.find(item);
            }
        }

        public boolean add(E item) {
            if (value.compareTo(item) > 0) {
                if (leftNode == null) {
                    size++;
                    leftNode = new Node<E>(item, this);
                    return true;
                } else {
                    return leftNode.add(item);
                }
            } else if (value.compareTo(item) < 0) {
                if (rightNode == null) {
                    size++;
                    rightNode = new Node<E>(item, this);
                    return true;
                } else {
                    return rightNode.add(item);
                }
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return "[" + value + "]";
        }
    }
}
