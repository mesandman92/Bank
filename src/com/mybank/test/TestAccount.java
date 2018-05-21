/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.test;

import com.mybank.domain.Account;
import com.mybank.domain.Bank;
import com.mybank.domain.CheckingAccount;
import com.mybank.domain.Customer;
import com.mybank.domain.SavingsAccount;

/**
 *
 * @author uladzimir
 */
public class TestAccount {
    public static void main(String[] args) {
        
        Customer firstCustomer = new Customer("John Doe");
        Customer secondCustomer = new Customer("Jane Doe");
        
        SavingsAccount johnSavings = new SavingsAccount(1000, 5);
        CheckingAccount johnAccount = new CheckingAccount(5000, 500);
        CheckingAccount janeAccount = new CheckingAccount(500, 100);
        
        firstCustomer.addAccount(johnSavings);
        firstCustomer.addAccount(johnAccount);
        secondCustomer.addAccount(janeAccount);
        Bank.addCustomer(firstCustomer);
        Bank.addCustomer(secondCustomer);
        
        Bank.getCustomer(0).getAccount(0).deposit(2000);
        Bank.getCustomer(0).getAccount(1).withdraw(5500);
        ((SavingsAccount) Bank.getCustomer(0).getAccount(0)).addInterestRate();
        System.out.println("");
        
        System.out.println(Bank.getCustomer(0));
        System.out.println(Bank.getCustomer(1));
        
        
    }
}
