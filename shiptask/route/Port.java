package ru.geekbrains.java3.lesson5.shiptask.route;

import ru.geekbrains.java3.lesson5.shiptask.Ship;
import ru.geekbrains.java3.lesson5.shiptask.goods.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port{
    private Map<Product,Integer> goods;
    private Map<Product, Integer> reservedGoods;
    private Lock lock;
    private String name;

    public Port(String name) {
        this.name = name;
        goods = new HashMap<>();
        reservedGoods = new HashMap<>();
        lock = new ReentrantLock();
    }

    public String getName() {
        return name;
    }

    public void addGoods(Product product, int volume){
        lock.lock();
        goods.put(product,goods.getOrDefault(product,0) + volume);
        lock.unlock();
    }

    public int reservation(Ship ship, int freeSpace){
        lock.lock();
        int reserved = 0;

//        reservedGoods.put(product,reservedGoods.getOrDefault(product,0) + volume);
//        int available = goods.get(product) - volume;
//        if (available != 0){
//            goods.put(product, available);
//        }else{
//            goods.remove(product);
//        }

        int volume = 0;
        Product key = null;

        for (Map.Entry<Product, Integer> map :
                goods.entrySet()) {
            if (map.getKey().getClass() == ship.getType()){
                key = map.getKey();
                volume = map.getValue();
            }
        }
        if (key != null){
            if (ship.getFreeSpace() - freeSpace >= volume){
                reservedGoods.put(key,reservedGoods.getOrDefault(key,0) + volume);
                reserved = volume;
                goods.remove(key);
            }else{
                reservedGoods.put(key,reservedGoods.getOrDefault(key,0) + ship.getFreeSpace() - freeSpace);
                reserved = ship.getFreeSpace() - freeSpace;
                goods.put(key,volume - ship.getFreeSpace() + freeSpace);
            }
        }
        lock.unlock();
        return reserved;
    }

    public void load(Ship ship, Harbor harbor){
        lock.lock();
        Product key = null;
        int value = 0;
        for (Map.Entry<Product, Integer> map :
                reservedGoods.entrySet()) {
            if (map.getKey().getClass() == ship.getType()) {
                key = map.getKey();
                value = map.getValue();
            }
        }
        if (key != null && ship.getFreeSpace() != 0) {
            if (ship.getFreeSpace() >= value) {
                ship.load(key,value);
                System.out.println(ship.getName() +
                        " загрузил в порту " + getName() +
                        " залива " + harbor.getName() +
                        " товар " + key.getName() +
                        " колличеством " + value + " ед.");
                reservedGoods.remove(key);
            }else{
                reservedGoods.put(key,reservedGoods.get(key) - ship.getFreeSpace());
                System.out.println(ship.getName() +
                        " загрузил в порту " + getName() +
                        " залива " + harbor.getName() +
                        " товар " + key.getName() +
                        " колличеством " + ship.getFreeSpace() + " ед.");
                ship.load(key,ship.getFreeSpace());
            }
        }
        lock.unlock();
    }

    public void unLoad(Product product, int volume){
        lock.lock();
        goods.put(product,goods.getOrDefault(product,0) + volume);
        lock.unlock();
    }

    public void info(){
        for (Map.Entry<Product, Integer> map :
                goods.entrySet()) {
            System.out.println("Unassigned goods : " + map.getKey().getName() + " " + map.getValue());
        }
        for (Map.Entry<Product, Integer> map :
                goods.entrySet()) {
            System.out.println("Reserved goods : " + map.getKey().getName() + " " + map.getValue());
        }
    }

    public int getTotalAmount(){
        int result = 0;
        for (Map.Entry<Product, Integer> map :
                goods.entrySet()) {
            result += map.getValue();
        }
        return result;
    }
}
