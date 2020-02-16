package ru.geekbrains.spring.lesson1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("visitorcard")
@Scope("prototype")
public class VisitorCard {
    private static int number;

    {
        number++;
    }

    @Value("Semen Semenov")
    private String name;

    public VisitorCard(String name) {
        this.name = name;
    }

    public VisitorCard() {
        this.name = "Абстрактный пациент";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getNumber() {
        return number;
    }
}
