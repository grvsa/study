import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Task2Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {false, new int[]{1,1,1,1,1,1,1}},
                {true, new int[]{1,2,3,4,5,6,7,8,9}},
                {true, new int[]{1,4}},
                {false, new int[]{}},
                {false, new int[]{4,4,4}}
        });
    }
    private boolean a;
    private int[] b;
    private Lesson6 lesson6;
    public Task2Test(boolean a, int[] b) {
        this.a = a;
        this.b = b;
    }

    @Before
    public void init(){
        lesson6 = new Lesson6();
    }

    @Test
    public void task2Test(){
        Assert.assertEquals(a,lesson6.task2(b));
    }
}
