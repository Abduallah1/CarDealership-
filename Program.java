package com.yearup.dealership;

public class Program {
    public static void main(String[] args) {
        Dealership dealership = new Dealership();

        UserInterface ui = new UserInterface(dealership);

        ui.start();
    }
}

