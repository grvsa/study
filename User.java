package ru.geekbrains.java1.lesson7moba;

import ru.geekbrains.java1.lesson7moba.units.Healer;
import ru.geekbrains.java1.lesson7moba.units.Unit;

import java.util.Scanner;

public class User extends Player{
    private static Scanner scanner = new Scanner(System.in);
    @Override
    public void turn() {
        Unit[] army = getArmy();
        Unit[] enemy = getEnemyArmy();

        while (true){
            System.out.println("Выберите действие \n" +
                    "[1] Всем атаковать\n" +
                    "[2] Лечить\n" +
                    "[3] Назначить противников\n" +
                    "[4] Состояние армии\n" +
                    "[5] Состояние армии противника\n" +
                    "[6] Пропуск хода");
            switch (getAction(6)){
                case 1: for (int i = 0; i < getArmy().length; i++) {
                        if (army[i].isAlive() && army[i].isCanAction()){
                            army[i].attack();
                            army[i].setCanAction(false);
                        }
                    }
                    return;
                case 2:
                    int count = 0;
                    Unit healer = null;
                    Unit target = null;
                    for (int i = 0; i < army.length; i++) {

                        System.out.println(army[i].getFullInfo());
                        if(army[i] instanceof Healer && army[i].isAlive() && army[i].isCanAction()){
                            count++;
                        }

                    }
                    if (count > 0) {
                        System.out.println("Выберите Healer");
                        int a = 0;
                        do {
                            a = getAction(army.length);
                            healer = army[a - 1];
                        } while (!(healer instanceof Healer) || !healer.isAlive() || !army[a - 1].isCanAction());
                    }else{
                        System.out.println("Нет доступных Healer");
                        break;
                    }
                    System.out.println("Выберите кого лечить");
                    do{
                        target = army[getAction(army.length) - 1];
                    }while(!target.isAlive());

                    if (healer != null && target !=null){
                        ((Healer) healer).castHeal(target);
                        healer.setCanAction(false);
                    }
                    break;
                case 3:
                    Unit soldier = null;
                    Unit atack = null;
                    for (Unit u: army
                    ) {
                        System.out.println(u.getFullInfo());
                    }
                    System.out.println("Выберите солдата");

                    do{
                        soldier = army[getAction(army.length) - 1];
                    }while(!soldier.isAlive());
                    for (Unit u: enemy
                    ) {
                        System.out.println(u.getFullInfo());
                    }
                    System.out.println("Выберите противника");

                    do{
                        atack = enemy[getAction(enemy.length) - 1];
                    }while(!atack.isAlive());
                    soldier.assignEnemy(atack);
                    break;
                case 4: for (Unit u: army
                         ) {
                        System.out.println(u.getFullInfo());
                    }
                    break;
                case 5:
                    for (Unit u: enemy
                    ) {
                        System.out.println(u.getFullInfo());
                    }
                    break;
                case 6:
                    return;
            }
        }
    }

    public int getAction(int limiter){
        int result = -1;

        do{
            try{
                result = scanner.nextInt();
            }catch (Exception e){
                result = -1;
            }
        }while(result <= 0 && result > limiter);

        return result;
    }

    public User(String name) {
        super(name);
    }
}
