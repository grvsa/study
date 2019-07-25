package ru.geekbrains.java3.lesson5.shiptask;

import ru.geekbrains.java3.lesson5.shiptask.goods.Product;
import ru.geekbrains.java3.lesson5.shiptask.route.Harbor;
import ru.geekbrains.java3.lesson5.shiptask.route.Port;
import ru.geekbrains.java3.lesson5.shiptask.route.Route;


import java.util.*;

public class Ship<T extends Product> extends Thread {
    private static int count = 0;
    private final int maxWeight;
    private final int maxSpeed;
    private final Class genType;
    private Route route;
    private Actions currentAction;
    private Map<T, Integer> cargo;
    private Harbor currentHarbor;
    private boolean running = true;

    public Ship(int maxWeight, int maxSpeed, Class<T> genType) {
        this.maxWeight = maxWeight;
        this.maxSpeed = maxSpeed;
        this.genType = genType;
        currentAction = Actions.WAIT;
        cargo = new HashMap<>();
        setName("Корабль#" + ++count + " с " + genType.getSimpleName());
    }

    public void load(T product, int volume) {
        cargo.put(product, cargo.getOrDefault(product, 0) + volume);
    }

    public Actions getCurrentAction() {
        return currentAction;
    }

    public int getWeight() {
        int result = 0;
        for (Map.Entry<T, Integer> map :
                cargo.entrySet()) {
            result += map.getValue();
        }
        return result;
    }

    public int getFreeSpace() {
        return maxWeight - getWeight();
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void go() {
        currentAction = Actions.SAILFORWARD;
    }

    public Class getType() {
        return genType;
    }

    public int getSpeed() {
        return maxSpeed - (int) (getWeight() / 1.0 / maxWeight * maxSpeed / 2);
    }

    public void stopShip() {
        currentAction = Actions.STOP;
        running = false;
    }

    @Override
    public void run() {

        while (running) {
            switch (currentAction) {
                case WAIT:
                    System.out.println("Wait" + getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case SAILFORWARD:
                    for (int i = 1; i < route.getRoute().size(); i++) {
                        route.getRoute().get(i).go(this);
                    }
                    currentAction = Actions.LOAD;
                    break;
                case LOAD:
                    currentHarbor = (Harbor) route.getRoute().get(route.getRoute().size() - 1);
                    Set<Port> portsLoad = currentHarbor.getPorts();
                    for (Port p :
                            portsLoad) {
                        p.load(this, currentHarbor);
                    }
                    currentAction = Actions.SAILBACK;
                    break;
                case SAILBACK:
                    for (int i = route.getRoute().size() - 2; i >= 0; i--) {
                        route.getRoute().get(i).go(this);
                    }
                    currentAction = Actions.UNLOAD;
                    break;
                case UNLOAD:
                    currentHarbor = (Harbor) route.getRoute().get(0);
                    Set<Port> portSet = currentHarbor.getPorts();
                    Port port = null;
                    for (Port p :
                            portSet) {
                        port = p;
                    }
                    if (port != null) {
                        for (Map.Entry<T, Integer> map :
                                cargo.entrySet()) {
                            port.unLoad(map.getKey(), map.getValue());
                            System.out.println(getName() +
                                    " разгрузил в порту " + port.getName() +
                                    " залива " + currentHarbor.getName() +
                                    " товар " + map.getKey().getName() +
                                    " колличеством " + map.getValue() + " ед.");
                        }
                        cargo.clear();
                    }
                    currentAction = Actions.WAIT;
                    break;
                case STOP:
                    running = false;
                    interrupt();
                    currentAction = Actions.STOP;
                    break;
            }
        }
        System.out.println("Thread interrupted - Bye !");
    }
}
