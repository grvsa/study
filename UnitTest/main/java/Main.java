import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // write your code here
        /*
            1. Добавить на серверную сторону чата логирование, с выводом информации о действиях на сервере
            (запущен, произошла ошибка, клиент подключился, клиент прислал сообщение/команду).

            2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
            Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
            идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку,
            иначе в методе необходимо выбросить RuntimeException.
            Написать набор тестов для этого метода (по 3-4 варианта входных данных).
            Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

            3. Написать метод, который проверяет состав массива из чисел 1 и 4.
            Если в нем нет хоть одной четверки или единицы, то метод вернет false;
            Написать набор тестов для этого метода (по 3-4 варианта входных данных).

            4. *Добавить на серверную сторону сетевого чата логирование событий.
        */

        System.out.println(Arrays.toString(task2(1,2,3,4,5,6,7,8)));
        System.out.println(Arrays.toString(task2(1,2,3,4,5,6,7,4)));
        System.out.println(Arrays.toString(task2(1,2,3,4,5,4,7,9)));
//        System.out.println(Arrays.toString(task2(1,2,3,0,5,6,7,9)));

        System.out.println(task3(1,2,3,4,5));
        System.out.println(task3(2,3,4,5));
        System.out.println(task3(1,2,3,5));
        System.out.println(task3(2,3,5));
        System.out.println(task3());

    }

    public static int[] task2(int...array){
        int index = -1;
        for (int i = array.length - 1; i >= 0 ; i--) {
            if (array[i] == 4) {
                index = i;
                break;
            }
        }
        if (index == -1) throw new RuntimeException("В массиве нет числа 4");
        return index == array.length - 1 ? new int[]{} : Arrays.copyOfRange(array,index + 1,array.length);
    }

    public static boolean task3(int...array){
        boolean containsOne = false;
        boolean containsFour = false;
        for (Integer i :
                array) {
            if (i == 1) containsOne = true;
            if (i == 4) containsFour = true;
            if (containsOne & containsFour) return true;
        }
        return false;
    }
}
