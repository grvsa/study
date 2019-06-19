package ru.geekbrains.java2.lesson5;

public class ArrayThread extends Thread {
    private float[] array;
    private int seed;

    public float[] getArray() {
        return array;
    }

    public int getSeed() {
        return seed;
    }

    public ArrayThread(int seed, float...array){
        super();
        this.array = array;
        this.seed = seed;
    }

    @Override
    public void run() {
//        System.out.println("Thread started" + Thread.currentThread().getName());
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + (i + seed) / 5.0f) * Math.cos(0.2f + (i + seed) / 5.0f) * Math.cos(0.4f + (i + seed) / 2.0f));
        }

    }
}
