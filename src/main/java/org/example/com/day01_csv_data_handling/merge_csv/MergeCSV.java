package org.example.com.day01_csv_data_handling.merge_csv;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MergeCSV {
    public static void main(String[] args) {
        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String outputFile = "merged_students.csv";

        mergeCSV(file1, file2, outputFile);
    }

    public static void mergeCSV(String file1, String file2, String outputFile) {
        Map<String, String> studentData = new HashMap<>();

        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            br1.readLine(); // Skip header
            br2.readLine();

            String line;
            while ((line = br1.readLine()) != null) {
                String[] fields = line.split(",");
                studentData.put(fields[0], line);
            }

            while ((line = br2.readLine()) != null) {
                String[] fields = line.split(",");
                studentData.put(fields[0], studentData.getOrDefault(fields[0], "") + "," + fields[1] + "," + fields[2]);
            }

            bw.write("ID,Name,Age,Marks,Grade");
            bw.newLine();
            for (String value : studentData.values()) {
                bw.write(value);
                bw.newLine();
            }

            System.out.println("CSV files merged successfully!");

        } catch (IOException e) {
            System.err.println("Error merging CSV files: " + e.getMessage());
        }
    }
}



