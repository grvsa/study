package ru.geekbrains.algjava.lesson4;

public class LinkedList {
    private LinkedListNode first;
    private LinkedListNode last;

    public LinkedListNode getFirst() {
        return first;
    }

    public LinkedListNode getLast() {
        return last;
    }

    public boolean isEmpty(){
        return first == null;
    }
    public Object getValue(){
        if (!isEmpty()){
            return first.getO();
        }
        throw new RuntimeException("Linked List is empty !");
    }
    public void setValue(Object o){
        if (!isEmpty()){
            first.setO(o);
        }else {
            throw new RuntimeException("Linked List is empty !");
        }
    }
    public void delete(){
        if (!isEmpty()){
            if (first.getNext() != null){
                first = first.getNext();
                first.setPrevious(null);
            }else{
                first = null;
                last = null;
            }
        }else {
            throw new RuntimeException("Linked List is empty !");
        }
    }
    public void insert(Object o){
        if (isEmpty()){
            first = new LinkedListNode(o);
            last = first;
        }else{
            LinkedListNode tmp = first;
            first = new LinkedListNode(o);
            first.setNext(tmp);
            tmp.setPrevious(first);
        }
    }
    public boolean contains(Object o){
        if (!isEmpty()){
            return first.contains(o);
        }
        return false;
    }
    @Override
    public String toString(){
        if (isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder(first.getO().toString());
        LinkedListNode tmp = first;
        while (tmp.getNext() != null){
            tmp = tmp.getNext();
            sb.append(", " + tmp.getO());
        }
        return sb.toString();
    }
    public void delete(Object o){
        if (isEmpty() || !contains(o)){
            throw new RuntimeException("Linked List is empty or have no record !");
        }
        LinkedListNode tmp = first;
        while (!tmp.getO().equals(o)){
            tmp = tmp.getNext();
        }
        tmp.getPrevious().setNext(tmp.getNext());
        tmp.getNext().setPrevious(tmp.getPrevious());
    }
    public void insertLast(Object o){
        LinkedListNode tmp = last;
        last = new LinkedListNode(o);
        last.setPrevious(tmp);
        tmp.setNext(last);
    }
    public Object getLastValue(){
        if (!isEmpty()){
            return last.getO();
        }
        throw new RuntimeException("Linked List is empty !");
    }
    public  void deleteLast(){
        if (!isEmpty()){
            if (last.getPrevious() != null) {
                last = last.getPrevious();
                last.setNext(null);
            }else{
                first = null;
                last = null;
            }
        }else {
            throw new RuntimeException("Linked List is empty !");
        }
    }
}
