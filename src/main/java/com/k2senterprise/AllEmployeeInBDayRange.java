package com.k2senterprise;

import java.util.ArrayList;
import java.util.List;

class Employee {
    String name;
    String birthDate;

    Employee(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }
}

public class AllEmployeeInBDayRange {

    public static void findEmployeesInBDayRange(List<Employee> employees, String startDate, String endDate) {
        for (Employee employee : employees) {
            if (employee.getBirthDate().compareTo(startDate) >= 0 && employee.getBirthDate().compareTo(endDate) <= 0) {
                System.out.println(employee.getName() + " has a birthday between " + startDate + " and " + endDate);
            }
        }
    }

    static List<Employee> getAllEmployees(){
        List<Employee> empList = new ArrayList<>();

        empList.add(new Employee("9999", "2020/11/07"));
        empList.add(new Employee("4444", "2020/12/06"));
        empList.add(new Employee("1233", "2020/11/07"));
        empList.add(new Employee("1234", "2020/12/06"));
        empList.add(new Employee("5678", "2020/11/15"));
        empList.add(new Employee("9101", "2020/10/30"));
        empList.add(new Employee("1121", "2020/11/25"));
        empList.add(new Employee("3141", "2021/01/01"));
        empList.add(new Employee("5161", "2020/11/01"));
        empList.add(new Employee("7181", "2020/11/30"));
        return empList;
    }

    public static void main(String[] args) {
        findEmployeesInBDayRange(getAllEmployees(), "2020/11/01", "2020/11/30");
    }

}
