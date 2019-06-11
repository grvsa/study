import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class MainTask3Test {
    private int[] input;
    private boolean result;

    public MainTask3Test(int[] input, boolean result) {
        this.input = input;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection testData(){
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,3,4,5,6,7,8},true},
                {new int[]{2,3,4,5,6,7,8},false},
                {new int[]{1,2,3,5,6,7,8},false},
                {new int[]{2,3,5,6,7,8},false},
                {new int[]{},false}
        });
    }

    @Test
    public void testTask3(){
        Assert.assertEquals(Main.task3(input),result);
    }
}
