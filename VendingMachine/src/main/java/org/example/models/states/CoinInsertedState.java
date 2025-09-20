package org.example.models.states;

import org.example.models.VendingMachine;

public class CoinInsertedState implements IVendingMachineState{

    private final VendingMachine vendingMachine;
    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        vendingMachine.userBalance += amount;
    }

    @Override
    public void selectProduct(String productCode) {
        vendingMachine.selectedProductCode = productCode;
        vendingMachine.currentState = vendingMachine.productSelectedState;
    }

    @Override
    public void dispenseProduct() {
        System.out.println("no product is selected");
    }

    @Override
    public void dispenseChange() {
        System.out.println("cash dispensing: " + vendingMachine.userBalance);
        vendingMachine.userBalance = 0;
        vendingMachine.currentState = vendingMachine.idleState;
        vendingMachine.selectedProductCode = null;

    }
}
