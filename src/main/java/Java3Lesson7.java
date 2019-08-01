import annotation.AfterSuite;
import annotation.BeforeSuite;
import annotation.Test;
import task1classes.*;


import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Java3Lesson7 {

    public static final String SOURCE = "./src/test/source/";
    public static final String TEST = "./src/test/fortest/";

    public static void main(String[] args) {
//        Task 1 - annotations

        try{
            start(Test1_1.class);
        }catch (RuntimeException e){
            System.out.println("RuntimeException task1(Test1_1.class)" + e.getMessage());
        }
        try{
            start(Test1_2.class);
        }catch (RuntimeException e){
            System.out.println("RuntimeException task1(Test1_2.class)" + e.getMessage());
        }
        try{
            start(Test1_3.class);
        }catch (RuntimeException e){
            System.out.println("RuntimeException task1(Test1_3.class)" + e.getMessage());
        }
        try{
            start(Test1_4.class);
        }catch (RuntimeException e){
            System.out.println("RuntimeException task1(Test1_4.class)" + e.getMessage());
        }
        try{
            start(Test1_5.class);
        }catch (RuntimeException e){
            System.out.println("RuntimeException task1(Test1_5.class)" + e.getMessage());
        }
        try{
            start(Test1_6.class);
        }catch (RuntimeException e){
            System.out.println("RuntimeException task1(Test1_6.class)" + e.getMessage());
        }

        try{
            start("task1classes.Test1_1");
        }catch (RuntimeException e){
            System.out.println("RuntimeException task1(Test1_6.class)" + e.getMessage());
        }

/*      Task 2 - class compare

        source folder - correct classes
        Source1 - not changed class - source1.txt
        Source2 - not changed class - source1.txt

        fortest folder - class to be tested with correct ones
        Source1 - not changed class (same as Source1 in source folder) - source1.txt
        Source2 - changed class - source2.txt
*/

        System.out.println();
        startTask2();
    }

    public static void start(String name){
        try {
            Class testClass = Class.forName(name);
            start(testClass);
        } catch (ClassNotFoundException e) {
            System.out.println("CLass not found !");
        }
    }

    public static void start(Class testclass){
        Set<AdapterMethod> setMethods = new TreeSet<AdapterMethod>();
        try {
            Method[] methods = testclass.getDeclaredMethods();
            int beforeCount = 0;
            int afterCount = 0;
            for (Method method :
                    methods) {
                boolean test = false;
                boolean before = false;
                boolean after = false;
                int priority = Integer.MIN_VALUE;
                Annotation[] annotations = method.getDeclaredAnnotations();

                for (Annotation annotation :
                        annotations) {
                    if (annotation.annotationType().equals(Test.class)){
                        priority = ((Test) annotation).priority();
                        test = true;
                    }else if(annotation.annotationType().equals(BeforeSuite.class)){
                        before = true;
                    }else if(annotation.annotationType().equals(AfterSuite.class)){
                        after = true;
                    }
                }

                if (test){
                    setMethods.add(new AdapterMethod(method,priority));
                }else if (before){
                    setMethods.add(new AdapterMethod(method,0));
                    beforeCount++;
                }else if (after){
                    setMethods.add(new AdapterMethod(method,Integer.MAX_VALUE));
                    afterCount++;
                }
            }

            if (beforeCount != 1 || afterCount != 1) {
                throw new RuntimeException("BeforeSuite count: " + beforeCount + " AfterSuite count: " + afterCount);
            }

            for (AdapterMethod adapterMethod :
                    setMethods) {
                Object testObject = testclass.newInstance();
                adapterMethod.getMethod().setAccessible(true);
                adapterMethod.getMethod().invoke(testObject,new Object[]{});
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void startTask2(){

        File sourcePath = new File(SOURCE);
        File testPath = new File(TEST);

        ArrayList<String> sourceClasses = new ArrayList<String>();
        ArrayList<String> testClasses = new ArrayList<String>();

        for (File file :
                sourcePath.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".class")){
                sourceClasses.add(file.getName().substring(0,file.getName().length() - 6));
            }

        }

        for (File file :
                testPath.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".class") && sourceClasses.contains(file.getName().substring(0,file.getName().length() - 6))){
                testClasses.add(file.getName().substring(0,file.getName().length() - 6));
            }else{
                if (file.isFile()) {
                    System.out.println("Source class not found: " + file.getName());
                }
            }
        }

        for (String test :
                testClasses) {
            System.out.println("Start test for class: " + test);
            System.out.println("Result: " + (test(test) ? "VALID" : "INVALID"));
            System.out.println();
        }
    }

    private static boolean test(String className){
        File source = new File(SOURCE);
        File test = new File(TEST);

        Class sourceClass = null;
        Class testClass = null;

        try {
            URL sourceURL = source.toURI().toURL();
            URL testURL = test.toURI().toURL();

            URLClassLoader sourceCL = new URLClassLoader(new URL[]{sourceURL});
            URLClassLoader testCL = new URLClassLoader(new URL[]{testURL});

            sourceClass = sourceCL.loadClass(className);
            testClass = testCL.loadClass(className);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        boolean result = true;
        for (Method method :
                sourceClass.getDeclaredMethods()) {
            String sourceName = method.getName();
            Type sourceType = method.getReturnType();
            Class[] sourceParameters = method.getParameterTypes();
            if (sourceType != void.class) {
                for (Method testMethod :
                        testClass.getDeclaredMethods()) {
                    String testName = testMethod.getName();
                    Type testType = testMethod.getReturnType();
                    Class[] testParameters = testMethod.getParameterTypes();
                    if (sourceName.equals(testName) && sourceType.equals(testType) && Arrays.equals(sourceParameters,testParameters)){
                        System.out.printf("Testing: " + sourceType + " " + sourceName + " " + Arrays.toString(sourceParameters ) + "\t");
                        try {
                            Object o1 = sourceClass.newInstance();
                            Object o2 = testClass.newInstance();
                            Object[] testCase = generateParameters(testParameters);
                            System.out.printf("Parameters: " + Arrays.toString(testCase) + "\t");

                            method.setAccessible(true);
                            Object resultO1 = method.invoke(o1,testCase);
                            testMethod.setAccessible(true);
                            Object resultO2 = testMethod.invoke(o2,testCase);

                            if (resultO1.equals(resultO2)){
                                System.out.println("O1 = " + resultO1.toString() + " O2 = " + resultO2.toString() + " Result: Pass");
                            }else{
                                System.out.println("O1 = " + resultO1.toString() + " O2 = " + resultO2.toString() + " Result: Fail");
                                result = false;
                            }
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        return result;
    }

    private static Object[] generateParameters(Class[] parameters){
        Object[] result = new Object[parameters.length];
        Random random = new Random();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] == float.class){
                result[i] = random.nextFloat() * (Float.MAX_VALUE /2  - Float.MIN_VALUE / 2) + Float.MIN_VALUE / 2;
            }else if(parameters[i] == int.class){
                result[i] = random.nextInt(8000) - 4000;
            }
        }
        return result;
    }
}
