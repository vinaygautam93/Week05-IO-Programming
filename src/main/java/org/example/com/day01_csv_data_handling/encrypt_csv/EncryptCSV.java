package org.example.com.day01_csv_data_handling.encrypt_csv;


import java.io.*;
import java.util.Base64;

public class EncryptCSV {
    public static void main(String[] args) {
        String inputFile = "employees.csv";
        String encryptedFile = "employees_encrypted.csv";
        String decryptedFile = "employees_decrypted.csv";

        encryptCSV(inputFile, encryptedFile);
        decryptCSV(encryptedFile, decryptedFile);
    }

    // Method to encrypt sensitive data in CSV file
    public static void encryptCSV(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String header = br.readLine();
            bw.write(header); // Write the header as it is
            bw.newLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                // Encrypt Salary and Email fields (assuming they are at index 3 and 4)
                fields[3] = encrypt(fields[3]);
                fields[4] = encrypt(fields[4]);

                // Write encrypted data
                bw.write(String.join(",", fields));
                bw.newLine();
            }

            System.out.println("CSV encrypted successfully!");

        } catch (IOException e) {
            System.err.println("Error encrypting CSV: " + e.getMessage());
        }
    }

    // Method to decrypt encrypted CSV file
    public static void decryptCSV(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String header = br.readLine();
            bw.write(header);
            bw.newLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                // Decrypt Salary and Email fields
                fields[3] = decrypt(fields[3]);
                fields[4] = decrypt(fields[4]);

                bw.write(String.join(",", fields));
                bw.newLine();
            }

            System.out.println("CSV decrypted successfully!");

        } catch (IOException e) {
            System.err.println("Error decrypting CSV: " + e.getMessage());
        }
    }

    // Encryption method using Base64 encoding
    public static String encrypt(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    // Decryption method using Base64 decoding
    public static String decrypt(String data) {
        return new String(Base64.getDecoder().decode(data));
    }
}