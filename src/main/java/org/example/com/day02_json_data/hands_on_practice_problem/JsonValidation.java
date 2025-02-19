package org.example.com.day02_json_data.hands_on_practice_problem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import java.io.File;
import java.io.IOException;

public class JsonValidation {
    public static void main(String[] args) throws IOException, ProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load JSON Schema
        JsonNode schemaNode = objectMapper.readTree(new File("schema.json"));
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(schemaNode);

        // Load JSON Data
        JsonNode jsonData = objectMapper.readTree(new File("user.json"));

        // Validate JSON
        if (schema.validate(jsonData).isSuccess()) {
            System.out.println("JSON is valid!");
        } else {
            System.out.println("Invalid JSON!");
        }
    }
}
