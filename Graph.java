import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Graph {
    private Vertex[] vArray;
    private int[][] connectionMatrix;
    private int maxSize;
    private ArrayDeque<Vertex> deque;
    private PriorityQueue<Vertex> priorityQueue;

    public Graph(int maxSize) {
        this.maxSize = maxSize;
        connectionMatrix = new int[maxSize][maxSize];
        vArray = new Vertex[maxSize];
        deque = new ArrayDeque<Vertex>();
        priorityQueue = new PriorityQueue<Vertex>();
    }

    public boolean add(Vertex vertex){
        for (int i = 0; i < vArray.length; i++) {
            if (vArray[i] == vertex){
                break;
            }
            if (vArray[i] == null){
                vArray[i] = vertex;
                return true;
            }
        }
        return false;
    }

    public boolean addAll(Vertex...vertexArray){
        for (Vertex vertex : vertexArray) {
            if (!add(vertex)){
                return false;
            }
        }
        return true;
    }

    public void setConnection(Vertex from, Vertex to, int length){
        int indexFrom = indexOf(from);
        int indexTo = indexOf(to);
        if (indexFrom != -1 && indexTo != -1 && indexFrom != indexTo){
            connectionMatrix[indexFrom][indexTo] = length;
            connectionMatrix[indexTo][indexFrom] = length;
        }
    }

    public void printMatrix(){
        System.out.print("\t");
        int count = 0;
        for (int i = 0; i < vArray.length; i++) {
            if (vArray[i] != null){
                System.out.print(vArray[i] + "\t");
            }else{
                count = i;
                break;
            }
        }
        System.out.println();
        for (int i = 0; i < count; i++) {
            System.out.print(vArray[i] + "\t");
            for (int j = 0; j < count; j++) {
                System.out.print(connectionMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private int indexOf(Vertex vertex){
        for (int i = 0; i < vArray.length; i++) {
            if (vArray[i] == vertex){
                return i;
            }
        }
        return -1;
    }

    public void route(Vertex fromVertex, Vertex toVertex){
        fromVertex.setDistance(0);
        priorityQueue.add(fromVertex);

        while (!priorityQueue.isEmpty()){
            Vertex firstVertex = priorityQueue.poll();
//            priorityQueue.clear();

            int indexFirst = indexOf(firstVertex);

            for (int i = 0; i < connectionMatrix.length; i++) {
                if (connectionMatrix[indexFirst][i] != 0 && vArray[i] != null && !vArray[i].isVisited()){
                    Vertex nextVertex = vArray[i];
                    if (nextVertex.getDistance() > firstVertex.getDistance() + connectionMatrix[indexFirst][i]){
                        nextVertex.setDistance(firstVertex.getDistance() + connectionMatrix[indexFirst][i]);
                        nextVertex.setPreviousVertex(firstVertex);
                    }
                    priorityQueue.add(nextVertex);
                }
            }
            firstVertex.visit();
        }

        System.out.println("Min distance: " + toVertex.getDistance());
        String route = "";
        while (toVertex != null){
            route = " - " + toVertex + route;
            toVertex = toVertex.getPreviousVertex();
        }
        System.out.println(route.substring(3));
    }

    public void printVertex(){
        for (int i = 0; i < vArray.length; i++) {
            if (vArray[i] != null){
                System.out.println(vArray[i].print());
            }
        }
    }

    public void dfs(){
        if (vArray[0] != null){
            deque.push(vArray[0]);
        }else{
            return;
        }

        while (!deque.isEmpty()){
            Vertex topVertex = deque.peek();
            if (!topVertex.isVisited()){
                System.out.println(topVertex);
                topVertex.visit();
            }
            int indexTop = indexOf(topVertex);
            for (int i = 0; i < connectionMatrix.length; i++) {
                if (connectionMatrix[indexTop][i] != 0 && vArray[i] != null && !vArray[i].isVisited()){
                    deque.push(vArray[i]);
                    break;
                }
            }
            if (deque.peek() == topVertex){
                deque.pop();
            }
        }
    }

    public void bfs(){
        if (vArray[0] != null){
            deque.add(vArray[0]);
        }else{
            return;
        }

        while (!deque.isEmpty()){
            Vertex firstVertex = deque.removeFirst();

            if (!firstVertex.isVisited()){
                System.out.println(firstVertex);
                firstVertex.visit();
            }

            int indexTop = indexOf(firstVertex);
            for (int i = 0; i < connectionMatrix.length; i++) {
                if (connectionMatrix[indexTop][i] != 0 && vArray[i] != null && !vArray[i].isVisited()){
                    deque.add(vArray[i]);
                }
            }
        }
    }

    public void clear(){
        for (int i = 0; i < vArray.length; i++) {
            if (vArray[i] != null){
                vArray[i].clear();
            }else{
                break;
            }
        }
        vArray = new Vertex[maxSize];
        connectionMatrix = new int[maxSize][maxSize];
    }
}
