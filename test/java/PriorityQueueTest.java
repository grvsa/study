import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import structures.PriorityQueueImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class PriorityQueueTest {

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

    PriorityQueueImpl priorityQueue;

    public PriorityQueueTest(String type, Object[] data) {
        this.type = type;

       this.data = data;
    }

    @Before
    public void init(){
        if (type.equals("Integer")){
            priorityQueue = new PriorityQueueImpl<Integer>();
        }else if (type.equals("String")){
            priorityQueue = new PriorityQueueImpl<String>();
        }else if (type.equals("Character")){
            priorityQueue = new PriorityQueueImpl<Character>();
        }
        for (int i = data.length - 1; i >= 0 ; i--) {
            priorityQueue.insert(data[i]);
        }
    }

//    empty test - full test
    @Test
    public void test1(){
        Assert.assertEquals(false, priorityQueue.isEmpty());
        Assert.assertEquals(false, priorityQueue.isFull());
        for (Object o :
                data) {
            priorityQueue.insert(o);
        }
        Assert.assertEquals(true, priorityQueue.isFull());
        priorityQueue.clear();
        Assert.assertEquals(true, priorityQueue.isEmpty());
    }

//    Order test
    @Test
    public void test2(){
        for (Object o :
                data) {
            Assert.assertEquals(o, priorityQueue.remove());
        }
    }

//    Peek test
    @Test
    public void test3(){
        Assert.assertEquals(data[0], priorityQueue.peekFront());
    }
}
