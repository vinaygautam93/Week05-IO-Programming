package org.example.com.day02_json_data.hands_on_practice_problem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilterJsonByAge {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode usersArray = objectMapper.readTree(new File("users.json"));

            List<JsonNode> filteredUsers = new ArrayList<>();

            // Iterate over JSON array and filter users
            for (JsonNode user : usersArray) {
                if (user.get("age").asInt() > 25) {
                    filteredUsers.add(user);
                }
            }

            // Convert the filtered list back to JSON and print
            String filteredJson = objectMapper.writeValueAsString(filteredUsers);
            System.out.println(filteredJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
