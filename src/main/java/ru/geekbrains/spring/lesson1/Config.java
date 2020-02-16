package ru.geekbrains.spring.lesson1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.geekbrains.spring.lesson1")
public class Config {
//    @Bean(name = "visitorcard")
//    public VisitorCard visitorCard(@Value("Petr Petrov") String name){
//        VisitorCard visitorCard = new VisitorCard(name);
//        return visitorCard;
//    }
//
//    @Bean(name = "terapist")
//    public AbstractDoctor terapist(VisitorCard visitorcard){
//        AbstractDoctor terapist = new Terapist();
//        terapist.setVisitorCard(visitorcard);
//        return terapist;
//    }
//
//    @Bean(name = "dentist")
//    public AbstractDoctor dentist(VisitorCard visitorcard){
//        AbstractDoctor dentist = new Dentist();
//        dentist.setVisitorCard(visitorcard);
//        return dentist;
//    }
//
//    @Bean(name = "ambulance")
//    public AbstractDoctor ambulance(VisitorCard visitorcard){
//        AbstractDoctor ambulance = new Ambulance();
//        ambulance.setVisitorCard(visitorcard);
//        return ambulance;
//    }
}
