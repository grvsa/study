package ru.geekbrains.java2.lesson2;

import ru.geekbrains.java2.lesson2.exceptions.MyArrayDataException;
import ru.geekbrains.java2.lesson2.exceptions.MyArraySizeException;

import java.util.Arrays;

public class Matrix {
    private static final int SIZE = 4;
    private static final String WRONG_DATA = "Wrong data at matrix[%d][%d] = %s";
    private static final String WRONG_SIZE = "Wrong size of matrix";

    public static void main(String[] args) {
        // write your code here

        /*
        1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4,
        при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
        2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
        Если в каком-то элементе массива преобразование не удалось
        (например, в ячейке лежит символ или текст вместо числа),
        должно быть брошено исключение MyArrayDataException – с детализацией,
        в какой именно ячейке лежат неверные данные.
        3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и
        MyArrayDataException и вывести результат расчета.
        */

        String[][] matrix = new String[SIZE][SIZE];
        int checkSum = 0;
        for (int i = 0; i < SIZE * SIZE; i++) {
            int number = (int) (Math.random() * 10 + 1);
            matrix[i / SIZE][i % SIZE] = "" + number;
            checkSum += number;
        }
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(checkSum);


        try {
            System.out.println(calculateArray(matrix));
        }catch (MyArrayDataException e){
            System.out.println(e.getMessage());
        }catch (MyArraySizeException e){
            System.out.println(e.getMessage());
        }

    }

    public static int calculateArray(String[][] matrix) throws MyArrayDataException, MyArraySizeException {
        int result = 0;
        if (matrix != null && matrix.length == SIZE) {
            for (String[] s :
                    matrix) {
                if (s == null || s.length != SIZE) throw new MyArraySizeException(WRONG_SIZE);
            }
        } else {
            throw new MyArraySizeException(WRONG_SIZE);
        }
        for (int i = 0; i < SIZE * SIZE; i++) {
            try {
                result += Integer.parseInt(matrix[i / SIZE][i % SIZE]);
            } catch (Exception e) {
                throw new MyArrayDataException(String.format(WRONG_DATA, i / SIZE, i % SIZE,matrix[i / SIZE][i % SIZE]));
            }
        }
        return result;
    }
}
