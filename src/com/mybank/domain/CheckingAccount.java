package com.mybank.domain;

public class CheckingAccount extends Account {

    private double overdraftAmount;

    public CheckingAccount(double initBalance, double overdraftAmount) {
        this.balance = initBalance;
        this.overdraftAmount = overdraftAmount;
    }

    public CheckingAccount(double initBalance) {
        this(initBalance, 0);
    }

    @Override
    public boolean withdraw(double amt) throws OverdraftException {
        if (amt <= balance+overdraftAmount) {
            balance = balance - amt;
            return true;
        }
        throw new OverdraftException(amt-balance-overdraftAmount, "Insufficient funds!");
    }

    public double getOverdraftAmount() {
        return overdraftAmount;
    }
    
    
}
