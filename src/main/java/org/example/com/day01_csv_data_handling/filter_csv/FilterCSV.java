package org.example.com.day01_csv_data_handling.filter_csv;

import java.io.*;

public class FilterCSV {
    public static void main(String[] args) {
        String filePath = "students.csv"; // CSV file with student records
        int threshold = 80; // Marks threshold
        filterCSV(filePath, threshold);
    }

    /**
     * Reads a CSV file and filters students with marks above the threshold.
     */
    public static void filterCSV(String filePath, int threshold) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read header
            if (line == null) {
                System.err.println("CSV file is empty.");
                return;
            }
            System.out.println(line); // Print header

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Ensure the array has at least 4 elements and marks column is not empty
                if (fields.length > 3 && !fields[3].trim().isEmpty()) {
                    try {
                        int marks = Integer.parseInt(fields[3].trim()); // Trim to remove extra spaces
                        if (marks > threshold) {
                            System.out.println(line); // Print matching records
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid marks entry: " + fields[3]);
                    }
                } else {
                    System.err.println("Skipping line due to missing or empty marks: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
