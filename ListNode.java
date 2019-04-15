package ru.geekbrains.algjava.lesson4;

public class ListNode {
    private Object o;
    private ListNode next;
    // Constructor
    public ListNode(Object o) {
        this.o = o;
    }
    //Getters / Setters
    public Object getO() {
        return o;
    }
    public void setO(Object o) {
        this.o = o;
    }
    public ListNode getNext() {
        return next;
    }
    public void setNext(ListNode next) {
        this.next = next;
    }
    //toString realization
    @Override
    public String toString() {
        return o.toString();
    }
    // contains realization
    public boolean contains(Object o){
        return this.o.equals(o) || (next != null ? next.contains(o) : false);
    }
}
