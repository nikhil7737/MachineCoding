package org.example.models.states;

public interface IVendingMachineState {


    void insertCoin(double amount);
    void selectProduct(String productCode);
    void dispenseProduct();
    void dispenseChange();

}
