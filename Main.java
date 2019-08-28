import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {


        TreeSetImpl<Integer> treeSet = new TreeSetImpl<Integer>();

//        treeSet.add(10);
//        treeSet.add(5);
//        treeSet.add(4);
//        treeSet.add(3);
//        treeSet.add(6);
//        treeSet.add(12);
//        treeSet.add(11);
//
//        System.out.println(treeSet.contains(20));
//        System.out.println(treeSet.min());
//        System.out.println(treeSet.max());
//
//        TreeSetImpl<Integer>.Node<Integer> node = treeSet.find(10);
//        System.out.println(node.min());
//        System.out.println(node.max());
//        System.out.println(node.maxDepth());
//        System.out.println(node.minDepth());
//        System.out.println(node.depth());
//        treeSet.print();
//        System.out.println(treeSet.remove(10));
//        treeSet.print();
//        System.out.println(treeSet.remove(6));
//        treeSet.print();
//
//        System.out.println(treeSet.remove(12));
//        treeSet.print();
//        System.out.println(treeSet.remove(5));
//        treeSet.print();
//        System.out.println(treeSet.size());
//        System.out.println("----------------------------------------");
//
//        treeSet.clear();
//        treeSet.print();
//        treeSet.add(10);
//        treeSet.add(11);
//        treeSet.add(12);
//        treeSet.add(13);
//        treeSet.add(14);
//        treeSet.add(15);
//        treeSet.print();
//        System.out.println("----------------------------------------");
//        System.out.println(treeSet.size());
//        treeSet.remove(15);
//        treeSet.print();
//        System.out.println(treeSet.size());
//
//        System.out.println("----------------------------------------");
//        System.out.println(treeSet.size());
//        treeSet.remove(12);
//        treeSet.print();
//        System.out.println(treeSet.size());
//        System.out.println("----------------------------------------");
//        System.out.println(treeSet.size());
//        treeSet.remove(10);
//        treeSet.print();
//        System.out.println(treeSet.size());
//        treeSet.find(11).fullInfo();
//        treeSet.find(14).fullInfo();
//        treeSet.find(13).fullInfo();
//
//        treeSet.clear();
//        treeSet.add(50);
//        treeSet.add(60);
//        treeSet.add(70);
//        treeSet.add(55);
//        treeSet.add(40);
//        treeSet.add(30);
//        treeSet.add(20);
//        treeSet.add(45);
//        treeSet.add(44);
//        treeSet.add(42);
//        System.out.println("----------------------------------------");
//
//        System.out.println(treeSet.size());
//        treeSet.print();
//        System.out.println("----------------------------------------");
//        treeSet.remove(50);
//        treeSet.print();
//        System.out.println("Size: " + treeSet.size());
//        System.out.println("Balance: " + treeSet.isBalance());
//        treeSet.add(41);
//        treeSet.print();
//        System.out.println("Balance: " + treeSet.isBalance());
//        treeSet.clear();
//        System.out.println("----------------------------------------");
//        treeSet.add(10);
//        System.out.println("Balance: " + treeSet.isBalance());
//        treeSet.add(5);
//        System.out.println("Balance: " + treeSet.isBalance());
//        treeSet.add(3);
//        treeSet.add(6);
//        treeSet.add(15);
//        treeSet.print();
//        System.out.println("Balance: " + treeSet.isBalance());
//        treeSet.clear();
//        System.out.println("----------------------------------------");
//
//        treeSet.addWithLevel(10,4);
//        treeSet.print();
//        System.out.println("----------------------------------------");
//        treeSet.addWithLevel(20,4);
//        treeSet.print();
//        System.out.println("----------------------------------------");
//        treeSet.addWithLevel(30,4);
//        treeSet.print();
//        System.out.println("----------------------------------------");
//        treeSet.addWithLevel(40,4);
//        treeSet.print();
//        System.out.println("----------------------------------------");
//        treeSet.addWithLevel(13,4);
//        treeSet.print();
//        System.out.println("----------------------------------------");
//        treeSet.addWithLevel(25,4);
//        treeSet.print();
//        System.out.println("----------------------------------------");
//        treeSet.addWithLevel(15,4);
//        treeSet.addWithLevel(12,4);
//        treeSet.addWithLevel(5,4);
//        treeSet.addWithLevel(3,4);
//        treeSet.addWithLevel(1,4);
//        treeSet.addWithLevel(0,4);
//        treeSet.print();

        Random random = new Random();
        int balanced = 0;
        for (int i = 0; i < 20; i++) {
            treeSet.clear();
            for (int j = 0; j < 1000; j++) {
                int number = random.nextInt(41) - 20;
                treeSet.addWithLevel(number,4);
            }
            if (treeSet.isBalance()){
                balanced++;
            }
            System.out.println("Tree number: " + i + " Balanced: " + treeSet.isBalance());
            treeSet.print();
            System.out.println("------------------------------------------------------------------------------");
        }
        System.out.printf("Balanced trees: %6.2f",(balanced / 20.0 * 100));
    }
}
