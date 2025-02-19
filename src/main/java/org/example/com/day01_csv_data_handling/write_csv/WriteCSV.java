package org.example.com.day01_csv_data_handling.write_csv;

import java.io.FileWriter;
import java.io.IOException;

public class WriteCSV {
    public static void main(String[] args) {
        String filePath = "employees.csv";

        try (FileWriter writer = new FileWriter(filePath)) {
            // Writing headers
            writer.append("ID,Name,Department,Salary\n");

            // Writing data
            writer.append("101,John Doe,IT,50000\n");
            writer.append("102,Jane Smith,HR,60000\n");
            writer.append("103,Robert Brown,Finance,55000\n");
            writer.append("104,Emily Johnson,Marketing,58000\n");
            writer.append("105,Michael White,IT,62000\n");

            System.out.println("CSV file written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
