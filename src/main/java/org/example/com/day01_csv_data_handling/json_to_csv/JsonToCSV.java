package org.example.com.day01_csv_data_handling.json_to_csv;


// This requires the Jackson library


import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;
import java.io.*;
import java.util.List;

public class JsonToCSV {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<?> students = mapper.readValue(new File("students.json"), List.class);

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(Map.class).withHeader();
        csvMapper.writer(schema).writeValue(new File("students.csv"), students);

        System.out.println("JSON converted to CSV successfully.");
    }
}

