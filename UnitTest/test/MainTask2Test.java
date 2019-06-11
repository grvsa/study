import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class MainTask2Test {
    private int[] input;
    private int[] output;

    public MainTask2Test(int[] input, int[] output) {
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters
    public static Collection test(){
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,3,4,5,6,7,8},new int[]{5,6,7,8}},
                {new int[]{1,2,3,4,5,6,7,4},new int[]{}},
                {new int[]{1,2,3,4,5,6,4,8},new int[]{8}},
        });
    }

    @Test
    public void test1Task2(){
        Assert.assertArrayEquals(Main.task2(input), output);
    }
    @Test(expected = RuntimeException.class)
    public void test2Task2(){
        Main.task2(output);
    }
}
