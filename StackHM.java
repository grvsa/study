package ru.geekbrains.algjava.lesson3;

public class StackHM {
    private Object[] oArray;
    private int index;

    public void push(Object o){
        if (index == oArray.length - 1){
            throw new RuntimeException("The stack is full !");
        }
        oArray[++index] = o;
    }
    public Object peek(){
        if (index >= 0){
            return oArray[index];
        }
        return null;
    }

    public Object pop(){
        if (index >= 0){
            return oArray[index--];
        }
        return null;
    }

    public StackHM(int size) {
        oArray = new Object[size];
        index = -1;
    }
}
