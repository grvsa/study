package ru.geekbrains.java3.lesson7;

import ru.geekbrains.java3.lesson7.annotations.AfterSuite;
import ru.geekbrains.java3.lesson7.annotations.BeforeSuite;
import ru.geekbrains.java3.lesson7.annotations.Test;
import ru.geekbrains.java3.lesson7.testclasses.TestClass;
import ru.geekbrains.java3.lesson7.testclasses.TestClass2;
import ru.geekbrains.java3.lesson7.testclasses.TestClass3;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.PriorityQueue;
import java.util.logging.*;

/*

Создать класс, который может выполнять «тесты».
В качестве тестов выступают классы с наборами методов, снабженных аннотациями @Test.
Для этого у них должен быть статический метод start(),
которому в качестве параметра передается объект типа Class или имя класса.
Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если он присутствует.
Далее запускаются методы с аннотациями @Test,
а по завершении всех тестов – метод с аннотацией @AfterSuite.
К каждому тесту необходимо добавить приоритеты (int-числа от 1 до 10),
в соответствии с которыми будет выбираться порядок их выполнения.
Если приоритет одинаковый, то порядок не имеет значения.
Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре.
Если это не так – необходимо бросить RuntimeException при запуске «тестирования».
P.S. Это практическое задание – проект, который пишется «с нуля».
Данная задача не связана напрямую с темой тестирования через JUnit)

*/
public class Main {
    private static Class testClass = null;
    private static PriorityQueue<MetodeWrapper> quene = new PriorityQueue<>();
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    static{
        try {
            FileHandler fileHandler = new FileHandler("log.txt",true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Logger init problem",e);
        }
    }
    public static void main(String[] args) {
        // write your code here


        start(TestClass.class.getName());

        start(TestClass2.class);

        //start(TestClass3.class);
    }

    public static void start(Class testCase) {
        testClass = testCase;
        try {
            Object instance = updateQuene(testClass);
            excecute(instance);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getMessage(),e);
        }
    }

    public static void start(String name) {
        try {
            testClass = Class.forName(name);
            Object instance = updateQuene(testClass);
            excecute(instance);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getMessage(),e);
        }
    }

    private static void excecute(Object instance) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        while (!quene.isEmpty()) {
            Method method = quene.poll().getContent();
            Parameter[] parameters = method.getParameters();
            Object[] parametersToMethod = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parametersToMethod[i] = Class.forName(parameters[i].getType().getName()).newInstance();
            }
            if (parameters.length != 0) {
                method.setAccessible(true);
                method.invoke(instance, parametersToMethod);
            } else {
                method.setAccessible(true);
                method.invoke(instance);
            }
        }
    }

    private static Object updateQuene(Class testClass) throws IllegalAccessException, InstantiationException {
        Object instance = testClass.newInstance();
        boolean isBeforeSuite = false;
        boolean isAfterSuite = false;

        Method[] methods = testClass.getDeclaredMethods();
        for (Method methode :
                methods) {
            if (methode.isAnnotationPresent(Test.class)) {
                quene.add(new MetodeWrapper(methode, methode.getAnnotation(Test.class).priority()));
            } else if (methode.isAnnotationPresent(BeforeSuite.class)) {
                if (!isBeforeSuite) {
                    quene.add(new MetodeWrapper(methode, 0));
                    isBeforeSuite = true;
                } else {
                    throw new RuntimeException("BeforeSuite annotation is more than 1");
                }
            } else if (methode.isAnnotationPresent(AfterSuite.class)) {
                if (!isAfterSuite) {
                    quene.add(new MetodeWrapper(methode, 11));
                    isAfterSuite = true;
                } else {
                    throw new RuntimeException("AfterSuite annotation is more than 1");
                }
            }
        }
        if (!(isAfterSuite & isBeforeSuite)) {
            instance = null;
            quene.clear();
            throw new RuntimeException((!isAfterSuite ? "AfterSuite annotation absent " : " ") + (!isBeforeSuite ? "BeforeSuite annotation absent " : " "));
        }
        return instance;
    }
}

