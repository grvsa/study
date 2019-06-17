package ru.geekbrains.java3.lesson7.testclasses;

import ru.geekbrains.java3.lesson7.annotations.BeforeSuite;

public class TestClass3 {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite method");
    }
}
