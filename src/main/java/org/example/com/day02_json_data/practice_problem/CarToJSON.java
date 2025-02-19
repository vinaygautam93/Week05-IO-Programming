package org.example.com.day02_json_data.practice_problem;

import com.fasterxml.jackson.databind.ObjectMapper;

class Car {
    public String brand;
    public String model;
    public double price;

    public Car(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }
}

 class CarToJson {
    public static void main(String[] args) {
        try {
            // Create an object of Car
            Car car = new Car("Toyota", "Camry", 30000.0);

            // Convert Java object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(car);

            // Print the JSON string
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
