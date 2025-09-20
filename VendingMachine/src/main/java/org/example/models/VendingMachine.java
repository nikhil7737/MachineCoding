package org.example.models;

import org.example.models.states.CoinInsertedState;
import org.example.models.states.IVendingMachineState;
import org.example.models.states.IdleState;
import org.example.models.states.ProductSelectedState;
import org.example.services.InventoryService;

public class VendingMachine {

    public IVendingMachineState idleState = new IdleState(this);
    public IVendingMachineState coinInsertedState = new CoinInsertedState(this);
    public IVendingMachineState productSelectedState = new ProductSelectedState(this);
    public IVendingMachineState currentState = idleState;

    public final InventoryService inventoryService;

    public double userBalance = 0;
    public String selectedProductCode = null;

    public VendingMachine(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void insertCoin(double amount) {
        currentState.insertCoin(amount);
    }


    public void selectProduct(String productCode) {
        currentState.selectProduct(productCode);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void dispenseCash() {
        currentState.dispenseChange();
    }

    public boolean tryDispensingProduct() {

        double selectedProductPrice = inventoryService.getProduct(selectedProductCode).price;
        if (selectedProductPrice <= userBalance && inventoryService.tryDeductingInventory(selectedProductCode)) {
            userBalance -= selectedProductPrice;
            return true;
        }
        return false;

    }
}