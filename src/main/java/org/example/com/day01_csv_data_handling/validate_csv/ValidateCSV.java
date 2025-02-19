package org.example.com.day01_csv_data_handling.validate_csv;

import java.io.*;
import java.util.regex.*;

public class ValidateCSV {
    public static void main(String[] args) {
        String filePath = "contacts.csv";
        validateCSV(filePath);
    }

    /**
     * Validates email and phone number fields in a CSV file.
     */
    public static void validateCSV(String filePath) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String phoneRegex = "\\d{10}";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read header
            System.out.println(line); // Print header

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Matcher emailMatcher = emailPattern.matcher(fields[1]);
                Matcher phoneMatcher = phonePattern.matcher(fields[2]);

                if (!emailMatcher.matches()) {
                    System.out.println("Invalid Email: " + fields[1]);
                }
                if (!phoneMatcher.matches()) {
                    System.out.println("Invalid Phone Number: " + fields[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
