package ru.geekbrains.java2.lesson3;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private Map<String,String> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public String get(String family){
        StringBuilder sb = new StringBuilder("Фамилия: " + family + " Телефоны: ");
        boolean contains = false;
        for (Map.Entry<String, String> m :
                phoneBook.entrySet()) {
            if (m.getValue().equals(family)) {
                contains = true;
                sb.append(m.getKey() + " ");
            }
        }
        if (!contains) sb.append("не зарегистрировано");
        return sb.toString();
    }

    public void add(String phone, String family){
        phoneBook.put(phone,family);
    }
}