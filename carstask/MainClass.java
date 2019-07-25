package ru.geekbrains.java3.lesson5.carstask;

import ru.geekbrains.java3.lesson5.carstask.stages.Road;
import ru.geekbrains.java3.lesson5.carstask.stages.Tunnel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

    public static final int CARS_COUNT = 4;
    public static CyclicBarrier barrier= new CyclicBarrier(CARS_COUNT + 1);
    public static ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10),barrier);
        }
        for (int i = 0; i < cars.length; i++) {
            executorService.execute(cars[i]);
        }
        executorService.shutdown();
        try {
            while (barrier.getNumberWaiting()!= CARS_COUNT);
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
