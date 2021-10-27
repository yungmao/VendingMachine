package org.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class VendingMachine {
    private ArrayList<Item> vendingMachine;

    VendingMachine(String input) {
        this.vendingMachine = FileIO_Handler.readCSV(input);
    }

    public ArrayList<Item> getVendingMachine() {
        return vendingMachine;
    }

    public void printProducts() {
        Map<String, Double> availableProducts = getVendingMachine().stream()
                .filter(p -> p.getAmount() > 0)
                .collect(Collectors.toMap(Item::getName, Item::getCost));
        for (Map.Entry<String, Double> entry : availableProducts.entrySet()) {
            System.out.print(entry.getKey() + " cost $" + entry.getValue().toString()+"\n");
        }
    }
}
