package org.example.com.day01_csv_data_handling.count_rows;

import java.io.*;

public class CountRowsCSV {
    public static void main(String[] args) {
        String filePath = "students.csv"; // Path to CSV file
        int rowCount = countRows(filePath);
        System.out.println("Total number of records (excluding header): " + rowCount);
    }

    /**
     * Reads a CSV file and counts the number of rows (excluding the header).
     * @param filePath The file path of the CSV file.
     * @return The number of records excluding the header.
     */
    public static int countRows(String filePath) {
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header row
            while (br.readLine() != null) {
                count++; // Increment count for each row
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return count;
    }
}
