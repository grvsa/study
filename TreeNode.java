package ru.geekbrains.algjava.lesson6;


public class TreeNode {
    private Integer i;
    private int level;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(){

    }
    public TreeNode(Integer i) {
        this.i = i;
        level = 0;
    }
    private TreeNode(Integer i, int level){
        this.i = i;
        this.level = level;
    }
    public void add(Integer i){
        if (this.i == null){
            this.i = i;
            this.level = 0;
        }else {
            if (i < this.i) {
                if (left == null) {
                    left = new TreeNode(i, calculateLevel());
                } else {
                    left.add(i);
                }
            } else if (i > this.i){
                if (right == null) {
                    right = new TreeNode(i, calculateLevel());
                } else {
                    right.add(i);
                }
            }
        }
    }
    public int depth(){
        if (left == null && right == null){
            return 1;
        }
        int l = 0;
        int r = 0;
        if (left != null){
            l = left.depth();
        }
        if (right != null) {
            r = right.depth();
        }
        return 1 + (l > r ? l : r);
    }
    public void print(){
        int depth = depth();
        String[] sArray = new String[depth];
        for (int j = depth - 1; j >=0 ; j--) {
            sArray[j] = printNode(j);
        }
        for (String s :
                sArray) {
            System.out.println(s);
        }
    }
    private String printNode(int level){

        return "" +
                (left != null ? left.printNode(level) : "\t") +
                (this.level == level ? i + "\t": "\t") +
                (right != null ? right.printNode(level) : "\t");
    }
    public Integer min(){
        if (left == null){
            return i;
        }
        return left.min();
    }
    public Integer max(){
        if (right == null){
            return i;
        }
        return right.max();
    }
    private int calculateLevel(){
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        int result = 0;
        for (StackTraceElement e :
                stack) {
            if (e.getMethodName().equals("add") && e.getClassName().equals("ru.geekbrains.algjava.lesson6.TreeNode")){
                result++;
            }
        }
        return result;
    }
    public boolean find(Integer i){
        if (i != null){
            return this.i == i ||
                    (left != null ? left.find(i): false) ||
                    (right != null ? right.find(i): false);
        }
        return false;
    }
    public int size(){
        if (i == null){
            return 0;
        }
        return 1 +
                (left != null ? left.size(): 0) +
                (right != null ? right.size(): 0);
    }
    public boolean delete(Integer i){
        boolean result = false;
        if (find(i)){
            TreeNode current = this;
            TreeNode parent = this;
            while (current.i != i){
                parent = current;
                if (current.i > i){
                    current = current.left;
                }else{
                    current = current.right;
                }
            }
            if (current.left == null && current.right == null){
                if (current != parent){
                    if (parent.left == current){
                        parent.left = null;
                    }else{
                        parent.right = null;
                    }
                }else{
                    this.i = null;
                }
                result = true;
            }else if (current.left != null && current.right != null){
                Integer temp = current.left.max();
                current.delete(temp);
                current.i = temp;
                result = true;
            }else{
                if (current != parent){
                    current.levelDecrease();
                    if (parent.left == current){
                        parent.left = current.left == null ? current.right : current.left;
                    }else{
                        parent.right = current.left == null ? current.right : current.left;
                    }
                }else{
                    if (current.left != null){
                        current.left.levelDecrease();
                        current.i = current.left.i;
                        current.right = current.left.right;
                        current.left = current.left.left;
                    }else{
                        current.right.levelDecrease();
                        current.i = current.right.i;
                        current.left = current.right.left;
                        current.right = current.right.right;
                    }
                }
                result = true;
            }
        }
        return result;
    }
    private void levelDecrease(){
        level--;
        if (left != null){
            left.levelDecrease();
        }
        if (right != null){
            right.levelDecrease();
        }
    }
    public boolean balance(){
        boolean result = Math.abs(
                (left != null ? left.depth() : 0) -
                        (right != null ? right.depth(): 0)
        ) <= 1;
        return result
                && (left != null ? left.balance() : true)
                && (right != null ? right.balance(): true);
    }
}
