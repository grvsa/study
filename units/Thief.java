package ru.geekbrains.java1.lesson7moba.units;

public class Thief extends Unit {

    @Override
    public String damage(int damage) {
        if(getDice().getChance() < 50){
            return getInfo() + " Уклонился от удара";
        }else{
            setHealth(super.getHealth() - damage);
            if (getHealth() <= 0){
                setAlive(false);
                return getInfo() + " Умер от повреждений";
            }else{
                return getInfo() + " Получил повреждения " + damage;
            }
        }
    }

    public Thief(int health, int damage, String name) {
        super(health, damage, name);
    }
}
