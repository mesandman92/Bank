package com.mybank.domain;

import java.util.ArrayList;

public class Customer {

    private ArrayList<Account> accounts;

    private String firstName;
    private String lastName;

    private int customerNumber;

    private static int customerNumberBase = 1000;

    private int numOfAccounts;

    public Customer(String fullName, String lastName) {
        accounts = new ArrayList<>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumberBase++;
        this.numOfAccounts = 0;
    }

    @Override
    public String toString() {
        String s = "Customer:" + firstName + ","+ lastName + ", customerNumber=" + customerNumber + ", numOfAccounts=" + numOfAccounts + '}';
        for (int i = 0; i < this.numOfAccounts; i++) {
            Account acc = getAccount(i);
            if (acc instanceof SavingsAccount) {
                s = s + "\n\t"+(i+1)+" Savings account with interest rate %" + ((SavingsAccount) acc).getInterestRate();
            } else {
                s = s + "\n\t"+(i+1)+" Checking account with overdraft %" + ((CheckingAccount) acc).getOverdraftAmount();
            }
            s = s + ", balance $" + acc.getBalance();
        }
        s=s+"\n";
        return s;
    }

    public Account getAccount(int accountNumber) {
        if (accountNumber <= accounts.size() && numOfAccounts != 0) {
            return accounts.get(accountNumber);
        }
        return null;
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
        numOfAccounts++;
    }
}
