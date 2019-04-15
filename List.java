package ru.geekbrains.algjava.lesson4;

public class List {
    private ListNode current;

    public void insert(Object o){
        if (isEmpty()){
            current = new ListNode(o);
        }else{
            ListNode tmp = current;
            current = new ListNode(o);
            current.setNext(tmp);
        }
    }
    public void delete(){
        if (isEmpty()){
            throw new RuntimeException("List is empty !");
        }else{
            if (current.getNext() != null){
                current = current.getNext();
            }else{
                current = null;
            }
        }
    }
    public boolean isEmpty(){
        return current == null;
    }
    public Object getValue(){
        if (current == null){
            throw new RuntimeException("List is empty !");
        }else{
            return current.getO();
        }
    }
    public boolean contais(Object o){
        if (isEmpty()){
            return false;
        }
        return current.contains(o);
    }
    @Override
    public String toString() {
        if (current == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(current.getO());
        ListNode tmp = current;
        while (tmp.getNext() !=null){
            tmp = tmp.getNext();
            sb.append(", " + tmp.getO());
        }
        return sb.toString();
    }
    public void delete(Object o){
        if (isEmpty() || !contais(o)){
            throw new RuntimeException("List is empty or have no records!");
        }
        if (current.getO().equals(o)){
            delete();
        }else{
            ListNode previous = null;
            ListNode current = this.current;
            while (!current.getO().equals(o)){
                previous = current;
                current = current.getNext();
            }
            previous.setNext(current.getNext());
        }
    }
}
