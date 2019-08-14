import array.SortedArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Test1 {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"Integer",1,1,8,9,new Integer[]{0,1,2,3,4,5,6,7}},
                {"Character",'d',3, 6,'F', new Character[]{'a','b','c','d','e','f'}},
                {"String","aa",1, 6,"bb", new String[]{"a","aa","aaa","aaaa","aaab","aaabc"}}
        });
    }

    private String type;
    private Object check;
    private Object missing;
    private int size;
    private int index;
    private Object[] data;

    SortedArray array;

    public Test1(String type, Object check, int index, int size, Object missing, Object[] data) {
        this.type = type;
        this.check = check;
        this.index = index;
        this.size = size;
        this.data = data;
        this.missing = missing;
    }

    @Before
    public void init(){
        if (type.equals("Integer")){
            array = new SortedArray<Integer>();
        }else if (type.equals("String")){
            array = new SortedArray<String>();
        }else if (type.equals("Character")){
            array = new SortedArray<Character>();
        }
        array.addAll(data);
    }

//    check size
    @Test
    public void test1(){
        Assert.assertEquals(size,array.size());
    }

//    check item
    @Test
    public void test2(){
        Assert.assertEquals(index,array.indexOf(check));
    }

//    remove
    @Test
    public void test3(){
        Assert.assertEquals(check,array.remove(check));
        Assert.assertEquals(size - 1,array.size());
    }

    @Test
    public void test4(){
        Assert.assertEquals(check,array.remove(index));
        Assert.assertEquals(size - 1,array.size());
    }

//    Add
    @Test
    public void test5(){
        array.add(check);
        Assert.assertEquals(size + 1, array.size());
        Assert.assertEquals(check,array.get(size));
    }

//    Contains
    @Test
    public void test6(){
        Assert.assertEquals(true,array.contains(check));
        Assert.assertEquals(false,array.contains(missing));
    }

//    Exceptions
    @Test(expected = RuntimeException.class)
    public void test7(){
        Assert.assertEquals(-1,array.get(-1));
    }

    @Test(expected = RuntimeException.class)
    public void test8(){
        Assert.assertEquals(-1,array.get(size));
    }

    @Test(expected = RuntimeException.class)
    public void test9(){
        Assert.assertEquals(-1,array.remove(missing));
    }
}
