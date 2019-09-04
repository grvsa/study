import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");
        Vertex vertexE = new Vertex("E");
        Vertex vertexF = new Vertex("F");
        Vertex vertexG = new Vertex("G");
        Vertex vertexH = new Vertex("H");
        Vertex vertexI = new Vertex("I");
        Vertex vertexJ = new Vertex("J");

        Graph graph = new Graph(12);
        System.out.println(graph.add(vertexA));
        System.out.println(graph.addAll(vertexB, vertexC, vertexD, vertexE, vertexF, vertexG, vertexH, vertexI));
        System.out.println(graph.add(vertexJ));

        graph.setConnection(vertexA,vertexB, 20);
        graph.setConnection(vertexA,vertexC, 20);
        graph.setConnection(vertexA,vertexD, 20);
        graph.setConnection(vertexB,vertexE, 20);
        graph.setConnection(vertexE,vertexJ, 20);
        graph.setConnection(vertexC,vertexF, 20);
        graph.setConnection(vertexF,vertexH, 20);
        graph.setConnection(vertexH,vertexJ, 20);
        graph.setConnection(vertexD,vertexG, 20);
        graph.setConnection(vertexG,vertexI, 20);
        graph.setConnection(vertexI,vertexJ, 20);

        graph.setConnection(vertexC,vertexE,5);

        graph.printMatrix();
//        graph.bfs();

//        graph.dfs();

        graph.printVertex();

        graph.route(vertexA, vertexJ);

        graph.printVertex();


//        Vertex vertex1 = new Vertex("1");
//        Vertex vertex2 = new Vertex("2");
//        Vertex vertex3 = new Vertex("3");
//        Vertex vertex4 = new Vertex("4");
//        Vertex vertex5 = new Vertex("5");
//        Vertex vertex6 = new Vertex("6");
//
//        graph.addAll(vertex1,vertex2,vertex3,vertex4,vertex5,vertex6);
//
//        graph.setConnection(vertex1,vertex6,14);
//        graph.setConnection(vertex1,vertex3,9);
//        graph.setConnection(vertex1,vertex2,7);
//
//        graph.setConnection(vertex2,vertex3,10);
//        graph.setConnection(vertex2,vertex4,15);
//
//        graph.setConnection(vertex4,vertex3,11);
//        graph.setConnection(vertex4,vertex5,6);
//
//        graph.setConnection(vertex3,vertex6,2);
//        graph.setConnection(vertex5,vertex6,19);
//
//        graph.printMatrix();
//
//        graph.route(vertex1,vertex5);
//
//        graph.printVertex();
    }
}
