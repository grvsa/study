package ru.geekbrains.algjava.lesson4;

public class LinkedListIterator {
    private LinkedList linkedList;
    private LinkedListNode first;
    private LinkedListNode last;
    private LinkedListNode current;

    public LinkedListIterator(LinkedList linkedList) {
        this.linkedList = linkedList;
        reset();
    }
    public void reset(){
        first = linkedList.getFirst();
        current = first;
        last = linkedList.getLast();
    }
    public boolean atEnd(){
        return current == last ? true : false;
    }
    public Object getCurrent(){
        return current.getO();
    }
    public void deleteCurrent(){
        if (linkedList.isEmpty()){
            throw new RuntimeException("Linked list is empty !");
        }else{
            if (current.getNext() == null && current.getPrevious() == null){
                linkedList.delete();
                first = null;
                last = null;
                current = null;
            }else if (current.getPrevious() == null){
                linkedList.delete();
                first = linkedList.getFirst();
                current = first;
            }else if (current.getNext() == null){
                linkedList.deleteLast();
                last = linkedList.getLast();
                current = last;
            }else{
                LinkedListNode tmp = current;
                current = current.getNext();
                current.setPrevious(tmp.getPrevious());
                tmp.getPrevious().setNext(current);
            }
        }

    }
    public void insertBefore(Object o){
        if (linkedList.isEmpty()){
            linkedList.insert(o);
            reset();
        }else{
            if (current.getPrevious() == null){
                linkedList.insert(o);
                first = linkedList.getFirst();
            }else{
                LinkedListNode tmp = new LinkedListNode(o);
                current.getPrevious().setNext(tmp);
                tmp.setPrevious(current.getPrevious());
                tmp.setNext(current);
                current.setPrevious(tmp);
            }
        }
    }
    public void insertAfter(Object o){
        if (linkedList.isEmpty()){
            linkedList.insert(o);
            reset();
        }else{
            if (atEnd()){
                linkedList.insertLast(o);
                last = linkedList.getLast();
            }else{
                LinkedListNode tmp = current.getNext();
                current.setNext(new LinkedListNode(o));
                current.getNext().setPrevious(current);
                current.getNext().setNext(tmp);
                tmp.setPrevious(current.getNext());
            }
        }
    }
    public Object nextLink(){
        if (!atEnd()){
            current = current.getNext();
            return current.getO();
        }
        throw new RuntimeException("At end !");
    }

}
