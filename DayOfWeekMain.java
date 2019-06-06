package ru.geekbrains.java2.lesson2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class DayOfWeekMain {
    public static void main(String[] args) {

        /*
         Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
         С его помощью необходимо решить задачу определения кол-ва рабочих часов до конца недели
         по заднному текущему дню.

         Возвращает кол-во оставшихся рабочих часов до конца
         недели по заданному текущему дню. Считается, что
         текущий день ещё не начался, и рабочие часы за него
         должны учитываться.
        */
        System.out.println(getWorkingHours(DayOfWeek.FRIDAY));
    }
    public static int getWorkingHours(DayOfWeek dayOfWeek){
        int result = 0;
        for (DayOfWeek d :
                DayOfWeek.values()) {
            if (d.ordinal() >= dayOfWeek.ordinal()){
                result += d.getHour();
            }
        }
        return result;
    }
}
