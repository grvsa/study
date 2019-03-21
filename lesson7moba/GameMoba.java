package ru.geekbrains.java1.lesson7moba;

import ru.geekbrains.java1.lesson7moba.units.Healer;
import ru.geekbrains.java1.lesson7moba.units.Thief;
import ru.geekbrains.java1.lesson7moba.units.Unit;
import ru.geekbrains.java1.lesson7moba.units.Warrior;

public class GameMoba {
    private Player p1;
    private Player p2;
    private User user;

    public static void main(String[] args) {
	// write your code here
        GameMoba game = new GameMoba();
        game.setPlayers();
        game.run();

    }

    public void setPlayers(){
        p1 = new Player("GRVSA");
        p1.addArmyUnit(new Warrior(100,10,p1.getName()));
        p1.addArmyUnit(new Warrior(100,10,p1.getName()));
        p1.addArmyUnit(new Thief(60,25,p1.getName()));
        p1.addArmyUnit(new Healer(50,5,30,p1.getName()));
//
        p2 = new Player("Sargt");
        p2.addArmyUnit(new Warrior(100,10,p2.getName()));
        p2.addArmyUnit(new Warrior(100,10,p2.getName()));
        p2.addArmyUnit(new Thief(100,10,p2.getName()));
        p2.addArmyUnit(new Healer(50,5,30,p2.getName()));
//
        user = new User("User");
        user.addArmyUnit(new Warrior(100,10,user.getName()));
        user.addArmyUnit(new Warrior(100,10,user.getName()));
        user.addArmyUnit(new Warrior(100,10,user.getName()));
        user.addArmyUnit(new Healer(50,5,30,user.getName()));

        user.addEnemyArmy(p2.getArmy());
        p2.addEnemyArmy(p1.getArmy());
        p1.addEnemyArmy(p2.getArmy());
    }

    public void run(){
//      Игрок + компьютер
//        while (!user.allDead() && !p2.allDead()){
//            System.out.println("------------------------------------------------------------------------------");
//            user.newTurn();
//            user.turn();
//            if (user.checkWin()){
//                System.out.println("Победил " + user.getName());
//                break;
//            }
//            System.out.println("------------------------------------------------------------------------------");
//            p2.newTurn();
//            p2.turn();
//            if (p2.checkWin()){
//                System.out.println("Победил " + p2.getName());
//                break;
//            }
//        }
//        result();

//        Игра 2х компьютеров.

        while (!p1.checkWin() || !p2.checkWin()){
            System.out.println("------------------------------------------------------------------------------");
            p1.newTurn();
            p1.turn();
            if (p1.checkWin()){
                System.out.println("Победил " + p1.getName());
                break;
            }
            System.out.println("------------------------------------------------------------------------------");
            p2.newTurn();
            p2.turn();
            if (p2.checkWin()){
                System.out.println("Победил " + p2.getName());
                break;
            }
        }
        System.out.println("------------------------------------------------------------------------------");
        result();
    }

    public void result(){

        for (Unit u:p1.getArmy()
        ) {
            System.out.println(u.getInfo() + " Уровень/Опыт: " + u.getLevel() + "/" + u.getExp() + " Убито:" + u.getKills());
        }
        System.out.println("------------------------------------------------");
        for (Unit u:p2.getArmy()
        ) {
            System.out.println(u.getInfo() + " Уровень/Опыт: " + u.getLevel() + "/" + u.getExp() + " Убито:" + u.getKills());
        }
    }
}
