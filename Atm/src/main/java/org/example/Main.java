package org.example;

import org.example.models.Account;
import org.example.models.Card;
import org.example.services.AccountService;
import org.example.services.Atm;
import org.example.services.transactions.BalanceEnquiryTransaction;
import org.example.services.transactions.CashDepositTransaction;
import org.example.services.transactions.CashWithdrawTransaction;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        Card card1 = new Card("card1");
        Card card2 = new Card("card2");
        Card card3 = new Card("card3");

        AccountService accountService = new AccountService();
        accountService.addAccount(new Account(card1, "pin1"));
        accountService.addAccount(new Account(new Card("card2"), "pin2"));
        accountService.addAccount(new Account(new Card("card3"), "pin3"));


        Atm atm = new Atm(accountService);

        atm.insertCard(card1);
        atm.insertPin("pin2");

        atm.insertPin("pin1");
        atm.executeTransaction(new CashDepositTransaction(100));
        atm.executeTransaction(new CashDepositTransaction(200));
        atm.executeTransaction(new BalanceEnquiryTransaction());
        atm.executeTransaction(new CashWithdrawTransaction(150));
        atm.ejectCard();

        atm.insertPin("342");
        atm.executeTransaction(new BalanceEnquiryTransaction());

    }
}