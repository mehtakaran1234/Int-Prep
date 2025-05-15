package com.k2senterprise;

public class Student{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private String name;
    private int age;
    private String className;


    public Student(String name, int age, String className) {
        this.name = name;
        this.age = age;
        this.className = className;
    }
    //getter and setter methods


}
