package ru.geekbrains.algjava.lesson3;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueneHM {
    private class Item{
        public int id;
        public long count;
        public Object o;

        public Item(int id, long count, Object o) {
            this.id = id;
            this.count = count;
            this.o = o;
        }
    }
    private int size;
    private Item[] items;
    private long number;

    public PriorityQueneHM(int size) {
        items = new Item[size];
        this.size = 0;
        number = 0;
    }

    public void insert(Object o, int id){
        if (size == items.length){
            throw new RuntimeException("The Priority Quene is full !");
        }
        if ( o != null){
            size++;
            items[0] = new Item(id,++number,o);
        }
        sort();
    }
    private void sort(){
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if(o1 == null){
                    return -1;
                }
                if (o2 == null){
                    return 1;
                }
                if (o1.id > o2.id){
                    return -1;
                }else if (o1.id < o2.id){
                    return 1;
                }else{
                    return o1.count > o2.count ? -1 : (o1.count < o2.count ? 1 : 0);
                }
            }
        });
    }
    public Object remove(){
        if (size != 0) {
            Object o = items[items.length - 1].o;
            items[items.length - 1] = null;
            size--;
            sort();
            return o;
        }
        return null;
    }

    public Object peek(){
        if (size != 0){
            return items[items.length - 1].o;
        }
        return null;
    }
    public void print(){
         for (int i = 0; i < items.length; i++) {
            System.out.println(items[i] == null ? "[ null ]" : "[Item: " + items[i].o + "\t Id: " + items[i].id + "\t Count: " + items[i].count + " ] ");
        }
    }
}
