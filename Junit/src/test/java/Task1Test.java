import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Task1Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{},new int[]{4,4,4,4,4,4,4,4,4}},
                {new int[]{2,1,5,2,3,6,1,7},new int[]{4,2,1,5,2,3,6,1,7}},
                {new int[]{1},new int[]{4,4,4,4,4,4,4,4,1}},
                {new int[]{1,7},new int[]{1,2,4,4,2,3,4,1,7}},
//                {new RuntimeException("No 4"),new Object[]{}},
//                {new RuntimeException("No 4"),new Object[]{1,2,2,3,7}},
                {new int[]{2,3,1,7},new int[]{1,2,4,4,2,3,1,7}},
                {new int[]{},new int[]{1,2,4,4,2,3,4,1,4}}

        });
    }
    private int[] a;
    private int[] b;
    Lesson6 lesson6;

    public Task1Test(int[] a, int[] b){
        this.a = a;
        this.b = b;
    }

    @Before
    public void init(){
        lesson6 = new Lesson6();
    }

    @Test
    public void task1Test1(){
        Assert.assertArrayEquals(a,lesson6.task1(b));
    }

    @Test (expected = RuntimeException.class)
    public void task1Test2(){
        Assert.assertArrayEquals(new int[] {},lesson6.task1(new int[]{}));
        Assert.assertArrayEquals(new int[] {},lesson6.task1(new int[]{1,2,2,3,7}));
    }
}
