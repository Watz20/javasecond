package lesson4.hw4;

import java.util.HashMap;

public class Main {
/*
Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
Посчитать, сколько раз встречается каждое слово.
2.
Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью
 метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией
  может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии
  должны выводиться все телефоны. Желательно не добавлять лишний функционал
  (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем через консоль и т.д).
  Консоль использовать только для вывода результатов проверки телефонного справочника.
 */

    public static void main(String[] args) {
        String[] words = {"Math", "Apple", "Cabbage", "Art", "Math", "Onion", "Art", "Math", "Art", "Joy", "Map", "Onion"};
        HashMap<String, Integer> wordUniqueWithRepeats = new HashMap<>();

        for (String word : words) {
            if (wordUniqueWithRepeats.containsKey(word)) {
                wordUniqueWithRepeats.put(word, wordUniqueWithRepeats.get(word) + 1);
            } else {
                wordUniqueWithRepeats.put(word, 1);
            }
        }
        System.out.println(wordUniqueWithRepeats);
    }
}