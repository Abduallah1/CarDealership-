package com.yearup.dealership;

import java.time.LocalDate; // Add this import
import java.util.Scanner;

public class UserInterface {
    private ContractDataManager contractDataManager = new ContractDataManager();
    private Dealership dealership;

    public UserInterface(Dealership dealership) {
        this.dealership = dealership;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add Vehicle\n2. Remove Vehicle\n3. List Vehicles\n4. SELL/LEASE A VEHICLE\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addVehicle(scanner);
                    break;
                case 2:
                    removeVehicle(scanner);
                    break;
                case 3:
                    listVehicles();
                    break;
                case 4:
                    handleSellOrLease(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void addVehicle(Scanner scanner) {
        System.out.print("Enter VIN: ");
        String vin = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Manufacture Year (YYYY-MM-DD): ");
        String manufactureDate = scanner.nextLine();  // consume newline
        Vehicle vehicle = new Vehicle(vin, price, LocalDate.parse(manufactureDate));
        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added.");
    }

    private void removeVehicle(Scanner scanner) {
        System.out.print("Enter VIN to remove: ");
        String vin = scanner.nextLine();
        Vehicle vehicle = dealership.findVehicleByVin(vin);
        if (vehicle != null) {
            dealership.removeVehicle(vehicle);
            System.out.println("Vehicle removed.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void listVehicles() {
        if (dealership.getInventory().isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            for (Vehicle vehicle : dealership.getInventory()) {
                System.out.println("VIN: " + vehicle.getVin() + ", Price: " + vehicle.getPrice() + ", Age: " + vehicle.getAge() + " years");
            }
        }
    }

    private void handleSellOrLease(Scanner scanner) {
        System.out.print("Enter VIN: ");
        String vin = scanner.nextLine();
        Vehicle vehicle = dealership.findVehicleByVin(vin);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Customer Email: ");
        String email = scanner.nextLine();

        System.out.print("Is this a sale or lease? (SALE/LEASE): ");
        String contractType = scanner.nextLine().toUpperCase();

        Contract contract;
        if (contractType.equals("SALE")) {
            System.out.print("Finance? (yes/no): ");
            boolean finance = scanner.nextLine().equalsIgnoreCase("yes");
            contract = new SalesContract("20241105", name, email, vehicle, finance);
        } else if (contractType.equals("LEASE")) {
            if (vehicle.getAge() > 3) {
                System.out.println("Cannot lease vehicles older than 3 years.");
                return;
            }
            contract = new LeaseContract("20241105", name, email, vehicle);
        } else {
            System.out.println("Invalid option.");
            return;
        }

        contractDataManager.saveContract(contract);
        dealership.removeVehicle(vehicle);
        System.out.println(contractType + " contract created and saved.");
    }
}
