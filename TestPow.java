import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class TestPow {
    Random random;

    public TestPow() {
        random = new Random();
    }

    @Test
    public void test1(){
        int value1 = random.nextInt(9);
        int value2 = random.nextInt(9);
        Assert.assertEquals((int) Math.pow(value1,value2), Main.pow(value1,value2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void test2(){
        Assert.assertEquals(-1,Main.pow(1,-1));
    }
}
