package org.example.services.transactions;

import org.example.services.Atm;

public class BalanceEnquiryTransaction implements ITransaction {
    @Override
    public void execute(Atm atm) {
        System.out.println("balance is: " + atm.accountService.getBalance(atm.currentCard));
    }
}
