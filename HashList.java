package ru.geekbrains.algjava.lesson8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashList implements Iterable{
    private List[] hashTable;
    private int size;
    private int count;
    private int countObjects;
    private HashListIterator iterator;

    public HashList(int size) {
        this.size = size;
        hashTable = new List[size];
        count = 0;
        countObjects = 0;
    }

    private int hashIndex(int hash){
        return hash % size;
    }

    public void add(Object o){
        Data temp = new Data(o);
        int index = hashIndex(temp.hashCode());
        if (hashTable[index] != null){
            hashTable[index].add(temp);
        }else{
            count++;
            List list = new ArrayList<Data>();
            list.add(temp);
            hashTable[index] = list;
        }
        countObjects++;
        fillRate();
    }

    public boolean delete(Object o){
        Data temp = new Data(o);
        int index = hashIndex(temp.hashCode());
        if (hashTable[index] != null){
            if(hashTable[index].remove(temp)){
                countObjects--;
            }else{
                return false;
            }
            if (hashTable[index].size() == 0){
                hashTable[index] = null;
                count--;
            }
            return true;
        }
        return false;
    }

    public boolean contains(Object o){
        Data temp = new Data(o);
        int index = hashIndex(temp.hashCode());
        if (hashTable[index] != null){
            return hashTable[index].contains(temp);
        }
        return false;
    }

    private void fillRate(){
        if (100 * count > 75 * size){
            System.out.println("Need to increase + recalculate");
            count = 0;
            countObjects = 0;
            size *= 2;
            List[] oldHashTable = hashTable;
            hashTable = new List[size];
            for (List list :
                    oldHashTable) {
                if (list != null){
                    for (Object o :
                            list) {
                        add(((Data) o).getO());
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[HashList size: " + size + "\t Count Objects: " + countObjects + "\t Count: " + count + "]" + System.lineSeparator());
        for (List list :
                hashTable) {
            stringBuilder.append(list + System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator iterator() {
        if (iterator == null){
            iterator = new HashListIterator();
        }
        return iterator;
    }

    private class HashListIterator implements Iterator{
        private int counter;
        private Iterator iterator;
        private int index;
//        private int index;
//        private int prevSubCounter;
//        private int prevIndex;
//        private int subListCounter;

        private HashListIterator() {
            counter = 0;
//            index = 0;
//            prevIndex = -1;
//            prevSubCounter = -1;
//            for (int i = 0; i < hashTable.length; i++) {
//                if (hashTable[i] != null){
//                    index = i;
//                    break;
//                }
//            }
            index = 0;
            while (hashTable[index] == null){
                index++;
                index %= size;
            }
            iterator = hashTable[index].iterator();
        }

        @Override
        public boolean hasNext() {
            return counter != countObjects;
        }

        @Override
        public Object next() {
//            if (!hasNext()){
//                throw new RuntimeException("Illegal iterator operation - iterator at END position");
//            }
//            Data temp = (Data) hashTable[index].get(subListCounter);
//            prevIndex = index;
//            prevSubCounter = subListCounter;
//            counter++;
//            if (hasNext()) {
//                if (subListCounter == hashTable[index].size() - 1) {
//                    subListCounter = 0;
//                    index++;
//                    index %= size;
//                    while (hashTable[index] == null){
//                        index++;
//                        index %= size;
//                    }
//                }else{
//                    subListCounter++;
//                }
//            }
//            return temp.getO();
            if (hasNext()) {
                if (iterator.hasNext()) {
                    counter++;
                    return ((Data) iterator.next()).getO();
                }else{
                    index++;
                    index %= size;
                    while (hashTable[index] == null){
                        index++;
                        index %= size;
                    }
                    iterator = hashTable[index].iterator();
                    counter++;
                    return ((Data) iterator.next()).getO();
                }
            }else{
                throw new RuntimeException("Illegal iterator operation - at END position");
            }
        }

        @Override
        public void remove() {
//            if (prevSubCounter == -1 && prevIndex == -1){
//                throw new RuntimeException("Illegal iterator operation - can not remove item !");
//            }
//            hashTable[prevIndex].remove(prevSubCounter);
//            countObjects--;
//            if (hashTable[prevIndex].size() == 0){
//                hashTable[prevIndex] = null;
//                count--;
//            }
//            prevSubCounter = -1;
//            prevIndex = -1;
            iterator.remove();
            countObjects--;
            counter--;
            if (!iterator.hasNext()){
                hashTable[index] = null;
                count--;
            }
        }
    }
}
