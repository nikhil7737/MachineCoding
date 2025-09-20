package org.example;

import org.example.models.VendingMachine;
import org.example.services.InventoryService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        InventoryService inventoryService = new InventoryService();
        VendingMachine vendingMachine = new VendingMachine(inventoryService);

        vendingMachine.selectProduct("p1"); //fail

        vendingMachine.insertCoin(50);

        //add validation in selectProduct
        vendingMachine.selectProduct("p1");
        vendingMachine.dispenseProduct();

        vendingMachine.insertCoin(500);
        vendingMachine.selectProduct("p2");
        vendingMachine.dispenseProduct();
        vendingMachine.dispenseCash();

    }
}