package org.example.services;

import org.example.models.states.AtmState;
import org.example.models.states.AuthenticatedState;
import org.example.models.states.CardInsertedState;
import org.example.models.states.IdleState;

public class AtmStateFactory {

    private final AtmState idleState;
    private final AtmState cardInsertedState;
    private final AtmState authenticatedState;


    public AtmStateFactory(Atm atm) {
        this.idleState = new IdleState(atm);
        this.cardInsertedState = new CardInsertedState(atm);
        this.authenticatedState = new AuthenticatedState(atm);
    }

    public AtmState getIdleState() {
        return this.idleState;
    }

    public AtmState getCardInsertedState() {
        return this.cardInsertedState;
    }

    public AtmState getAuthenticatedState() {
        return this.authenticatedState;
    }

}
