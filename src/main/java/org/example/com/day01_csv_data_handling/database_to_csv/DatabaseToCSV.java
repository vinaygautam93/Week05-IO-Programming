package org.example.com.day01_csv_data_handling.database_to_csv;


import java.io.*;
import java.sql.*;

public class DatabaseToCSV {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/company";
        String username = "root";
        String password = "password";
        String filePath = "employees_report.csv";

        exportToCSV(jdbcURL, username, password, filePath);
    }

    public static void exportToCSV(String jdbcURL, String username, String password, String filePath) {
        String query = "SELECT * FROM employees";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            bw.write("Employee ID, Name, Department, Salary");
            bw.newLine();

            while (rs.next()) {
                bw.write(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getDouble(4));
                bw.newLine();
            }

            System.out.println("CSV Report Generated!");

        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}



