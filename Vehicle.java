package com.yearup.dealership;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Vehicle {
    private String vin;
    private double price;
    private LocalDate manufactureDate;

    public Vehicle(String vin, double price, LocalDate manufactureDate) {
        this.vin = vin;
        this.price = price;
        this.manufactureDate = manufactureDate;
    }

    public String getVin() {
        return vin;
    }

    public double getPrice() {
        return price;
    }

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(manufactureDate, LocalDate.now());
    }
}
