package ru.geekbrains.java1.lesson7moba.units;

public class Thief extends Unit {

    @Override
    public void damage(int damage) {
        if(getDice().getChance() < 50){
            System.out.println(getInfo() + " Уклонился от удара");
        }else{
            setHealth(super.getHealth() - damage);
            if (getHealth() <= 0){
                setAlive(false);
                System.out.println(getInfo() + " Умер от повреждений");
            }else{
                System.out.println(getInfo() + " Получил повреждения " + damage);
            }
        }
    }

    public Thief(int health, int damage, String name) {
        super(health, damage, name);
    }
}
