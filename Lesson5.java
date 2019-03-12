package Lesson_5;

import java.util.Arrays;

public class Lesson5 {
    public static void main(String[] args) {
    int[] aTrees = {5,1,5,2,2,4,1,4,5,1,5,3,2,4,4,4,5,1,3,4,2,2,
                    1,2,4,4,4,5,4,3,5,4,4,5,5,1,4,1,5,3,1,4,5,3,
                    3,4,2,2,4,4,5,5,1,1,1,4,5,5,4,4,2,4,3,1,3,3,
                    1,1,3,1,3,4,4,3,2,2,1,3,4,4,2,3,4,2,4,4,1,4,
                    4,4,2,1,2,4,1,5,2,2,5,4,2,2,3,1,5,5,3,5,3,1,
                    4,5,4,2,1,3,1,2,1,4,1,3,4,2,2,5,2,3,1,1,2,3,
                    3,4,4,2,4,1,2,2,2,5,1,5,1,2,2,1,3,3,4,3,5,3,
                    5,1,2,1,3,3,2,4,1,4,3,5,1,2,1,2,3,2,1,3,2,2,
                    4,3,2,1,5,1,4,5,4,4,5,5,4,2,3,5,1,3,4,3,2,4,
                    5,2,5,2,4,1,4,5,2,3,3,4,4,3,5,2,2,3,5,1,2,4,
                    3,4,4,3,2,2,1,4,5,5,1,5,2,4,5,5,4,2,2,1,5,1,
                    3,4,2,4,2,2,4,3,5,2,2,4,4,4,5,5,2,5,5,2,5,1,
                    1,5,5,4,1,2,4,1,2,2,5,4,5,1,5,4,4,5,5,5,3,3,
                    4,3,3,5,3,2,2,2,2,2,1,2,5,2,3,4,3,5,5,2,4,5,
                    3,4,3,1,3,2,1,1,5,4,4,2,3,1,3,4,2,4,1,3,5,1,
                    5,3,5,2,3,4,4,1,3,1,5,5,1,2,2,1,3,1,5,1,2,2,
                    1,5,1,3,3,2,1,3,2,5,1,1,2,3,5,5,4,3,1,3,3,1,
                    5,4,2,3,4};

        System.out.println(Arrays.toString(counters(aTrees)));

        Person[] employees = {new Person("Alex","Plumer","pl@gmail.com","1234",30000,45),
                new Person("Vitya","Courier","carry@gmail.com","1235",15000,25),
                new Person("Sofia","ReceptionGirl","res@gmail.com","1111",20000,22),
                new Person("Lidiya Petrovna","Chief accountant","fi@gmail.com","1999",60000,55),
                new Person("Petr Petrovich","Owner","bb@gmail.com","7777",300000,60)
        };
        for (Person p: employees
             ) {
            if (p.getAge() > 40){
                p.printInfo();
            }
        }
    }


    public static int[] counters(int[] array){
        int[] result = new int[20];
        for (int i: array) {
            result[i - 1]++;
        }
        return result;
    }
}

class Person{
    private String fIO;
    private String job;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Person(String fIO, String job, String email, String phone, int salary, int age) {
        this.fIO = fIO;
        this.job = job;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo(){
        System.out.println(this.toString());
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fIO='" + fIO + '\'' +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
