package org.example.com.day02_json_data.hands_on_practice_problem;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Iterator;

// Create ReadJsonKeysValues read JSON file and print all keys and values
class ReadJsonKeysValues {
    public static void main(String[] args) {
        try {
            // Read JSON file content as a string
            String content = new String(Files.readAllBytes(Paths.get("src/main/java/org/Person.json")));

            // Convert string to JSON object
            JSONObject jsonObject = new JSONObject(content);

            // Iterate over all keys and print key-value pairs
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                System.out.println(key + ": " + jsonObject.get(key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

