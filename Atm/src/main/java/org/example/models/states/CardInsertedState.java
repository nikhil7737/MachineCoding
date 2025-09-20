package org.example.models.states;

import org.example.services.Atm;

public class CardInsertedState extends AtmState {
    public CardInsertedState(Atm atm) {
        super(CardInsertedState.class.getName(), atm);
    }

    public void insertPin(String pin) {
        boolean isCorrectPin = atm.accountService.verifyPin(atm.currentCard, pin);
        if (isCorrectPin) {
            System.out.println("auth success");
            atm.currentState = atm.atmStateFactory.getAuthenticatedState();
        }
        else {
            System.out.println("invalid pin. try again");
        }
    }

    public void ejectCard() {
        atm.currentCard = null;
        atm.currentState = atm.atmStateFactory.getIdleState();
        System.out.println("card ejected successfully");
    }

}
