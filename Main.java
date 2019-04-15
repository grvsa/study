package ru.geekbrains.algjava.lesson4;
/*
List
insert - в начало списка
delete - из начала списка
display - вывод всех элементов списка
getNext - след элемент
setNext - установить след элемент
getValue - получить значение текущего

insert ?????
isEmpty
delete
display
find

 Iterator
    ●	reset() — перемещение в начало списка;
    ●	nextLink() — перемещение итератора к следующему элементу;
    ●	getCurrent() — получение элемента, на который указывает итератор;
    ●	atEnd() — возвращает true, если итератор находится в конце списка;
    ●	insertAfter() — вставка элемента после итератора;
    ●	insertBefore() — вставка элемента до итератора;
    ●	deleteCurrent() — удаление элемента в текущей позиции итератора.

 */
public class Main {

    public static void main(String[] args) {
	// write your code here
    //  List
        List list = new List();
        System.out.println(list);
        list.insert("Alex");
        list.insert("Pavel");
        list.insert("Maxim");
        list.insert("Genady");
        list.insert("Yermolay");
        System.out.println(list);
        System.out.println(list.getValue());
        System.out.println(list.contais("Sergy"));
        System.out.println(list.contais("Alex"));
        System.out.println(list.contais("Pavel"));
        System.out.println(list.contais("Sergey"));
        list.delete();
        System.out.println(list);
        list.delete("Pavel");
        System.out.println(list);
    //  LinkedList

        LinkedList linkedList = new LinkedList();
        System.out.println(linkedList);
        linkedList.insert("Sasha 1");
        linkedList.insert("Sasha 2");
        linkedList.insert("Sasha 3");
        linkedList.insert("Sasha 4");
        System.out.println(linkedList);
        linkedList.delete();
        System.out.println(linkedList);
        linkedList.delete("Sasha 2");
        System.out.println(linkedList);
        linkedList.deleteLast();
        linkedList.deleteLast();
        System.out.println(linkedList.isEmpty());
        linkedList.insert("First");
        linkedList.insert("Second");
        linkedList.insertLast("Third");
        System.out.println(linkedList);
        System.out.println(linkedList.getValue());
        System.out.println(linkedList.getLastValue());
        linkedList.deleteLast();
        linkedList.deleteLast();
        System.out.println(linkedList);
        linkedList.setValue("Fifth");
        System.out.println(linkedList);
        linkedList.delete();
    //  LinkedList Iterator
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);
        linkedList.insert(4);
        linkedList.insert(5);
        System.out.println(linkedList);
        LinkedListIterator iterator = new LinkedListIterator(linkedList);
        while(!iterator.atEnd()){
            System.out.println(iterator.nextLink());
        }
        iterator.reset();
        iterator.nextLink();
        iterator.insertBefore(2);
        iterator.insertAfter(7);
        System.out.println(linkedList);
        iterator.deleteCurrent();
        System.out.println(linkedList);
        iterator.reset();
        System.out.println(iterator.getCurrent());
        iterator.insertBefore(1);
        System.out.println(linkedList);
        iterator.nextLink();
        iterator.nextLink();
        iterator.nextLink();
        iterator.nextLink();
        iterator.nextLink();
        System.out.println(iterator.getCurrent());
        //iterator.nextLink();
        iterator.insertAfter(10);
        System.out.println(linkedList);
        iterator.deleteCurrent();
        iterator.deleteCurrent();
        iterator.deleteCurrent();
        iterator.deleteCurrent();
        iterator.deleteCurrent();
        iterator.deleteCurrent();
        iterator.deleteCurrent();
        System.out.println(linkedList);
        iterator.deleteCurrent();
        iterator.deleteCurrent();

    }
}
