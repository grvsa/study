package ru.geekbrains.algjava.lesson7;

import java.util.Arrays;
/*
		A	B	C	D	E	F	G	H	I	K
		0	1	2	3	4	5	6	7	8	9
A	0		1
B	1	1		1	1	1
C	2		1				1		1
D	3		1					1
E	4		1					1
F	5			1
G	6				1	1			1
H	7			1				1		1	1
I	8								1
K	9								1		

 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        Graph graph = new Graph(10);
        Vertex v0 = new Vertex("A",10);
        Vertex v1 = new Vertex("B",10);
        Vertex v2 = new Vertex("C",10);
        Vertex v3 = new Vertex("D",10);
        Vertex v4 = new Vertex("E",10);
        Vertex v5 = new Vertex("F",10);
        Vertex v6 = new Vertex("G",10);
        Vertex v7 = new Vertex("H",10);
        Vertex v8 = new Vertex("I",10);
        Vertex v9 = new Vertex("K",10);

        graph.addVertex(v0);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);


        graph.addConnection(1,0,1);
        graph.addConnection(0,1,1);
        graph.addConnection(7,2,1);
        graph.addConnection(2,7,1);
        graph.addConnection(7,6,1);
        graph.addConnection(6,7,1);
        graph.addConnection(6,3,1);
        graph.addConnection(6,4,1);
        graph.addConnection(3,6,1);
        graph.addConnection(4,6,1);

        graph.addConnection(1,2,1);
        graph.addConnection(1,3,1);
        graph.addConnection(1,4,1);
        graph.addConnection(2,1,1);
        graph.addConnection(3,1,1);
        graph.addConnection(4,1,1);

        graph.addConnection(5,2,1);
        graph.addConnection(2,5,1);
        graph.addConnection(7,8,1);
        graph.addConnection(7,9,1);
        graph.addConnection(8,7,1);
        graph.addConnection(9,7,1);


        graph.bsf();
        System.out.println();
        Vertex[][] temp = v9.getPath();
        for (int i = 0; i < temp.length; i++) {
            System.out.println(Arrays.toString(temp[i]));
        }
    }
}
