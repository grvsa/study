package ru.geekbrains.java3.lesson5.shiptask.route;

import ru.geekbrains.java3.lesson5.shiptask.Ship;

public class Sea extends Stage {

    public Sea(int length, String name) {
        super(length, name);
    }

    @Override
    public void go(Ship ship) {
        System.out.println(ship.getName() + " вышел море " +  getName());
        try {
            Thread.sleep(getLength() / ship.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ship.getName() + " прошел море " +  getName());
    }
}
