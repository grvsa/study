package ru.geekbrains.java3.lesson5.shiptask.route;

import java.util.ArrayList;
import java.util.Arrays;

public class Route {
    private ArrayList<Stage> route;

    public Route(Stage... stages) {
        this.route = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getRoute() {
        return route;
    }
}
