import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import structures.DequeImpl;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DequeTest {

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

    DequeImpl deque;

    public DequeTest(String type, Object[] data) {
        this.type = type;
        this.data = data;
    }

    @Before
    public void init(){

        if (type.equals("Integer")){
            deque = new DequeImpl<Integer>();
        }else if (type.equals("String")){
            deque = new DequeImpl<String>();
        }else if (type.equals("Character")){
            deque = new DequeImpl<Character>();
        }

        for (Object o :
                data) {
            deque.insertFirst(o);
        }
    }

//    empty test - full test
    @Test
    public void test1(){
        Assert.assertEquals(false, deque.isEmpty());
        Assert.assertEquals(false, deque.isFull());
        for (Object o :
                data) {
            deque.insertFirst(o);
        }
        Assert.assertEquals(true, deque.isFull());
        deque.clear();
        Assert.assertEquals(true, deque.isEmpty());
    }

//    Order test
    @Test
    public void test2(){
        for (int i = 0; i < data.length / 2; i++) {
            Assert.assertEquals(data[i],deque.removeLast());
            Assert.assertEquals(data[data.length - 1 - i],deque.removeFirst());
        }
    }

//    Reverse order test
    @Test
    public void test3(){
        deque.clear();
        for (Object o :
                data) {
            deque.insertLast(o);
        }

        for (int i = 0; i < data.length; i++) {
            Assert.assertEquals(data[i],deque.removeFirst());
        }
    }

//    Peek test
    @Test
    public void test4(){
        Assert.assertEquals(data[0], deque.peekLast());
        Assert.assertEquals(data[data.length - 1], deque.peekFirst());
    }
}
