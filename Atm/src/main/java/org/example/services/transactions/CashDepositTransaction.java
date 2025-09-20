package org.example.services.transactions;

import org.example.services.Atm;

public class CashDepositTransaction implements ITransaction{

    private final double amount;

    public CashDepositTransaction(double amount) {
        this.amount = amount;
    }


    @Override
    public void execute(Atm atm) {
        if (atm.accountService.depositMoney(atm.currentCard, amount)) {
            System.out.println("success deposit");
        }
        else {
            System.out.println("something went wrong");
        }
    }
}
