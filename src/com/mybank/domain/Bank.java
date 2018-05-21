package com.mybank.domain;

public class Bank {

    private Customer[] customers = new Customer[1000];
    
    private int numOfClients = 0;
    
    private static Bank myBank = new Bank();

    public static Bank getBank() {
        return myBank;
    }

    private Bank() {

    }

    public Customer getCustomer(int customerNumber) {
        if (customerNumber < customers.length) {
            return customers[customerNumber];
        }
        return null;
    }

    public void addCustomer(Customer newCustomer) {
        customers[numOfClients] = newCustomer;
        numOfClients++;
    }

    public int getNumOfClients() {
        return numOfClients;
    }
    
    
}
