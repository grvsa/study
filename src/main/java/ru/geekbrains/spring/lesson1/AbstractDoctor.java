package ru.geekbrains.spring.lesson1;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDoctor {
    @Autowired
    private VisitorCard visitorCard;
    private String name;

    public VisitorCard getVisitorCard() {
        return visitorCard;
    }

    @Autowired
    public void setVisitorCard(VisitorCard visitorCard) {
        this.visitorCard = visitorCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    public AbstractDoctor(VisitorCard visitorCard) {
        this.visitorCard = visitorCard;
    }

    public AbstractDoctor(String name) {
        this.name = name;
    }

    @Autowired
    public AbstractDoctor(VisitorCard visitorCard, String name) {
        this.visitorCard = visitorCard;
        this.name = name;
    }

    public AbstractDoctor() {
    }

    public abstract void processing();
}
