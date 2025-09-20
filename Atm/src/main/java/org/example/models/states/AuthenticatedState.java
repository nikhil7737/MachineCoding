package org.example.models.states;

import org.example.services.Atm;
import org.example.services.transactions.ITransaction;

public class AuthenticatedState extends AtmState {
    public AuthenticatedState(Atm atm) {
        super(AuthenticatedState.class.getName(), atm);
    }

    public void executeTransaction(ITransaction transaction) {
        transaction.execute(atm);
    }

    public void ejectCard() {
        atm.currentCard = null;
        atm.currentState = atm.atmStateFactory.getIdleState();
        System.out.println("card ejected successfully");

    }


}
