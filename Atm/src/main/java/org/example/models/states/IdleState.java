package org.example.models.states;

import org.example.services.Atm;
import org.example.models.Card;

public class IdleState extends AtmState {
    public IdleState(Atm atm) {
        super(IdleState.class.getName(), atm);
    }

    @Override
    public void insertCard(Card card) {
        atm.currentCard = card;
        atm.currentState = atm.atmStateFactory.getCardInsertedState();
    }

}
