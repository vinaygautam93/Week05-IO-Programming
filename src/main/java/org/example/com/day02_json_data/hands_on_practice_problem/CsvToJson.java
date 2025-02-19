package org.example.com.day02_json_data.hands_on_practice_problem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.util.List;
import java.util.Map;

public class CsvToJson {
    public static void main(String[] args) {
        try {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader(); // Read CSV with a header

            // Read CSV into a list of maps (key-value pairs)
            List<Map<String, String>> data = csvMapper.readerFor(new TypeReference<List<Map<String, String>>>() {})
                    .with(csvSchema)
                    .readValue(new File("data.csv"));

            // Convert list to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

            // Print JSON output
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
