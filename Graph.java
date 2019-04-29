package ru.geekbrains.algjava.lesson7;

import java.util.ArrayDeque;
import java.util.Deque;

public class Graph {
    private int size;
    private int[][] connected;
    private Vertex[] list;
    private int count;
    private Deque<Vertex> stackQuene;

    public Graph(int size) {
        this.size = size;
        list = new Vertex[size];
        connected = new int[size][size];
        count = 0;
        stackQuene = new ArrayDeque<>();
    }

    public void clear(){
        for (Vertex v :
                list) {
            v.clear();
        }
    }

    public void addVertex(Vertex v){
        if (count != size){
            list[count++] = v;
        }else{
            throw new RuntimeException("Graph is full");
        }
    }

    public void addConnection(int x,int y,int value){
        if (x < size &&  y < size && x >= 0 && y >= 0){
            connected[y][x] = value;
        }else{
            throw new RuntimeException("Index out of Bounds");
        }
    }

    public void bsf(){
        stackQuene.add(list[0]);

        while (!stackQuene.isEmpty()){
            Vertex v = stackQuene.pollFirst();
            v.setPass(true);
            v.print();

            int index = find(v);
            for (int i = 0; i < count; i++) {
                if (!list[i].isPass()){
                    if (connected[i][index] > 0){
                        stackQuene.add(list[i]);
                        copy(v.getPath(),list[i].getPath());
                    }

                }
            }
        }
    }

    public int find(Vertex v){
        int result = -1;
        for (int i = 0; i < count; i++) {
            if (list[i].getName().equals(v.getName())){
                result = i;
                break;
            }
        }
        return result;
    }

    public void copy(Vertex [][] pathSource, Vertex[][] pathTarget){
//        for (int i = 0; i < count; i++) {
//            for (int j = 0; j < count; j++) {
//                pathTarget[i][j] = pathSource[i][j];
//            }
//        }
        int countTarget = 0;
        while (pathTarget[countTarget][0] != null){
            countTarget++;
        }
        int countSource = 0;
        while (pathSource[countSource][0] != null){
            for (int i = 0; i < pathSource[0].length; i++) {
                pathTarget[countTarget][i] = pathSource[countSource][i];
            }
            countSource++;
            countTarget++;
        }
    }
}
