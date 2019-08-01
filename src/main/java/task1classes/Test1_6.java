package task1classes;

import annotation.AfterSuite;
import annotation.BeforeSuite;
import annotation.Test;

public class Test1_6 {

    @BeforeSuite
    public void test0(){
        System.out.println(this.getClass().getSimpleName() + " Test 0 BeforeSuite");
    }

    @BeforeSuite
    public void test7(){
        System.out.println(this.getClass().getSimpleName() + " Test 7 BeforeSuite");
    }

    @Test(priority = 1)
    public void test1(){
        System.out.println(this.getClass().getSimpleName() + " Test 1 Priority 1");
    }

    @Test(priority = 2)
    public void test2(){
        System.out.println(this.getClass().getSimpleName() + " Test 2 Priority 2");
    }

    @Test(priority = 3)
    public void test3(){
        System.out.println(this.getClass().getSimpleName() + " Test 3 Priority 3");
    }

    @Test(priority = 4)
    public void test4(){
        System.out.println(this.getClass().getSimpleName() + " Test 4 Priority 4");
    }

    @Test(priority = 2)
    public void test5(){
        System.out.println(this.getClass().getSimpleName() + " Test 5 Priority 2");
    }

    @AfterSuite
    public void test6(){
        System.out.println(this.getClass().getSimpleName() + " Test 6 AfterSuite");
    }
}
