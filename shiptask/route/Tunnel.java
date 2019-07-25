package ru.geekbrains.java3.lesson5.shiptask.route;

import ru.geekbrains.java3.lesson5.shiptask.Ship;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore semaphore;

    public Tunnel(int length, String name, int lines) {
        super(length, name);
        semaphore = new Semaphore(lines);
    }

    @Override
    public void go(Ship ship) {
        try {
            System.out.println(ship.getName() + " у входа в канал " + getName());
            semaphore.acquire();
            System.out.println(ship.getName() + " начал прохождение канала " + getName());
            Thread.sleep(getLength() / ship.getSpeed() * 1000);
            System.out.println(ship.getName() + " закончил прохождение канала " + getName());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
