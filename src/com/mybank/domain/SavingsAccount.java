package com.mybank.domain;

public class SavingsAccount extends Account {

    private double interestRate;
    
    public SavingsAccount(double initBalance, double interestRate) {
        this.balance = initBalance;
        this.interestRate = interestRate;
    }
    
    public void addInterestRate() {
        this.balance = this.balance*(1+interestRate);
    }

    public double getInterestRate() {
        return interestRate;
    }
    
    
}
