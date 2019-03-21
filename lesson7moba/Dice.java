package ru.geekbrains.java1.lesson7moba;

import java.util.Random;

public class Dice {
    Random dice;

    public Dice() {
        dice = new Random();
    }
    public int getChance(){
        return (dice.nextInt(100) + dice.nextInt(100) + dice.nextInt(100) + 3) / 3;
    }
}
