package ru.geekbrains.algjava.lesson6;

public class Main {

    public static void main(String[] args) {
	// write your code here
        TreeNode treeNode = new TreeNode(50);
        treeNode.add(40);
        treeNode.add(60);
        treeNode.add(35);
        treeNode.add(25);
        treeNode.add(36);
        treeNode.add(41);
        treeNode.add(66);
        treeNode.add(65);
        treeNode.add(67);
        //System.out.println(treeNode.printTree());
        System.out.println(treeNode.depth());
        System.out.println(treeNode.min());
        System.out.println(treeNode.max());
        System.out.println(treeNode.size());
        System.out.println(treeNode.find(50));
        System.out.println(treeNode.find(25));
        System.out.println(treeNode.find(60));
        System.out.println(treeNode.find(36));
        System.out.println(treeNode.find(11));

        treeNode.print();
        System.out.println("Balance: " + treeNode.balance());
        System.out.println(treeNode.delete(30));
        System.out.println(treeNode.delete(67));
        treeNode.print();
        System.out.println(treeNode.delete(50));
        treeNode.print();
        System.out.println(treeNode.delete(41));
        treeNode.print();
        System.out.println(treeNode.delete(40));
        treeNode.print();
        System.out.println(treeNode.delete(35));
        treeNode.print();
        System.out.println(treeNode.delete(60));
        treeNode.print();
        System.out.println(treeNode.delete(66));
        treeNode.print();
        System.out.println(treeNode.delete(65));
        treeNode.print();
        System.out.println(treeNode.delete(36));
        treeNode.print();
        System.out.println(treeNode.delete(25));
        treeNode.print();
        treeNode.add(60);
        treeNode.add(50);
        treeNode.add(70);
        treeNode.print();
        System.out.println(treeNode.delete(50));
        treeNode.print();
        System.out.println(treeNode.delete(60));
        treeNode.print();
        System.out.println(treeNode.balance());
        treeNode.add(75);
        treeNode.print();
        System.out.println("Balance: " + treeNode.balance());
        treeNode.add(74);
        treeNode.print();
        System.out.println("Balance: " + treeNode.balance());
        treeNode.add(61);
        treeNode.print();
        System.out.println("Balance: " + treeNode.balance());
        treeNode.add(50);
        treeNode.add(40);
        treeNode.add(65);
        treeNode.add(63);
        treeNode.add(67);
        treeNode.add(55);
        treeNode.print();
        System.out.println("Balance: " + treeNode.balance());
        treeNode.add(73);
        treeNode.print();
        System.out.println("Balance: " + treeNode.balance());
        System.out.println(treeNode.depth());

        int unBalanceCount = 0;
        for (int i = 0; i < 20; i++) {
            TreeNode treeNode1 = new TreeNode();
            while (treeNode1.depth() < 7){
                treeNode1.add((int) (Math.random() * 201 - 100));
            }
            System.out.println("Tree #" + i + " Balance: " + treeNode.balance());
            treeNode1.print();
            if (!treeNode1.balance()){
                unBalanceCount++;
            }
        }
        System.out.printf("Процент несбалансированных деревьев : %f",(unBalanceCount / 20.0 * 100));
    }
}
