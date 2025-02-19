package org.example.com.day01_csv_data_handling.read_large_csv;

import java.io.*;

public class ReadLargeCSV {
    public static void main(String[] args) {
        String filePath = "large_data.csv";
        readCSVInChunks(filePath, 100);
    }

    public static void readCSVInChunks(String filePath, int chunkSize) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                count++;
                if (count % chunkSize == 0) {
                    System.out.println(count + " records processed...");
                }
            }
            System.out.println("Total records processed: " + count);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
