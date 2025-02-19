package org.example.com.day01_csv_data_handling.detect_duplicates;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class DetectDuplicates {
    public static void main(String[] args) {
        String filePath = "students.csv";
        findDuplicates(filePath);
    }

    public static void findDuplicates(String filePath) {
        Set<String> seenIds = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (!seenIds.add(fields[0])) {
                    duplicates.add(line);
                }
            }

            System.out.println("Duplicate Records:");
            for (String dup : duplicates) {
                System.out.println(dup);
            }

        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }
}
