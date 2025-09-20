package org.example.services;

import org.example.models.Card;
import org.example.models.states.AtmState;
import org.example.models.states.IdleState;
import org.example.services.transactions.ITransaction;

public class Atm {

    public AtmState currentState;
    public Card currentCard;
    public final AtmStateFactory atmStateFactory;
    public final AccountService accountService;


    public Atm(AccountService accountService) {
        this.accountService = accountService;
        currentState = new IdleState(this);
        currentCard = null;
        atmStateFactory = new AtmStateFactory(this);
    }


    public void dispenseCash(double amount) {

    }


    public void insertCard(Card card) {
        currentState.insertCard(card);
    }

    public void insertPin(String pin) {
        currentState.insertPin(pin);
    }

    public void executeTransaction(ITransaction transaction) {
        currentState.executeTransaction(transaction);
    }

    public void ejectCard() {
        currentState.ejectCard();
    }

}
