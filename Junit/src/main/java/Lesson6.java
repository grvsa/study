import java.util.Arrays;

public class Lesson6 {
    public static void main(String[] args) {
        int [] test1 = task1(1,2,3,10,5,6,7,8,9);
        System.out.println(Arrays.toString(test1));

        System.out.println(task2(1,2,3,1,5,6,7,8,9,0));
    }

    public static int[] task1(int...array){
        for (int i = array.length - 1; i >= 0 ; i--) {
            if (array[i] == 4){
                return Arrays.copyOfRange(array,i + 1,array.length);
            }
        }
        throw new RuntimeException("No 4");
    }

    public static boolean task2(int...array){
        boolean has4 = false;
        boolean has1 = false;
        for (int i = 0; i < array.length; i++) {
            if (has4 || array[i] == 4) has4 = true;
            if (has1 || array[i] == 1) has1 = true;
            if (has1 && has4) return true;
        }
        return false;
    }
}
