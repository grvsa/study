package ru.geekbrains.algjava.lesson3;

import java.util.Arrays;

public class DequeHM {
    private Object[] oArray;
    private int left;
    private int right;
    private int size;

    public DequeHM(int size) {
        oArray = new Object[size];
        this.size = 0;
        left = 0;
        right = 0;
    }

    public Object peekLeft(){
        if (size > 0){
            return oArray[left];
        }
        return null;
    }

    public Object peekRight(){
        if (size > 0){
            return oArray[right];
        }
        return null;
    }

    public void insertLeft(Object o){
        if (size == oArray.length){
            throw new RuntimeException("The deque is full !");
        }

        if (size != 0){
            if (left == 0) {
                left += oArray.length;
            }
            left--;
        }
        size++;
        oArray[left] = o;
    }

    public void insertRight(Object o){
        if (size == oArray.length){
            throw new RuntimeException("The deque is full !");
        }

        if (size != 0){
            if (++right == oArray.length){
                right = 0;
            }
        }
        size++;
        oArray[right] = o;
    }

    public Object removeLeft(){
        if (size > 0){
            size--;
            Object o = oArray[left];
            oArray[left] = null;
            if (++left == oArray.length){
                left = 0;
            }
            return o;
        }
        return null;
    }

    public Object removeRight(){
        if (size > 0){
            size--;
            Object o = oArray[right];
            oArray[right] = null;
            if (--right < 0){
                right += oArray.length;
            }
            return o;
        }
        return null;
    }

    public void print(){
        System.out.println("Left: " + left + "\t Right: " + right + "\t Size: " + size);
        System.out.println(Arrays.toString(oArray));
    }
}
