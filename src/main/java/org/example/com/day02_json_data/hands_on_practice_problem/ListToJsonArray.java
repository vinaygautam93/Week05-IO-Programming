package org.example.com.day02_json_data.hands_on_practice_problem;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;

class User {
    private String name;
    private int age;
    private String email;

    // Constructor
    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters (Required for Jackson)
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
}

public class ListToJsonArray {
    public static void main(String[] args) {
        try {
            // Create a list of User objects
            List<User> users = new ArrayList<>();
            users.add(new User("Alice", 24, "alice@example.com"));
            users.add(new User("Bob", 30, "bob@example.com"));
            users.add(new User("Charlie", 28, "charlie@example.com"));

            // Convert list to JSON array
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writeValueAsString(users);

            // Print JSON output
            System.out.println(jsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
