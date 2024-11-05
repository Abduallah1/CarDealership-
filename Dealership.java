package com.yearup.dealership;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private List<Vehicle> inventory = new ArrayList<>();

    public Vehicle findVehicleByVin(String vin) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getVin().equalsIgnoreCase(vin))
                .findFirst()
                .orElse(null);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public List<Vehicle> getInventory() {
        return inventory;
    }

    public void loadInventory() {
    }
}
