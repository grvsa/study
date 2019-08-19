import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class LinkedListTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"Integer",8, new Integer[]{0,1,2,3,4,5,6,7}},
                {"Character",'A', new Character[]{'a','b','c','d','e','f'}},
                {"String","FF", new String[]{"a","aa","aaa","aaaa","aaab","aaabc"}}
        });
    }

    private String type;
    private Object[] data;
    private Object missing;

    LinkedListImpl list;
    ListIterator iterator;
    Random random;

    public LinkedListTest(String type, Object missing, Object[] data) {
        this.type = type;
        this.data = data;
        this.missing = missing;
        random = new Random();
    }

    @Before
    public void init(){

        if (type.equals("Integer")){
            list = new LinkedListImpl<Integer>();
        }else if (type.equals("String")){
            list = new LinkedListImpl<String>();
        }else if (type.equals("Character")){
            list = new LinkedListImpl<Character>();
        }

    }

//    empty - test
    @Test
    public void test1(){
        Assert.assertEquals(true,list.isEmpty());
        Assert.assertEquals(false,list.isFull());
        list.insertFirst(data[0]);
        Assert.assertEquals(false,list.isEmpty());
        list.clear();
        Assert.assertEquals(true,list.isEmpty());
    }
//    Order - test
    @Test
    public void test2(){
        for (Object o :
                data) {
            list.insertLast(o);
        }

        Assert.assertArrayEquals(data,list.toArray());
        list.clear();
        for (Object o :
                data) {
            list.insertFirst(o);
        }
        Collections.reverse(Arrays.asList(data));
        Assert.assertArrayEquals(data,list.toArray());
    }

//    ForEach - test
    @Test
    public void test3(){
        int count = 0;
        for (Object o :
                list) {
            Assert.assertEquals(data[count++], o);
        }
    }

//    Iterator - test
    @Test
    public void test4(){
        for (Object o :
                data) {
            list.insertLast(o);
        }
        iterator = (ListIterator) list.iterator();
        for (Object o :
                data) {
            Object result = null;
            if (iterator.hasNext()) result = iterator.next();
            Assert.assertEquals(o,result);
        }
    }
    @Test (expected = NoSuchElementException.class)
    public void test5(){
        for (Object o :
                data) {
            list.insertFirst(o);
        }
        iterator = (ListIterator) list.iterator();
        while (iterator.hasNext()) iterator.next();
        Assert.assertEquals(data[0],iterator.next());
    }

    @Test (expected = NoSuchElementException.class)
    public void test6(){
        for (Object o :
                data) {
            list.insertFirst(o);
        }
        iterator = (ListIterator) list.iterator();
        while (iterator.hasNext()) iterator.next();
        while (iterator.hasPrevious()) iterator.previous();
        Assert.assertEquals(data[0],iterator.previous());
    }

    @Test (expected = ConcurrentModificationException.class)
    public void test7(){
        for (Object o :
                data) {
            list.insertFirst(o);
        }
        iterator = (ListIterator) list.iterator();
        if (iterator.hasNext()) iterator.next();
        list.insertLast(data[0]);
        Assert.assertEquals(data[0],iterator.previous());
    }

//    Peek - test
    @Test
    public void test8(){
        for (Object o :
                data) {
            list.insertFirst(o);
        }
        Assert.assertEquals(data[data.length - 1],list.peekFirst());
        Assert.assertEquals(data[0],list.peekLast());
    }

//    contains - test
    @Test
    public void test9(){
        int value = random.nextInt(data.length);
        for (Object o :
                data) {
            list.insertFirst(o);
        }
        Assert.assertEquals(true,list.contains(data[value]));
        Assert.assertEquals(false,list.contains(missing));
    }

//    remove - test
    @Test
    public void test10(){

        List array2 = new ArrayList();
        for (Object o :
                data) {
            list.insertLast(o);
            array2.add(o);
        }
        array2.remove(0);
        list.removeFirst();
        Assert.assertArrayEquals(array2.toArray(),list.toArray());
        array2.remove(array2.size() - 1);
        list.removeLast();
        Assert.assertArrayEquals(array2.toArray(),list.toArray());
        int value = random.nextInt(array2.size());
        list.remove(array2.get(value));
        array2.remove(value);
        Assert.assertArrayEquals(array2.toArray(),list.toArray());
        Assert.assertEquals(array2.size(),list.size());
    }
}
