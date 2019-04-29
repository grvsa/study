package ru.geekbrains.algjava.lesson7;

import java.util.ArrayList;

public class Vertex {
    private String name;
    private boolean pass;
    private Vertex[][] path;
    private int size;

    public Vertex(String name, int size) {
        this.name = name;
        this.size = size;
        pass = false;
        path = new Vertex[size][size];
    }

    public String getName() {
        return name;
    }

    public boolean isPass() {
        return pass;
    }

    public Vertex[][] getPath() {
        return path;
    }

    public void clear(){
        pass = false;
        path = new Vertex[size][size];
    }

    public void setPass(boolean pass) {
        this.pass = pass;
        if (path[0][0] == null){
            path[0][0] = this;
            return;
        }
        for (int i = 0; i < path.length; i++) {
            if (path[i][0] == null){
                return;
            }
            for (int j = 0; j < path[0].length; j++) {
                if (path[i][j] == null){
                    if (path[i][j - 1] != this) {
                        path[i][j] = this;
                    }
                    j = size;
                }

            }
        }

    }
    public void print(){
        System.out.print(" [" + name + "] ");
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }

}
