package lesson4.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private final HashMap<String, String> book = new HashMap<>();

    public void addContact(String phone, String surName) {
        book.put(phone, surName);
    }

    public List<String> getPhones(String surname) {
        ArrayList<String> phones = new ArrayList<>();
        for (Map.Entry<String, String> entry : book.entrySet()) {
            if (entry.getValue().equals(surname)) {
                phones.add(entry.getKey());
            }
        }
        return phones;
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addContact("8905648077", "Петров");
        phoneBook.addContact("8912450802", "Иванов");
        phoneBook.addContact("8903223802", "Петров");
        phoneBook.addContact("897880802", "Ветров");
        phoneBook.addContact("8908654802", "Танков");

        System.out.println(phoneBook.getPhones("Петров"));
    }
}
