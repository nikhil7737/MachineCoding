package org.example.services;

import org.example.models.Account;
import org.example.models.Card;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private Map<String, Account> accounts = new HashMap<>();


    public void addAccount(Account account) {
        String cardNumber = account.card.cardNumber;
        if (accounts.containsKey(cardNumber)) {
            System.out.print("account already exists");
        }
        else {
            accounts.put(cardNumber, account);
        }
    }

    public boolean withdrawMoney(Card card, Double amount) {
        return addBalance(card, -amount);
    }

    public boolean depositMoney(Card card, Double amount) {
        return addBalance(card, amount);
    }

    private boolean addBalance(Card card, Double amount) {
        validateCard(card);
        Account account = accounts.get(card.cardNumber);
        account.lock.lock();
        boolean isSuccess = false;
        try {
            if (account.balance + amount >= 0) {
                account.balance += amount;
                isSuccess = true;
            }
        }
        finally {
            account.lock.unlock();
        }
        return isSuccess;
    }

    private void validateCard(Card card) {
        if (!accounts.containsKey(card.cardNumber)) {
            throw new IllegalArgumentException("invalid card");
        }
    }

    public boolean verifyPin(Card card, String pin) {
        validateCard(card);
        return accounts.get(card.cardNumber).pin.equals(pin);
    }

    public double getBalance(Card card) {
        validateCard(card);
        return accounts.get(card.cardNumber).balance;
    }


}
