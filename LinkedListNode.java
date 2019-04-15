package ru.geekbrains.algjava.lesson4;

public class LinkedListNode {
    private Object o;
    private LinkedListNode previous;
    private LinkedListNode next;
    // Constructor
    public LinkedListNode(Object o) {
        this.o = o;
        previous = null;
        next = null;
    }
    // Getters/Setters
    public Object getO() {
        return o;
    }
    public void setO(Object o) {
        this.o = o;
    }
    public LinkedListNode getPrevious() {
        return previous;
    }
    public void setPrevious(LinkedListNode previous) {
        this.previous = previous;
    }
    public LinkedListNode getNext() {
        return next;
    }
    public void setNext(LinkedListNode next) {
        this.next = next;
    }
    // toString realization;
    @Override
    public String toString(){
        return o.toString();
    }
    // contains realization
    public boolean contains(Object o){
        return this.o.equals(o) || (next != null ? next.contains(o) : false);
    }

}
