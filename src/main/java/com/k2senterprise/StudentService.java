package com.k2senterprise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentService {

    public List<Student> getData(){
        return List.of(
                new Student("Alice", 20, "IT"),
                new Student("Bob", 21, "Physics"),
                new Student("Charlie", 22, "Electronics"),
                new Student("David", 23, "IT"),
                new Student("Eve", 20, "Physics"),
                new Student("Frank", 21, "Electronics"),
                new Student("Grace", 22, "IT"),
                new Student("Heidi", 23, "Physics"),
                new Student("Ivan", 20, "Electronics"),
                new Student("Judy", 21, "IT")
        );
    }

    public void printData(){
        final List<Student> data = getData();
        Map<String, Double> classWithAvgAge = data.stream().collect(Collectors.groupingBy(Student::getClassName,
                Collectors.averagingInt(Student::getAge)));

        classWithAvgAge.forEach((className, avgAge) -> {
            System.out.println("Class: " + className);
            System.out.println("Average Age: " + avgAge);
        });


    }

    public static void main(String[] args) {
        StudentService s = new StudentService();
        s.printData();
    }
}