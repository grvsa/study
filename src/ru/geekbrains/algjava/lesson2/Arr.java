package ru.geekbrains.algjava.lesson2;

public class Arr {
    private int[][] iArray;
    private int size;
    private int find;
    private int findNuber;
    private boolean isSorted;
    private static final int ARRAY_FRAME = 1000;

    public Arr(){
        iArray = new int[1][ARRAY_FRAME];
        size = 0;
        find = -1;
        isSorted = false;
    }

    public void sortBouble(){
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (iArray[j / ARRAY_FRAME][j % ARRAY_FRAME] > iArray[(j + 1) / ARRAY_FRAME][(j + 1) % ARRAY_FRAME]){
                    int temp = iArray[j / ARRAY_FRAME][j % ARRAY_FRAME];
                    iArray[j / ARRAY_FRAME][j % ARRAY_FRAME] = iArray[(j + 1) / ARRAY_FRAME][(j + 1) % ARRAY_FRAME];
                    iArray[(j + 1) / ARRAY_FRAME][(j + 1) % ARRAY_FRAME] = temp;
                }
            }
        }
        isSorted = true;
    }

    public void sortSelection(){
        for (int i = 0; i < size; i++) {
            int min = iArray[i / ARRAY_FRAME][i % ARRAY_FRAME];
            int pos = i;
            for (int j = i + 1; j < size ; j++) {
                if (iArray[j / ARRAY_FRAME][j % ARRAY_FRAME] < min){
                    min = iArray[j / ARRAY_FRAME][j % ARRAY_FRAME];
                    pos = j;
                }
            }
            if (pos != i){
                iArray[pos / ARRAY_FRAME][pos % ARRAY_FRAME] = iArray[i / ARRAY_FRAME][i % ARRAY_FRAME];
                iArray[i / ARRAY_FRAME][i % ARRAY_FRAME] = min;
            }
        }
        isSorted = true;
    }

    public void sortInsertion(){
        for (int i = 1; i < size; i++) {
            int pos = i;
            while(pos > 0 && (iArray[pos / ARRAY_FRAME][pos % ARRAY_FRAME] < iArray[(pos - 1) / ARRAY_FRAME][(pos - 1) % ARRAY_FRAME])){
                int temp = iArray[pos / ARRAY_FRAME][pos % ARRAY_FRAME];
                iArray[pos / ARRAY_FRAME][pos % ARRAY_FRAME] = iArray[(pos - 1) / ARRAY_FRAME][(pos - 1) % ARRAY_FRAME];
                iArray[(pos - 1) / ARRAY_FRAME][(pos - 1) % ARRAY_FRAME] = temp;
                pos--;
            }
        }
        isSorted = true;
    }

    public int booleanFind(int number){
        if (isSorted){
            int first = 0;
            int last = size / 2;
            int index;
            while(first < last){
                index = (first + last) / 2;
                if (iArray[index / ARRAY_FRAME][index % ARRAY_FRAME] == number){
                    return index;
                }else if(iArray[index / ARRAY_FRAME][index % ARRAY_FRAME] > number){
                    last = index;
                }else{
                    first = index;
                }
            }
        }
        return -1;
    }

    public void clear(){
        iArray = new int[1][ARRAY_FRAME];
        size = 0;
        find = -1;
        isSorted = false;
    }

    public int find(int number){
        for (int i = 0; i < size; i++) {
            if (iArray[i / ARRAY_FRAME][i % ARRAY_FRAME] == number){
                find = i;
                findNuber = number;
                return find;
            }
        }
        return -1;
    }

    public int findNext(){
        if (find >= 0){
            for (int i = find + 1; i < size; i++) {
                if (iArray[i / ARRAY_FRAME][i % ARRAY_FRAME] == findNuber){
                    find = i;
                    return find;
                }
            }
        }
        return -1;
    }

    public void add(int i){
        if (size == iArray.length * ARRAY_FRAME){
            update();
            add(i);
        }else{
            iArray[size / ARRAY_FRAME][size % ARRAY_FRAME] = i;
            size++;
        }
        update();
        isSorted = false;
    }

    public int get(int index){
        if (index < 0 || index >= size){
            throw new RuntimeException("Out of Bounds");
        }
        return iArray[index / ARRAY_FRAME][index % ARRAY_FRAME];
    }

    public void set(int index , int number){
        if (index < 0 || index >= size){
            throw new RuntimeException("Out of Bounds");
        }
        iArray[index / ARRAY_FRAME][index % ARRAY_FRAME] = number;
    }

    public void remove(){
        if (size > 0){
            size--;
        }
        update();
        isSorted = false;
    }

    public void remove(int index){
        if (index < 0 || index >= size){
            throw new RuntimeException("Out of Bounds");
        }
        for (int i = index; i < size; i++) {
            iArray[i / ARRAY_FRAME][i % ARRAY_FRAME] = iArray[(i + 1) / ARRAY_FRAME][i % ARRAY_FRAME + 1];
        }
        size--;
        update();
    }

    public void insert(int index, int number){
        if (index < 0 || index >= size){
            throw new RuntimeException("Out of Bounds");
        }
        for (int i = size; i > index ; i--) {
            iArray[i / ARRAY_FRAME][i % ARRAY_FRAME] = iArray[(i - 1)/ ARRAY_FRAME][i % ARRAY_FRAME - 1];
        }
        iArray[index / ARRAY_FRAME][index % ARRAY_FRAME] = number;
        size++;
        update();
        isSorted = false;
    }

    public void test(){
        System.out.printf("Array Y: %5d%nArray X: %5d%n",iArray.length,iArray[0].length);
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Arr{size=" + size +"} [");
        for (int i = 0; i < size; i++) {
            sb.append(iArray[i / ARRAY_FRAME][i % ARRAY_FRAME]);
            if (i < size - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void update(){
        if (size / ARRAY_FRAME + 1 == iArray.length && size % ARRAY_FRAME > ARRAY_FRAME / 2) {
            int[][] temp = new int[iArray.length + 1][ARRAY_FRAME];
            for (int i = 0; i < iArray.length; i++) {
                temp[i] = iArray[i];
            }
            iArray = temp;
            update();
        }else if (size / ARRAY_FRAME + 2 == iArray.length && size % ARRAY_FRAME < ARRAY_FRAME / 2){
            int[][] temp = new int[iArray.length - 1][ARRAY_FRAME];
            for (int i = 0; i < iArray.length - 1; i++) {
                temp[i] = iArray[i];
            }
            iArray = temp;
            update();
        }
    }
}
