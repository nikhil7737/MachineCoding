package org.example.services.transactions;

import org.example.models.Card;
import org.example.services.AccountService;
import org.example.services.Atm;

public class CashWithdrawTransaction implements ITransaction{

    private final double amount;

    public CashWithdrawTransaction(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Atm atm) {
        if (atm.accountService.withdrawMoney(atm.currentCard, amount)) {
            System.out.println("dispensing amount: " + amount);
            atm.dispenseCash(amount);
        }
        else {
            System.out.println("insufficient funds");
        }
    }
}
