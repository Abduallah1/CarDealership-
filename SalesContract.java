package com.yearup.dealership;

public class SalesContract extends Contract {
    private static final double SALES_TAX = 0.05;
    private static final double RECORDING_FEE = 100.00;
    private static final double PROCESSING_FEE_UNDER_10K = 295.00;
    private static final double PROCESSING_FEE_ABOVE_10K = 495.00;
    private static final double LOW_INTEREST_RATE = 0.0425;
    private static final double HIGH_INTEREST_RATE = 0.0525;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;
    }

    @Override
    public double getTotalPrice() {
        double basePrice = getVehicle().getPrice();
        double processingFee = basePrice < 10000 ? PROCESSING_FEE_UNDER_10K : PROCESSING_FEE_ABOVE_10K;
        totalPrice = basePrice * (1 + SALES_TAX) + RECORDING_FEE + processingFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0;

        double principal = getTotalPrice();
        int loanTerm = principal >= 10000 ? 48 : 24;
        double interestRate = principal >= 10000 ? LOW_INTEREST_RATE : HIGH_INTEREST_RATE;

        monthlyPayment = (principal * interestRate) / loanTerm;
        return monthlyPayment;
    }
}

