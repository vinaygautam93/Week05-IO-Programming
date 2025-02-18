package org.example.com.day01_csv_data_handling.read_csv;

import java.io.*;

public class ReadCSV {
    public static void main(String[] args) {
        String filePath = "students.csv"; // CSV file path

        // Try-with-resources to automatically close the reader
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                // Skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] data = line.split(","); // Split by comma
                System.out.println("ID: " + data[0] + ", Name: " + data[1] +
                        ", Age: " + data[2] + ", Marks: " + data[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
