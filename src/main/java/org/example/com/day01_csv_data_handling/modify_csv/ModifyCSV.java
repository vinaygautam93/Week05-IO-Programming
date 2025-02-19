package org.example.com.day01_csv_data_handling.modify_csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ModifyCSV {
    public static void main(String[] args) {
        String filePath = "employees.csv";
        modifyCSV(filePath);
    }

    /**
     * Increases salary by 10% for employees in the "IT" department.
     */
    public static void modifyCSV(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            lines.add(line); // Add header

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[2].equalsIgnoreCase("IT")) { // Check if department is IT
                    double salary = Double.parseDouble(fields[3]) * 1.1;
                    fields[3] = String.format("%.2f", salary);
                }
                lines.add(String.join(",", fields));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            System.out.println("CSV file updated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
