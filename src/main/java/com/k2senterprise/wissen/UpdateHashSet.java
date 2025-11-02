package com.k2senterprise.wissen;

import java.util.HashSet;

//Consider the following program to insert values into HashSet.
class StudentTest {
    public int id;
    public String name;

    public StudentTest(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int hashCode() {
        return this.id;
    }

    public String toString() {
        return "Student: " + this.name + "@" + Integer.toHexString(hashCode());
    }

    public boolean equals(Object o) {
        if (o instanceof StudentTest) {
            StudentTest s = (StudentTest) o;
            return s.id == this.id ? true : false;
        }
        return false;
    }
}

public class UpdateHashSet {

    public static void main(String[] args) {
        HashSet<StudentTest> studentTestList = new HashSet<>();

        StudentTest st1 = new StudentTest("Nimit", 1);
        StudentTest st2 = new StudentTest("Rahul", 3);
        StudentTest st3 = new StudentTest("Nimit", 2);
        studentTestList.add(st1);
        studentTestList.add(st2);
        studentTestList.add(st3);

        System.out.println(studentTestList.size());

        st1.id = 2;
        System.out.println(studentTestList.size());
    }
}

//What will be the output?
