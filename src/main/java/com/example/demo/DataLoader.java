package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @Override
    public void run(String... strings) throws Exception {
        Set<Car> cars = new HashSet<>();

        Category category = new Category();
        category.setName("Truck");

        Car car = new Car();
        car.setMake("Honda");
        car.setModel("Ridgeline");
        car.setYear(2020);
        car.setCategory(category);
        cars.add(car);

        category.setCars(cars);
        categoryRepository.save(category);
        carRepository.save(car);


    }
}
