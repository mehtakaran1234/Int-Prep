package com.k2senterprise.wissen;

import java.util.HashMap;
import java.util.Map;

/******************************************************************************

 Welcome to GDB Online.
 GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
 C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
 Code, Compile, Run and Debug online from anywhere in world.

 *******************************************************************************/
class Student
{
    String name;
    int id;

    Student(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        return this.id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        StudentTest StudentTest = (StudentTest) obj;
        return (StudentTest.name.equals(this.name) && StudentTest.id == this.id);
    }

    @Override
    public String toString() {
        return "Student: " + this.name + "@" + Integer.toHexString(hashCode());
    }
}

public class DuplicateStudent {
    public static void main(String[] args)
    {

        StudentTest g1 = new StudentTest("aditya", 1);
        StudentTest g2 = new StudentTest("aditya", 1);

        Map<StudentTest, String> map = new HashMap<>();
        map.put(g1, "CSE");
        map.put(g2, "IT");

        for(StudentTest studentTest : map.keySet())
        {
            System.out.println("Key : " + studentTest);
            System.out.println("Value : " + map.get(studentTest).toString());
        }
    }
}

