package ru.geekbrains.spring.lesson1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dentist")
public class Dentist extends AbstractDoctor {
    @Autowired
    public Dentist(VisitorCard visitorCard) {
        super(visitorCard, "Зубной врач");
    }

    public Dentist() {
        super("Зубной врач");
    }

    @Override
    @Autowired
    public void setVisitorCard(VisitorCard visitorCard) {
        super.setVisitorCard(visitorCard);
    }

    @Override
    public void processing() {
        System.out.println("Добрый день " + getVisitorCard().getName() + getVisitorCard().getNumber());
        System.out.println("Вы находитесь на приеме у врача " + getName());
    }
}
