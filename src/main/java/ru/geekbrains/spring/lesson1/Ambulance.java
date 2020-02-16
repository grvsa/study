package ru.geekbrains.spring.lesson1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ambulance")
public class Ambulance extends AbstractDoctor {
    @Autowired
    public Ambulance(VisitorCard visitorCard) {
        super(visitorCard, "Скорая помощь");
    }

    public Ambulance() {
        super("Скорая помощь");
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
