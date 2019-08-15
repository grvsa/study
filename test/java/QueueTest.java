import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import structures.QueueImpl;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QueueTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"Integer", new Integer[]{0,1,2,3,4,5,6,7}},
                {"Character", new Character[]{'a','b','c','d','e','f'}},
                {"String", new String[]{"a","aa","aaa","aaaa","aaab","aaabc"}}
        });
    }

    private String type;
    private Object[] data;

    QueueImpl queue;

    public QueueTest(String type, Object[] data) {
        this.type = type;
        this.data = data;
    }

    @Before
    public void init(){
        if (type.equals("Integer")){
            queue = new QueueImpl<Integer>();
        }else if (type.equals("String")){
            queue = new QueueImpl<String>();
        }else if (type.equals("Character")){
            queue = new QueueImpl<Character>();
        }

        for (Object o :
                data) {
            queue.insert(o);
        }
    }

//    empty test - full test
    @Test
    public void test1(){
        Assert.assertEquals(false, queue.isEmpty());
        Assert.assertEquals(false, queue.isFull());
        for (Object o :
                data) {
            queue.insert(o);
        }
        Assert.assertEquals(true, queue.isFull());
        queue.clear();
        Assert.assertEquals(true, queue.isEmpty());
    }

//    Order test
    @Test
    public void test2(){
        for (Object o :
                data) {
            Assert.assertEquals(o,queue.remove());
        }
    }

//    Peek test
    @Test
    public void test3(){
        Assert.assertEquals(data[0],queue.peekFront());
    }
}
