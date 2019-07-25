package ru.geekbrains.java3.lesson5.shiptask;

import ru.geekbrains.java3.lesson5.shiptask.goods.Cloth;
import ru.geekbrains.java3.lesson5.shiptask.goods.Food;
import ru.geekbrains.java3.lesson5.shiptask.goods.Oil;
import ru.geekbrains.java3.lesson5.shiptask.route.*;

public class Main {
    public static void main(String[] args) {
        Ship<Oil> oilShip = new Ship<>(1000,300,Oil.class);
        Ship<Cloth> clothShip = new Ship<>(1000,300,Cloth.class);
        Ship<Food> foodShip = new Ship<>(1000,300,Food.class);

        Food food = new Food(1);
        Cloth cloth = new Cloth(3);
        Oil oil = new Oil(5);

        Port start = new Port("Начало Пути");
        Port end1 = new Port("Конец Пути 1");
        Port end2 = new Port("Конец Пути 2");
        Port end3 = new Port("Конец Пути 3");

        end1.addGoods(oil,330);
        end2.addGoods(oil,330);
        end3.addGoods(oil,330);

        end3.addGoods(food,330);
        end2.addGoods(food,330);
        end1.addGoods(food,330);

        end3.addGoods(cloth,330);
        end2.addGoods(cloth,330);
        end1.addGoods(cloth,330);

        Route route = new Route(new Harbor("Начало Пути",start),
                new Tunnel(60,"Начала Пути",2),
                new Sea(100,"Средиземное"),
                new Tunnel(30,"Конца пути",1),
                new Harbor("Конца Пути",end1,end2,end3));

        Dispatcher dispatcher = new Dispatcher(route,2700,oilShip,clothShip,foodShip);
        dispatcher.start();

        try {
            dispatcher.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test");

    }
}
