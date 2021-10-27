package org.example;


import java.util.*;
import java.util.stream.Collectors;

public class VendingMachine {
    public static Scanner input = new Scanner(System.in);
    private ArrayList<Item> vendingMachine;

    VendingMachine(String input) {
        this.vendingMachine = FileIO_Handler.readCSV(input);
        buyAvailableProduct();
        FileIO_Handler.writeCSV(vendingMachine,"save "+input);
    }

    public ArrayList<Item> getVendingMachine() {
        return vendingMachine;
    }

    public void printAvailableProducts() {
        Map<String, Double> availableProducts = getVendingMachine().stream()
                .filter(p -> p.getAmount() > 0)
                .collect(Collectors.toMap(Item::getName, Item::getCost));
        for (Map.Entry<String, Double> entry : availableProducts.entrySet()) {
            System.out.print(entry.getKey() + " cost $" + entry.getValue().toString() + "\n");
        }
    }

    public Item searchItem(String name) throws Exception {
        Optional<Item> searched_item = getVendingMachine().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findAny();
        if (searched_item.isEmpty()) {
            throw new Exception("No product found");
        }
        return searched_item.get();
    }

    public double sellIfCan(Item item, int customer_money) throws Exception {
        double wanted_item_price = item.getCost();
        double change = 0;
        if (wanted_item_price > customer_money) {
            throw new Exception("Not enough money");
        } else {
            change = customer_money - wanted_item_price;
            item.setAmount(item.getAmount()-1);
        }
        return change;
    }

    public void buyAvailableProduct() {
        printAvailableProducts();
        System.out.printf("How much money do you insert?: ");
        int money = input.nextInt();
        input.nextLine();
        System.out.println("What would you like to buy: ");
        String name_of_product = input.nextLine();
        try {
            Item found_item = searchItem(name_of_product);
            try {
                sellIfCan(found_item, money);
            } catch (Exception e) {
                AuditLogger.addEvent(e.getMessage());
            }
        } catch (Exception e) {
            AuditLogger.addEvent(e.getMessage());
        }


    }
}
