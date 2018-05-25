package com.mybank.domain;

public class AccumulateSavingsBatch {

  public AccumulateSavingsBatch() {
  }

  public void doBatch() {

      Bank bank = Bank.getBank();
    // For each customer...
    for ( int cust_idx = 0;
          cust_idx < bank.getNumOfClients();
          cust_idx++ ) {
      Customer customer = bank.getCustomer(cust_idx);

      // For each account for this customer...
      for ( int acct_idx = 0;
            acct_idx < customer.getNumOfAccounts();
            acct_idx++ ) {
        Account account = customer.getAccount(acct_idx);
        String  account_type = "";

        // Determine the account type
        if ( account instanceof SavingsAccount ) {
          SavingsAccount savings = (SavingsAccount) account;
	  savings.addInterestRate();
        } else {
          // ignore all other account types
        }
      }
    }
  }
}
