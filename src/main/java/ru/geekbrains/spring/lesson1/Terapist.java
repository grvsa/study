package ru.geekbrains.spring.lesson1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("terapist")
public class Terapist extends AbstractDoctor {
    @Autowired
    public Terapist(VisitorCard visitorCard) {
        super(visitorCard,"Терапевт");
    }

    public Terapist() {
        super("Терапевт");
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
