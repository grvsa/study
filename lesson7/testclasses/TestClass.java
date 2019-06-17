package ru.geekbrains.java3.lesson7.testclasses;

import ru.geekbrains.java3.lesson7.annotations.AfterSuite;
import ru.geekbrains.java3.lesson7.annotations.BeforeSuite;
import ru.geekbrains.java3.lesson7.annotations.Test;

public class TestClass {
    @Test(priority = 1)
    public void test1(String a){
        System.out.println("test1 priority 1");
    }
    @Test(priority = 5)
    public void test5(){
        System.out.println("test5 priority 5");
    }

    @Test(priority = 9)
    private void test9(){
        System.out.println("test9 priority 9");
    }

    @Test(priority = 3)
    public void test3(){
        System.out.println("test3 priority 3");
    }

    @Test(priority = 8)
    protected void test8(){
        System.out.println("test8 priority 8");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite method");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite method");
    }
}
