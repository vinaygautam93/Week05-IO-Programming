package org.example.com.day01_csv_data_handling.csv_to_object;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Student {
    String id, name;
    int age;
    double marks;

    public Student(String id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{ID=" + id + ", Name='" + name + "', Age=" + age + ", Marks=" + marks + "}";
    }
}

public class CSVToObject {
    public static void main(String[] args) {
        String filePath = "students.csv";
        List<Student> students = readCSV(filePath);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static List<Student> readCSV(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                students.add(new Student(fields[0], fields[1], Integer.parseInt(fields[2]), Double.parseDouble(fields[3])));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
        return students;
    }
}

