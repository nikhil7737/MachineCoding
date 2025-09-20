package org.example.models.states;

import org.example.models.VendingMachine;

public class ProductSelectedState implements IVendingMachineState{

    private final VendingMachine vendingMachine;
    public ProductSelectedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        vendingMachine.userBalance += amount;
    }

    @Override
    public void selectProduct(String productCode) {
        vendingMachine.selectedProductCode = productCode;
    }

    @Override
    public void dispenseProduct() {
        boolean success = vendingMachine.tryDispensingProduct();
        if (success) {
            System.out.println("product dispensed: " + vendingMachine.selectedProductCode);
        } else {
            System.out.println("not enough balance or inventory");
        }

    }

    @Override
    public void dispenseChange() {
        System.out.println("cash dispensing: " + vendingMachine.userBalance);
        vendingMachine.userBalance = 0;
        vendingMachine.currentState = vendingMachine.idleState;
        vendingMachine.selectedProductCode = null;
    }
}
