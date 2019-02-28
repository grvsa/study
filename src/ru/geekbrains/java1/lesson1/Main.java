package ru.geekbrains.java1.lesson1;
// 1. Создать пустой проект в IntelliJ IDEA и прописать метод main();
public class Main {

    public static void main(String[] args) {
	// 2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte a = 10;
        int b = 20;
        long c = 30;
        float d = 11.1f;
        double e = 31.2;
        boolean g = false;
        char f = 'f';
        String s = "Hello world";

        System.out.println(calculate(1,1,1,1));
        System.out.println(checkSummary(5,16));
        printSign(-10);
        System.out.println(checkSign(-10));
        printGreetings("Alex");
        printYear(1600);

    }
    public static int calculate(int a,int b,int c,int d){
//        3. Написать метод вычисляющий выражение a * (b + (c / d)) и
//        возвращающий результат,где a, b, c, d – входные параметры этого метода;

        return a * (b + (int) (c / d));
    }
    public static boolean checkSummary(int a,int b){
//        4. Написать метод, принимающий на вход два числа, и
//        проверяющий что их сумма лежит в пределах от 10 до 20(включительно), если да – вернуть true,
//        в противном случае – false;

        return (a + b) >= 10 && (a + b) <= 20 ? true: false;
    }
    public static void printSign(int a){
//        5. Написать метод, которому в качестве параметра передается целое число,
//        метод должен напечатать в консоль положительное ли число передали, или отрицательное;
//        Замечание: ноль считаем положительным числом.

        System.out.println((a >= 0 ? "Положительное" : "Отрицательное"));
    }
    public static boolean checkSign(int a){
//        6. Написать метод, которому в качестве параметра передается целое число,
//        метод должен вернуть true, если число отрицательное;
        return a < 0 ? true : false;
    }
    public static void printGreetings(String s){
//        7. Написать метод, которому в качестве параметра передается строка,
//        обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;

        System.out.println("Привет, " + s + "!");
    }
    public static void printYear(int y){
//        8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
//        Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

        System.out.println(((y%400 == 0 || (y%100 != 0 && y%4 == 0)) ? "Високосный" : "Не високосный"));
    }
}
