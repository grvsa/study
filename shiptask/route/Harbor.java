package ru.geekbrains.java3.lesson5.shiptask.route;

import ru.geekbrains.java3.lesson5.shiptask.Ship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Harbor extends Stage {
    private Set<Port> ports;

    public Harbor(String name, Port...ports) {
        super(1, name);
        this.ports = new HashSet<>(Arrays.asList(ports));
    }

    @Override
    public void go(Ship ship) {
        System.out.println(ship.getName() + " прибыл в залив " + getName());
    }

    public Set<Port> getPorts() {
        return ports;
    }
}
