package org.example.services;

import org.example.models.Inventory;
import org.example.models.Product;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    Map<String, Inventory> inventoryMap = Map.of(
            "p1", new Inventory(new Product("p1", 100), 5),
            "p2", new Inventory(new Product("p2", 200), 5),
            "p3", new Inventory(new Product("p3", 300), 2)
    );


    public Product getProduct(String shelfCode) {
        validateShelfCode(shelfCode);
        return inventoryMap.get(shelfCode).product;
    }

    private void validateShelfCode(String shelfCode) {
        if (!inventoryMap.containsKey(shelfCode)) {
            throw new IllegalArgumentException("invalid shelf code");
        }
    }

    public boolean tryDeductingInventory(String shelfCode) {
        validateShelfCode(shelfCode);
        if (inventoryMap.get(shelfCode).quantity > 0) {
            inventoryMap.get(shelfCode).quantity--;
            return true;
        }
        else {
            return false;
        }
    }

    public void addInventory(String shelfCode, int quantity) {
        validateShelfCode(shelfCode);
        inventoryMap.get(shelfCode).quantity += quantity;
    }


}
