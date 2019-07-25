package ru.geekbrains.java3.lesson5.carstask.stages;

import ru.geekbrains.java3.lesson5.carstask.Car;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
