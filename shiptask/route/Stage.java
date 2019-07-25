package ru.geekbrains.java3.lesson5.shiptask.route;

import ru.geekbrains.java3.lesson5.shiptask.Ship;

public abstract class Stage {
    private int length;
    private String name;

    public abstract void go(Ship ship);

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public Stage(int length, String name) {
        this.length = length;
        this.name = name;
    }
}
