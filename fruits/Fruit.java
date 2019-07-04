package ru.geekbrains.java3.lesson1.fruits;

public abstract class Fruit {
    private float weight;

    public final float getWeight(){
        return weight;
    }

    public Fruit(float weight) {
        this.weight = weight;
    }
}
