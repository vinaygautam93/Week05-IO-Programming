package org.example.com.day01_csv_data_handling.search_csv;


import java.io.*;

public class SearchCSV {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        String searchName = "Alice"; // Name to search
        searchCSV(filePath, searchName);
    }

    /**
     * Searches for an employee by name in the CSV file and prints their details.
     */
    public static void searchCSV(String filePath, String searchName) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read header
            System.out.println(line); // Print header

            while ((line = br.readLine()) != null) {
                if (line.contains(searchName)) {
                    System.out.println("Found: " + line);
                    return;
                }
            }
            System.out.println("Record not found.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
