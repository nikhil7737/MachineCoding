package org.example.models.states;

import org.example.services.Atm;
import org.example.models.Card;
import org.example.services.transactions.ITransaction;

public abstract class AtmState {

    public final String name;
    public final Atm atm;


    public AtmState(String name, Atm atm) {
        this.name = name;
        this.atm = atm;
    }

    public void insertCard(Card card) {
        System.out.println("insertCard is not applicable in " + this.name);
    }

    public void insertPin(String pin) {
        System.out.println("insertPin is not applicable in " + this.name);
    }

    public void executeTransaction(ITransaction transaction) {
        System.out.println("executeTransaction is not applicable in " + this.name);
    }

    public void ejectCard() {
        System.out.println("ejectCard is not applicable in " + this.name);
    }

}
