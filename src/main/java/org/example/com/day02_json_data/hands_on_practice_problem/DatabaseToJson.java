package org.example.com.day02_json_data.hands_on_practice_problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.*;
import java.util.*;

public class DatabaseToJson {
    public static void main(String[] args) {
        String url = "https://graph.facebook.com/19292868552";
        String user = "root";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            List<Map<String, Object>> records = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                record.put("id", rs.getInt("id"));
                record.put("name", rs.getString("name"));
                record.put("email", rs.getString("email"));
                records.add(record);
            }

            // Convert to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(records);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
