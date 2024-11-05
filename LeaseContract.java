package com.yearup.dealership;

public class LeaseContract extends Contract {
    private static final double LEASE_RATE = 0.04;
    private static final int LEASE_TERM = 36;
    private double endingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        this.endingValue = vehicle.getPrice() * 0.5;
        this.leaseFee = vehicle.getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        totalPrice = endingValue + leaseFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        double principal = getVehicle().getPrice();
        monthlyPayment = (principal * LEASE_RATE) / LEASE_TERM;
        return monthlyPayment;
    }
}
