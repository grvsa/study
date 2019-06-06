package ru.geekbrains.java2.lesson2;

public enum DayOfWeek {
    MONDAY("ПОНЕДЕЛЬНИК",8),
    TUESDAY("ВТОРНИК",8),
    WEDNESDAY("СРЕДА",8),
    THURSDAY("ЧЕТВЕРГ",8),
    FRIDAY("ПЯТНИЦА",8),
    SATURDAY("СУББОТА",0),
    SUNDAY("ВОСКРЕСЕНЬЕ",0);

    int hour;
    String rus;

    public int getHour() {
        return hour;
    }

    DayOfWeek(String rus, int hour) {
        this.hour = hour;
        this.rus = rus;
    }
}
