package ru.geekbrains.java3.lesson1hw;

import ru.geekbrains.java3.lesson1hw.fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> items;

    public Box() {
        this.items = new ArrayList<T>();
    }

    public void add(T...item){
        for (T t :
                item) {
            items.add(t);
        }
    }

    public boolean compare(Box<? extends Fruit> box){
        return getWeight() == box.getWeight();
    }

    public float getWeight(){
        float sum = 0.0f;
        for (T t :
                items) {
            sum += t.getWeight();
        }
        return sum;
    }

    public void move(Box<T> box){
        for (T t :
                items) {
            box.add(t);
        }
        items.clear();
    }
}
