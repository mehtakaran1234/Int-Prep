package com.k2senterprise.amazon.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static List<Employee> generateRandomEmployeeList() {
        Employee emp1 = new Employee("John", 101);
        Employee emp2 = new Employee("Alice", 102);
        Employee emp3 = new Employee("John", 101); // duplicate
        Employee emp4 = new Employee("John", 101);
        Employee emp5 = new Employee("John", 102);
        return List.of(emp1, emp2, emp3, emp4, emp5);
    }

    public static void main(String[] args) {
        List<Employee> emp = generateRandomEmployeeList();
        Map<Integer, List<String>> map = new HashMap<>();
        for (Employee e : emp) {
            if(map.containsKey(e.getId())){
                List<String> employeeFromMapList = map.get(e.getId());
                if(!employeeFromMapList.contains(e.getName())){
                    employeeFromMapList.add(e.getName());
                }
                map.put(e.getId(), employeeFromMapList);
            }
            else {
                List<String> employeeList = new ArrayList<>();
                employeeList.add(e.getName());
                map.put(e.getId(), employeeList);
            }
        }
        System.out.println(map);

    }
}
