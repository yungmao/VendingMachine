package org.example;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class VendingMachine {
    public static Scanner input = new Scanner(System.in);
    private ArrayList<Item> vendingMachine;

    VendingMachine(String input) {
        this.vendingMachine = FileIO_Handler.readCSV(input);
        runVendingMachine();
        FileIO_Handler.writeCSV(vendingMachine, "save " + input);
    }

    public ArrayList<Item> getVendingMachine() {
        return vendingMachine;
    }

    public void printAvailableProducts() {
        Map<String, BigDecimal> availableProducts = getVendingMachine().stream()
                .filter(p -> p.getAmount() > 0)
                .collect(Collectors.toMap(Item::getName, Item::getCost));
        for (Map.Entry<String, BigDecimal> entry : availableProducts.entrySet()) {
            BigDecimal price = entry.getValue().setScale(2, RoundingMode.HALF_EVEN);
            System.out.print(entry.getKey() + " cost $" + price + "\n");
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

    public BigDecimal sellIfCan(Item item, double money) throws Exception {
        BigDecimal wanted_item_price = item.getCost();
        BigDecimal customer_money = new BigDecimal(money);
        BigDecimal change = new BigDecimal(0);
        if (wanted_item_price.compareTo(customer_money) == 1) {
            throw new Exception("Not enough money");
        } else {
            change = customer_money.subtract(wanted_item_price);
            item.setAmount(item.getAmount() - 1);
            System.out.println("Sold " + item.getName());
        }
        return change.setScale(2,RoundingMode.HALF_UP);
    }

    public void buyAvailableProduct() {
        printAvailableProducts();
        System.out.printf("How much money do you insert?: ");
        double money = input.nextDouble();
        input.nextLine();
        System.out.printf("What would you like to buy: ");
        String name_of_product = input.nextLine();
        try {
            Item found_item = searchItem(name_of_product);
            try {
                BigDecimal change = sellIfCan(found_item, money);
                Change.giveChange(change);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                AuditLogger.addEvent(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AuditLogger.addEvent(e.getMessage());
        }
    }

    public void runVendingMachine() {
        while (true) {
            buyAvailableProduct();
            System.out.printf("Do you want to continue(y/n):");
            String option = input.nextLine();
            if (option.equalsIgnoreCase("y")) {
                buyAvailableProduct();
            } else if (option.equalsIgnoreCase("n")) {
                break;
            }
        }
    }
}
