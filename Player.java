package ru.geekbrains.java1.lesson7moba;

import ru.geekbrains.java1.lesson7moba.units.Healer;
import ru.geekbrains.java1.lesson7moba.units.Unit;

import javax.swing.*;
import java.util.Random;

public class Player {
    private Unit[] army;
    private Unit[] enemy;
    private String name;
    private StringBuilder combatInfo;

    public Unit[] getEnemyArmy() {
        return enemy;
    }

    public void newTurn(){
        if (army != null) {
            for (Unit u : army
            ) {
                u.setCanAction(true);
            }
        }
    }
    public Player(String name) {
        this.army = null;
        this.enemy = null;
        this.name = name;
    }

    public void addArmyUnit(Unit unit){
        if (army == null){
            army = new Unit[1];
            army[0] = unit;
        }else{
            Unit[] temp = new Unit[army.length + 1];
            for (int i = 0; i < army.length; i++) {
                temp[i] = army[i];
            }
            temp[army.length] = unit;
            army = temp;
        }
    }

    public void addEnemyArmy(Unit[] enemyArmy){
        if (enemyArmy != null){
            enemy = enemyArmy;
        }else{
            combatInfo.append("Попытка добавить пустую армию !\n");
        }
    }

    public Unit[] getArmy(){
        return army;
    }

    public String getName() {
        return name;
    }

    public void turn(){
        if (!checkWin()) {
            for (Unit u : army
            ) {
                if (u.isAlive() && !u.isFighting()) {
                    Random r = new Random();
                    int aEnemy;
                    do{
                         aEnemy = r.nextInt(enemy.length);
                    }while (!enemy[aEnemy].isAlive());
                    u.assignEnemy(enemy[aEnemy]);
                }
            }
            for (Unit u: army
                 ) {
                if (u.isAlive() && u.isCanAction()){
                    if (u instanceof Healer){
                        float maxDemaged = 1;
                        Unit unit = null;
                        for (Unit heal: army
                        ) {
                            if(heal.isAlive()) {
                                if (maxDemaged < heal.healthStatus()) {
                                 maxDemaged = heal.healthStatus();
                                 unit = heal;
                                 }
                            }
                        }
                        if(maxDemaged > 2){
                            ((Healer) u).castHeal(unit);
                            u.setCanAction(false);

                        }else{
                            u.attack();
                            u.setCanAction(false);
                        }
                    }else {
                        u.attack();
                        u.setCanAction(false);
                    }
                }
            }

        }
    }
    public String getCombatInfo(){
        combatInfo = new StringBuilder();
        combatInfo.append("------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        if (army != null) {
            for (Unit u : army
            ) {
                combatInfo.append(u.getCombatStatus());
            }
        }
        return combatInfo.toString();
    }
    public void getArmyInfo(){
        for (Unit u: army
             ) {
            System.out.println("Армия " + name + " " + u.getInfo() + (u.isAlive() ? " Жив" : " Мертв"));
        }
    }

    public void getEnemyInfo(){
        for (Unit u: enemy
        ) {
            System.out.println("Армия противника " + u.getInfo() + (u.isAlive() ? " Жив" : " Мертв"));
        }
    }

    public boolean checkWin(){
        boolean result = true;
        for (Unit u:enemy) {
            if (u.isAlive()){
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean allDead(){
        boolean result = true;
        for (Unit u: army
             ) {
            if (u.isAlive()){
                result = false;
            }
        }
        return result;
    }
}
