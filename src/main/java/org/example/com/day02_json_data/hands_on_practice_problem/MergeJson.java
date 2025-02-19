package org.example.com.day02_json_data.hands_on_practice_problem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class MergeJson {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON files
            JsonNode json1 = objectMapper.readTree(new File("data1.json"));
            JsonNode json2 = objectMapper.readTree(new File("data2.json"));

            // Merge JSON objects
            JsonNode mergedJson = deepMerge(json1, json2);

            // Print the merged JSON object
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Recursive deep merge function
    public static JsonNode deepMerge(JsonNode mainNode, JsonNode updateNode) {
        if (mainNode.isObject() && updateNode.isObject()) {
            ObjectNode mainObject = (ObjectNode) mainNode;
            updateNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode value = entry.getValue();

                if (mainObject.has(fieldName) && value.isObject()) {
                    // Recursive merge for nested objects
                    JsonNode mergedValue = deepMerge(mainObject.get(fieldName), value);
                    mainObject.set(fieldName, mergedValue);
                } else {
                    mainObject.set(fieldName, value);
                }
            });
        }
        return mainNode;
    }
}
