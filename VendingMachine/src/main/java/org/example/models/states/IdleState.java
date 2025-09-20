package org.example.models.states;

import org.example.models.VendingMachine;

public class IdleState implements IVendingMachineState {

    private final VendingMachine vendingMachine;
    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        vendingMachine.userBalance += amount;
        vendingMachine.currentState = vendingMachine.coinInsertedState;
    }

    @Override
    public void selectProduct(String productCode) {
        System.out.println("selectProduct not applicable in IdleState");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("dispenseProduct not applicable in IdleState");
    }

    @Override
    public void dispenseChange() {
        System.out.println("dispenseChange not applicable in IdleState");
    }
}
