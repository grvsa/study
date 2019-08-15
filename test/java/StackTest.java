import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import structures.StackImpl;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StackTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"Integer",7,8,new Integer[]{0,1,2,3,4,5,6,7}},
                {"Character",'f', 'e', new Character[]{'a','b','c','d','e','f'}},
                {"String","aaabc", "aaab", new String[]{"a","aa","aaa","aaaa","aaab","aaabc"}}
        });
    }

    private String type;
    private Object last;
    private Object prev;
    private Object[] data;

    StackImpl stack;

    public StackTest(String type, Object last, Object prev, Object[] data) {
        this.type = type;
        this.last = last;
        this.data = data;
        this.prev = prev;
    }

    @Before
    public void init(){
        if (type.equals("Integer")){
            stack = new StackImpl<Integer>();
        }else if (type.equals("String")){
            stack = new StackImpl<String>();
        }else if (type.equals("Character")){
            stack = new StackImpl<Character>();
        }

        for (Object o :
                data) {
            stack.push(o);
        }
    }

//    empty test - full test
    @Test
    public void test1(){
        Assert.assertEquals(false,stack.isEmpty());
        Assert.assertEquals(false,stack.isFull());
        for (Object o :
                data) {
            stack.push(o);
        }
        Assert.assertEquals(true,stack.isFull());
        stack.clear();
        Assert.assertEquals(true,stack.isEmpty());
    }

//    peek test
    @Test
    public void test2(){
        Assert.assertEquals(last,stack.peek());
    }

//    order test
    @Test
    public void test3(){
        for (int i = data.length - 1; i >= 0 ; i--) {
            Assert.assertEquals(data[i],stack.pop());
        }
    }
}
