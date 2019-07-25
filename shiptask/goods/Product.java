package ru.geekbrains.java3.lesson5.shiptask.goods;

public abstract class Product {
    protected String name;
    private int weight;

    public Product(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
