package ru.geekbrains.java1.lesson2;

public class Main {

    public static void main(String[] args) {
//        TASK 1
        int[] array1 = {1,0,0,1,1,1,1,1,0,0,0,1,0,1,1,0};
        printArray(array1);
        task1(array1);
        printArray(array1);
//        TASK 2
        int[] array2 = new int[8];
        printArray(array2);
        task2(array2);
        printArray(array2);
//        TASK 3
        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(array3);
        task3(array3);
        printArray(array3);
//        TASK 4
        int[][] array4 = new int[5][5];
        for (int i = 0; i < array4.length; i++)
            printArray(array4[i]);
        task4(array4);
        for (int i = 0; i < array4.length; i++)
            printArray(array4[i]);
//        TASK 5
        printArray(array3);
        System.out.println("Min: " + task5Min(array3) + " Max: " + task5Max(array3));
//        TASK 6
        int[] array5 = {2, 2};
        System.out.println(task6(array5));
//        TASK 7
        int[] array6 = {1, 2, 3, 4, 5, 6};
        printArray(array6);
        task7(array6, -7);
        printArray(array6);
    }
    public static void printArray(int[] array){
        System.out.print("{ ");
        for(int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println("}");
    }

    public static void task1(int[] array){
//        1. Задать целочисленный массив, состоящий из элементов 0 и 1.
//        Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
//        С помощью цикла и условия заменить 0 на 1, 1 на 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] == 0){
                array[i] = 1;
            }else{
                array[i] = 0;
            }
        }
    }

    public static void task2(int[] array){
//        2. Задать пустой целочисленный массив размером 8.
//        С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        for(int i = 0; i < array.length; i++)
            array[i] = i*3;
    }

    public static void task3(int[] array){
//        3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
//        пройти по нему циклом, и числа меньшие 6 умножить на 2;
        for (int i = 0; i < array.length; i++)
            if (array[i] < 6) array[i] *= 2;
    }

    public static void task4(int [][] array){
//        4. Создать квадратный двумерный целочисленный массив (
//        количество строк и столбцов одинаковое), и с помощью цикла(-ов)
//        заполнить его диагональные элементы единицами;
        for (int i = 0; i < array.length; i++){
            array[i][i] = 1;
            array[array.length - 1 - i][i] = 1;
        }
    }

    public static int task5Min(int[] array){
//        5. ** Задать одномерный массив и
//        найти в нем минимальный и максимальный элементы (без помощи интернета);
        int result = array[0];
        for (int i = 0; i < array.length; i++)
            if (array[i] < result) result = array[i];
        return result;
    }

    public static int task5Max(int[] array){
//        5. ** Задать одномерный массив и
//        найти в нем минимальный и максимальный элементы (без помощи интернета);
        int result = array[0];
        for (int i = 0; i < array.length; i++)
            if (array[i] > result) result = array[i];
        return result;
    }

    public static boolean task6(int[] array){
//        6. ** Написать метод, в который передается не пустой
//        одномерный целочисленный массив, метод должен вернуть true,
//        если в массиве есть место, в котором сумма левой и правой части массива равны.
//        Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
//        checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
        if (array.length == 1)  return true;
        int left = 0;
        int right = 0;
        int sumLeft = array[0];
        int sumRight = array[array.length - 1];
        while ( left + right < array.length - 2){
            if( sumLeft >= sumRight){
                right++;
                sumRight += array[array.length - 1 - right];
            }else {
                left++;
                sumLeft += array[left];
            }
        }
        return sumLeft == sumRight ? true : false;
    }

    public static void task7(int[] array, int n){
//        7. **** Написать метод, которому на вход подается одномерный массив и число n
//        (может быть положительным, или отрицательным), при этом метод должен
//        сместить все элементымассива на n позиций.
//        Для усложнения задачи нельзя пользоваться вспомогательными массивами.
        if (n < 0) n = ( n % array.length) + array.length;
        int i = 0;
        int counter = 0;
        int temp = array[0];
        int start = 0;
        do{
            int temp1 = array[(counter + n) % array.length];
            array[(counter + n) % array.length] = temp;
            i++;

            counter = (counter + n) % array.length;
            if (counter == start){
                counter = (counter + 1) % array.length;
                start = counter;
                temp = array[counter];
            }else{
                temp = temp1;
            }
        }while (i % array.length !=0);
    }
}
