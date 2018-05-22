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
import com.mybank.domain.OverdraftException;
import com.mybank.domain.SavingsAccount;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uladzimir
 */
public class TestAccount {
    public static void main(String[] args) {
        
        Bank bank = Bank.getBank();
        
        Customer firstCustomer = new Customer("John Doe");
        Customer secondCustomer = new Customer("Jane Doe");
        
        SavingsAccount johnSavings = new SavingsAccount(1000, 5);
        CheckingAccount johnAccount = new CheckingAccount(5000, 1000);
        CheckingAccount janeAccount = new CheckingAccount(500, 100);
        
        firstCustomer.addAccount(johnSavings);
        firstCustomer.addAccount(johnAccount);
        secondCustomer.addAccount(janeAccount);
        bank.addCustomer(firstCustomer);
        bank.addCustomer(secondCustomer);
        
        bank.getCustomer(0).getAccount(0).deposit(2000);
        try {
            bank.getCustomer(0).getAccount(1).withdraw(7500);
        } 
        catch (OverdraftException ex) {
            System.out.println(ex.getMessage()+": $"+ex.getDeficit()+"!\n");
        }
        catch (Exception ex) {
            System.out.println("Something went wrong! :-("+ex.getMessage());
        }
        ((SavingsAccount) bank.getCustomer(0).getAccount(0)).addInterestRate();
//        System.out.println("");
        
        displayCustomers(bank);
        
    }

    static void displayCustomers(Bank bank) {
        System.out.println(bank.getCustomer(0));
        System.out.println(bank.getCustomer(1));
    }
}
