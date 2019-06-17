package ru.geekbrains.java3.lesson7.testclasses;

import ru.geekbrains.java3.lesson7.annotations.AfterSuite;
import ru.geekbrains.java3.lesson7.annotations.BeforeSuite;
import ru.geekbrains.java3.lesson7.annotations.Test;

public class TestClass2 {
    @Test(priority = 1)
    public void test1(String a){
        System.out.println("test1 priority 1" + a.length());
    }
    @Test(priority = 2)
    public void test2(){
        System.out.println("test2 priority 2");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite method");
    }

    @BeforeSuite
    public void beforeSuite2(){
        System.out.println("BeforeSuite method2");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite method");
    }
}
