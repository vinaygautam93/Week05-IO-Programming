package org.example.com.day02_json_data.practice_problem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilterJsonByAge {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode (Tree structure)
            JsonNode usersArray = objectMapper.readTree(new File("users.json"));

            // List to store filtered users
            List<JsonNode> filteredUsers = new ArrayList<>();

            // Loop through JSON array and filter users with age > 25
            for (JsonNode user : usersArray) {
                if (user.get("age").asInt() > 25) {
                    filteredUsers.add(user);
                }
            }

            // Convert filtered users list back to JSON and print
            String filteredJson = objectMapper.writeValueAsString(filteredUsers);
            System.out.println(filteredJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
