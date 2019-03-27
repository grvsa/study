package ru.geekbrains.java1.lesson7moba.units;

public class Healer extends Unit{
    private int heal;

    public void castHeal(Unit unit){
        getCombatInfo().append(getInfo() + "Лечит - " + unit.cure(heal) + "\n");
    }

    public Healer(int health, int damage, int heal, String name) {
        super(health, damage, name);
        this.heal = heal;
    }
}
