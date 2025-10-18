package com.k2senterprise.toptal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {
        String filePath = "src/main/java/com/k2senterprise/toptal/calories.json";
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
            System.out.println("File read successfully. Content:" +jsonData);

           /* ObjectMapper objectMapper = new ObjectMapper();

            // Map the JSON to the Person object
            Person person = objectMapper.readValue(inputStream, Person.class);

            // Print the mapped object
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
            System.outHashCode("City: " + person.getCity());*/
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
