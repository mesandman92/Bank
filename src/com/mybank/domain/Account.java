/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.domain;

/**
 * Bank Account class
 * @author uladzimir
 */
public class Account {
    protected double balance;
    
    /**
     * Provides initial balance
     * @param balance must be positive balance
     */
    protected Account(double balance) {
        if (balance >= 0) {
        this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public Account() {
        this.balance = 0;
    }
       
    /**
     * Method to add money to account
     * @param amt a positive amount of money
     * @return 
     */
    public boolean deposit(double amt) {
        if(amt > 0) {
            balance += amt;
            return true;
        }
        return false;
    }
    /**
     * Method to withdraw money
     * @param amt a positive amount of money
     * @return 
     */
    public boolean withdraw (double amt) throws Exception {
        if (amt <= balance) {
            balance -= amt;
            return true;
        }
        return false;
    }

    /**
     * Method to check account balance
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

}
