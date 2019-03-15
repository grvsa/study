package ru.geekbrains.java1.lesson6hw;

public class Lesson6hw {

    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat("Vasya",1000,10);
        Cat cat4 = new Cat();

        cat1.swim(100);
        cat2.swim(100);
        cat3.swim(200);
        cat4.swim(300);
        cat3.run(0f);
        cat3.jump(11);
        task2("а Предложение  один     !Теперь предложение    дваПредложение   три ");
    }
    public static void task2(String s){
        StringBuilder s2 = new StringBuilder(s.replaceAll(" +", " ").trim());
        boolean change = false;
        for (int i = s2.length() - 1; i >= 0; i--){
            if(change){
                if (s2.charAt(i) >= 'а' && s2.charAt(i) <= 'я' && s2.charAt(i + 1) != ' '){
                    s2.insert(i + 1,' ');
                    s2.insert(i + 1,'.');
                    change = false;
                }else if(s2.charAt(i) == '!' || s2.charAt(i) == '?' || s2.charAt(i) == '.'){
                    if (s2.charAt(i + 1) != ' '){
                        s2.insert(i + 1, ' ');
                    }
                    change = false;
                }else if (s2.charAt(i) != ' '){
                    s2.insert(i + 1,'.');
                    change = false;
                }
            }else if (s2.charAt(i) >= 'А' && s2.charAt(i) <= 'Я'){
                change = true;
            }
        }
        if (s2.charAt(s2.length() - 1) != '.' && s2.charAt(s2.length() - 1) != '!' && s2.charAt(s2.length() - 1) != '?'){
            s2.append('.');
        }
        System.out.println(s2.toString());
    }
}

interface IRun{
    public void run(float distance);
}
interface IJump{
    public void jump(float distance);
}
interface ISwim{
    public void swim(float distance);
}
abstract class Animal implements IRun,IJump,ISwim{
    private String name;
    private float runLimit;
    private float jumpLimit;
    private float swimLimit;

    public Animal(String name, float runLimit, float jumpLimit, float swimLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
        this.swimLimit = swimLimit;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run(float distance) {
        if (distance <= 0){
            System.out.println("Некорректное значение");
        }else {
            System.out.println(distance > runLimit ? name + " не бежит - далеко" : name + " побежал");
        }
    }

    @Override
    public void jump(float distance) {
        if (distance <= 0){
            System.out.println("Некорректное значение");
        }
        System.out.println(distance > jumpLimit ? name + " не прыгает - высоко" : name + " прыгнул");
    }

    @Override
    public void swim(float distance) {
        if (distance <= 0){
            System.out.println("Некорректное значение");
        }
        System.out.println(distance > swimLimit ? name + " не плывет - далеко" : name + " плывет");
    }
}

class Cat extends Animal{
    private static int number = 1;
    public Cat() {
        super("Some Cat #" + number, 200,2,0);
        number++;
    }

    public Cat(String name, float runLimit, float jumpLimit) {
        super(name, runLimit, jumpLimit, 0);
    }

    @Override
    public void swim(float distance) {
        System.out.println(this.getName() + " не умеет плавать");
    }
}

class Dog extends Animal{
    private static int number = 1;
    public Dog(String name, float runLimit, float jumpLimit, float swimLimit) {
        super(name, runLimit, jumpLimit, swimLimit);
    }
    public Dog(){
        super("Some dog #" + number,500,0.5f,10);
        number++;
    }
}

