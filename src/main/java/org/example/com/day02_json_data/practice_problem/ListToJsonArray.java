package org.example.com.day02_json_data.practice_problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;

class Car {
    private String brand;
    private String model;
    private int year;

    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Getters (Needed for Jackson to serialize the object)
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
}

public class ListToJsonArray {
    public static void main(String[] args) {
        try {
            // Create a list of Car objects
            List<Car> cars = new ArrayList<>();
            cars.add(new Car("Toyota", "Camry", 2020));
            cars.add(new Car("Honda", "Civic", 2019));
            cars.add(new Car("Ford", "Mustang", 2021));

            // Convert list to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writeValueAsString(cars);

            // Print the JSON array
            System.out.println(jsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
