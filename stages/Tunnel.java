package ru.geekbrains.java3.lesson5.stages;

import ru.geekbrains.java3.lesson5.Car;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static final Semaphore tunnelCapacitySemaphore = new Semaphore(2,true);
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                tunnelCapacitySemaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                tunnelCapacitySemaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
