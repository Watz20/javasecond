package lesson9.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Oleg", Arrays.asList(new Course("Math"), new Course("Testing"), new Course("English"))));
        students.add(new Student("Alex", Arrays.asList(new Course("Russian"),new Course("Testing"), new Course("Boxing"),
                new Course("Literature"))));
        students.add(new Student("John", Arrays.asList(new Course("Math"))));

        //Написать функцию, принимающую список Student и возвращающую список уникальных курсов, на которые подписаны студенты.
        System.out.println(students.stream()
                .map(s -> s.getCourses())
                .flatMap(c -> c.stream())
                .collect(Collectors.toSet()));

        //Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных
        // (любознательность определяется количеством курсов).
        System.out.println(students.stream()
                .sorted((s1, s2) -> s2.getCourses().size() - s1.getCourses().size())
                 .limit(2)
                 .collect(Collectors.toList()));

        //Написать функцию, принимающую на вход список Student и экземпляр Course,
        // возвращающую список студентов, которые посещают этот курс.
        Course course = new Course("Math");
        System.out.println(students.stream()
                .filter(s -> s.getCourses().contains(course))
                .collect(Collectors.toList()));
    }
}
