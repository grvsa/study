package ru.geekbrains.java2.lesson5;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
1. Необходимо написать два метода, которые делают следующее:
1) Создают одномерный длинный массив, например:
static final int size = 10000000;
static final int h = size / 2;
float[] arr = new float[size];
2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

Пример деления одного массива на два:
System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:
System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:
Для первого метода надо считать время только на цикл расчета:
for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}
Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
*/
public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        // write your code here
        float[] arr = new float[size];
        Arrays.fill(arr,1.0f);
        float[] arr2 = new float[size];
        System.arraycopy(arr,0,arr2,0,arr.length);

//        float[] arr = new float[100];
//        Arrays.fill(arr,1.0f);
//        float[] arr2 = arr.clone();

        task1(arr);
        task2(4,arr2);

//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i] + "\t" + arr2[i] + "\t= " + (arr[i] - arr2[i]));
//        }
    }

    public static void task1(float... arr) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5.0f) * Math.cos(0.2f + i / 5.0f) * Math.cos(0.4f + i / 2.0f));
        }
        System.out.println("Task1 result: " + (System.currentTimeMillis() - a));
    }

    // на несколько потоков.

    public static void task2(int threadCount, float... arr) {
        long a = System.currentTimeMillis();

        List<ArrayThread> threads = new ArrayList<>();

        int size = arr.length / threadCount;
        for (int i = 0; i < arr.length; i += size) {
            if (i + size > arr.length){
                float[] subArray = new float[arr.length - i];
                System.arraycopy(arr,i,subArray,0,arr.length - i);
                threads.add(new ArrayThread(i,subArray));
            }else{
                float[] subArray = new float[size];
                System.arraycopy(arr,i,subArray,0,size);
                threads.add(new ArrayThread(i,subArray));
            }
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threads.size(); i++) {
            float[] cArray = threads.get(i).getArray();
            int seed = threads.get(i).getSeed();
            System.arraycopy(cArray,0,arr,seed,cArray.length);
        }

        System.out.println("Task2 result: " + (System.currentTimeMillis() - a));
    }
}
