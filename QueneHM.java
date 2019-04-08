package ru.geekbrains.algjava.lesson3;

import java.util.Arrays;

public class QueneHM {
    protected Object[] oArray;
    private int first;
    private int size;

    public QueneHM(int size) {
        oArray = new Object[size];
        first = 0;
        this.size = 0;
    }

    public Object remove(){
        if (size > 0){
            Object o = oArray[first];
            size--;
            first = ++first % oArray.length;
            return  o;
        }
        return null;
    }

    public Object peekFront(){
        if (size > 0){
            return  oArray[first];
        }
        return null;
    }

    public void insert(Object o){
        if (size == oArray.length){
            throw new RuntimeException("The quene is full !");
        }
        oArray[(first + size++) % oArray.length] = o;
    }

    public void print(){
        System.out.println("First: " + first);
        System.out.println("Size: " + size);
        System.out.println(Arrays.toString(oArray));
    }
}
