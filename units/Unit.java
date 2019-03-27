package ru.geekbrains.java1.lesson7moba.units;

import ru.geekbrains.java1.lesson7moba.Dice;

import javax.swing.*;

abstract public class Unit {
    private static float[] expScale = {0,1,2,3,4,5,6,7,8,47,58,70};
    private float exp;
    private int level;
    private int maxHealth;
    private int health;
    private int damage;
    private String name;
    private Dice dice;
    private boolean isAlive;
    private Unit enemy;
    private int kills;
    private boolean canAction;
    private StringBuilder combatInfo;

    public StringBuilder getCombatInfo() {
        return combatInfo;
    }

    public String getCombatStatus(){
        String result = combatInfo.toString();
        combatInfo = new StringBuilder();
        return result;
    }

    public boolean isCanAction() {
        return canAction;
    }

    public void setCanAction(boolean canAction) {
        this.canAction = canAction;
//        combatInfo = new StringBuilder();
    }

    public String getFullInfo() {
        return getInfo() + (isAlive ? ( enemy == null ? " Нет противника" : " сражается с " + enemy.getInfo()) : " Мертв");
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Dice getDice() {
        return dice;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public float healthStatus() {
        return maxHealth / health;
    }

    public float getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public String damage(int damage) {
        if(dice.getChance() < 20){
            return getInfo() + " Уклонился от удара";
        }else{
            health -= damage;
            if (health <= 0){
                isAlive = false;
                return getInfo() + " Умер от повреждений";
            }else{
                return getInfo() + " Получил повреждения " + damage;
            }
        }

    }

    public boolean assignEnemy(Unit unit){
        if (unit.isAlive){
            enemy = unit;
            combatInfo.append(getInfo() + " Будет сражаться с " + enemy.getInfo() + "\n");
            return true;
        }
        combatInfo.append(getInfo() + " Не может сражаться с " + unit.getInfo() + " Противник уже мертв.\n");
        return false;
    }

    public int getKills() {
        return kills;
    }

    public void attack(){
        if (enemy == null){
            combatInfo.append(getInfo() + " Не может атаковать - нет противника\n");
            return;
        }
        if (enemy.isAlive()){
            int attack = dice.getChance() * damage / 100;
            combatInfo.append(getInfo() + " Атакует противника " + attack + " - " + enemy.damage(attack) + "\n");

            if (!enemy.isAlive()){
                exp = exp + (float) enemy.getStat() / getStat();
                kills++;
                enemy = null;
                while (expScale[level + 1] < exp){
                    level++;
                    maxHealth *= 1.2f;
                    damage *= 1.2f;
                    health = maxHealth;
                    combatInfo.append(getInfo() + " Получил " + level + " уовень !\n");
                }
            }

        }else{
            combatInfo.append(getInfo() + " Не может атаковать - противник уже мертв\n");
            enemy = null;
        }
    }

    public int getStat(){
        return maxHealth*damage;
    }

    public String cure(int cure){
        if (isAlive) {
            health = health + cure > maxHealth ? maxHealth : health + cure;
            return getInfo() + " Получил лечение";
        }else {
            return getInfo() + "Мертв - вылечить невозможно";
        }
    }

    public boolean isAlive(){
        return isAlive;
    }

    public boolean isFighting(){
        return enemy == null ? false : true;
    }

    public String getInfo(){
        return "[" + (canAction ? " ]" : "X]") + name + " [" + maxHealth + "/" + health + "]:[" + damage + "] ";
    }

    public Unit(int health, int damage, String name) {
        this.health = health;
        this.damage = damage;
        this.maxHealth = health;
        this.name = "[" + name + " " + this.getClass().getSimpleName() + "]";
        this.dice = new Dice();
        this.isAlive = true;
        this.level = 0;
        this.exp = 0;
        this.enemy = null;
        this.kills = 0;
        this.canAction = true;
        combatInfo = new StringBuilder();
    }
}
