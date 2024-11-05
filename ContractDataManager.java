package com.yearup.dealership;

import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private static final String CONTRACTS_FILE = "contracts.txt";

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(CONTRACTS_FILE, true)) {
            String contractData = formatContractData(contract);
            writer.write(contractData + "\n");
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }

    private String formatContractData(Contract contract) {
        if (contract instanceof SalesContract) {
            SalesContract salesContract = (SalesContract) contract;
            return "SALE|" + salesContract.getDate() + "|" + salesContract.getCustomerName() + "|"
                    + salesContract.getCustomerEmail() + "|" + salesContract.getVehicle().getVin() + "|"
                    + salesContract.getTotalPrice() + "|" + salesContract.getMonthlyPayment();
        } else if (contract instanceof LeaseContract) {
            LeaseContract leaseContract = (LeaseContract) contract;
            return "LEASE|" + leaseContract.getDate() + "|" + leaseContract.getCustomerName() + "|"
                    + leaseContract.getCustomerEmail() + "|" + leaseContract.getVehicle().getVin() + "|"
                    + leaseContract.getTotalPrice() + "|" + leaseContract.getMonthlyPayment();
        } else {
            return "";
        }
    }
}

