package org.example.com.day01_csv_data_handling.sort_csv;

import java.io.*;
import java.util.*;

public class SortCSV {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        sortCSV(filePath);
    }

    /**
     * Sorts employees by salary in descending order and prints the top 5.
     */
    public static void sortCSV(String filePath) {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine();
            System.out.println(header); // Print header

            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }

            records.sort((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));

            for (int i = 0; i < Math.min(5, records.size()); i++) {
                System.out.println(String.join(",", records.get(i)));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

