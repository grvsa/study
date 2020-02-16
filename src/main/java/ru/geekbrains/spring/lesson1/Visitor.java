package ru.geekbrains.spring.lesson1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Visitor {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        AbstractDoctor doctor = context.getBean("terapist", AbstractDoctor.class);
        doctor.processing();

        doctor = context.getBean("terapist", AbstractDoctor.class);
        doctor.processing();

        doctor = context.getBean("ambulance", AbstractDoctor.class);
        doctor.processing();

        VisitorCard visitorCard = context.getBean("visitorcard", VisitorCard.class);
        System.out.println("!" + visitorCard.getName() + visitorCard.getNumber());

        visitorCard = context.getBean("visitorcard", VisitorCard.class);
        System.out.println("!" + visitorCard.getName() + visitorCard.getNumber());


    }
}
