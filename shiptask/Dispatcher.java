package ru.geekbrains.java3.lesson5.shiptask;

import ru.geekbrains.java3.lesson5.shiptask.route.Harbor;
import ru.geekbrains.java3.lesson5.shiptask.route.Port;
import ru.geekbrains.java3.lesson5.shiptask.route.Route;

import java.util.Set;

public class Dispatcher extends Thread{
    private Ship[] fleet;
    private int target;
    private Route route;
    private int achivment = 0;

    public Dispatcher(Route route, int target, Ship...fleet) {
        this.fleet = fleet;
        this.target = target;
        this.route = route;
    }

    @Override
    public void run() {
        for (Ship ship :
                fleet) {
            ship.setRoute(route);
            ship.start();
        }
        Harbor harbor = (Harbor) route.getRoute().get(route.getRoute().size() - 1);

        while (true){
            for (Ship ship :
                    fleet) {
                if (ship.getCurrentAction() == Actions.WAIT) {
                    int reserved = 0;
                    for (Port port :
                            harbor.getPorts()) {
                        reserved += port.reservation(ship, reserved);
                    }
                    if (reserved != 0 && achivment < target){
                        achivment += reserved;
                        ship.go();
                    }else {
                        ship.stopShip();
                    }
                }
            }
            if (getTotalAmount() >= target){
                for (Ship ship :
                        fleet) {
                    ship.stopShip();
                }
                break;
            }
        }
        System.out.println("Achived !!!!!!!!!");

//        while (getTotalAmount() <= target);

//        for (int i = 0; i < fleet.length; i++) {
//            if (fleet[i].getCurrentAction() != Actions.STOP){
//                i--;
//            }
//        }
//        System.out.println("Bye");
//
//        for (Ship ship :
//                fleet) {
//            ship.interrupt();
//            ship.stopShip();
//        }

    }

    public int getTotalAmount(){
        int result = 0;
        Harbor harbor = (Harbor) route.getRoute().get(0);
        for (Port p :
                harbor.getPorts()) {
            result += p.getTotalAmount();
        }
        return result;
    }
}
