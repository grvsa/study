import java.util.Arrays;

public class ArrayTask {
    public static void main(String[] args) {
        int[][] array = new int[7][5];

        fill(array,0,0, 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));;
        }

    }

    public static void fill(int[][] array,int modX,int modY , int step){
        int c1 = (array.length - modY * 2) * (array[0].length - modX * 2);
        int c2 = ((array.length - modY * 2 - 2 <= 0) || (array[0].length - modX * 2 - 2) <= 0 ? 0 : (array.length - modY * 2 - 2) * (array[0].length - modX * 2 - 2));

        int count = c1 - c2;
        int dx = 1;
        int dy = 0;
        int x = 0 + modX;
        int y = 0 + modY;
        while (count --  > 0){
            array[y][x] = step++;
            if (x + dx == array[0].length - modX || y + dy == array.length - modY || x + dx < modX || y + dy < modY){
                if (dx == 1 && dy == 0){
                    dx = 0;
                    dy = 1;
                }else if (dx == 0 && dy == 1){
                    dx = -1;
                    dy = 0;
                }else if (dx == -1 && dy == 0){
                    dx = 0;
                    dy = -1;
                }else{
                    dx = 1;
                    dy = 0;
                }
            }
            x += dx;
            y += dy;
        }

        if (c2 != 0){
            fill(array,++modX,++modY, step);
        }
    }

}
