package ru.geekbrains.java3.lesson1;

import ru.geekbrains.java3.lesson1.fruits.Fruit;

import java.util.ArrayList;
import java.util.Arrays;

public class Box <T extends Fruit> {
    private ArrayList<T> box;

    public Box() {
        box = new ArrayList<>();
    }

    public void add(T...fruit){
        box.addAll(Arrays.asList(fruit));
    }

    public float getWeight(){
        return box.stream().map(x -> x.getWeight()).reduce(0.0f,(x,y) -> x + y);
    }

    public boolean compare(Box<?> box){
        return getWeight() == box.getWeight();
    }
    // Не хотел делать ArrayList - public
    public void moveTo(Box<T> box){
        this.box.forEach(x-> box.add(x));
        this.box.clear();
    }
}
