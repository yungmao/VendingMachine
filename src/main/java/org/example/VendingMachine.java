package org.example;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class represent Vending Machine with its functionality - selling available products.
 */
public class VendingMachine {
    public static Scanner input = new Scanner(System.in);
    private ArrayList<Item> vendingMachine;


    /**
     * Return ArrayList of all products (including ones with amount == 0)
     * @return ArrayList of items
     */
    public ArrayList<Item> getVendingMachine() {
        return vendingMachine;
    }

    /**
     * Constructor reads products from input file and run Vending Machine. After use saves current status of Vending Machine
     * @param input File name of input, as well as output filename
     */
    VendingMachine(String input) {
        this.vendingMachine = FileIO_Handler.readCSV(input);
        runVendingMachine();
        FileIO_Handler.writeCSV(vendingMachine, "save " + input);
    }

    /**
     * Method used to start Vending Machine
     */
    public void runVendingMachine() {
        attemptSelling();
        boolean end_loop = false;
        while (!end_loop) {
            System.out.printf("Do you want to continue(y/n):");
            String option = input.nextLine();
            if (option.equalsIgnoreCase("y")) {
                attemptSelling();
            } else if (option.equalsIgnoreCase("n")) {
                System.out.println("Thank you for using our Vending Machine!");
                end_loop = true;
            }
        }
    }

    /**
     * Method tries to sell product to user.
     */
    public void attemptSelling() {
        printAvailableProducts();
        double money = 0.0;
        try {
            money = insertMoney();
        } catch (Exception e) {

        }
        try {
            System.out.printf("What would you like to buy: ");
            String name_of_product = input.nextLine();
            Item found_item = searchItem(name_of_product);
            BigDecimal change = sellIfCan(found_item, money);
            Change.giveChange(change);

        } catch (Exception e) {

        }
    }

    /**
     * Method used to input money by user
     * @return Inserted money
     * @throws Exception If inserted money is negative or non-numerical throw distinct exception
     */
    public double insertMoney() throws Exception {
        double money = 0.0;
        boolean acceptableAmount = false;
        while (!acceptableAmount) {
            System.out.printf("How much money do you insert?: ");
            try {
                if (input.hasNextDouble()) {
                    money = input.nextDouble();
                } else if (input.hasNext()) {
                    throw new Exceptions_VendingMachine.AmountNotDouble();
                }
                if (money < 0.0) {
                    throw new Exceptions_VendingMachine.NegativeAmount();
                } else {
                    acceptableAmount = true;
                }
            }
            catch (Exception e){

            }
            finally {
                input.nextLine();
            }
        }
        return money;
    }

    /**
     * Method display available products. Meaning that amount is greater than 0.
     */
    public void printAvailableProducts() {
        System.out.println("VENDING MACHINE");
        Map<String, BigDecimal> availableProducts = getVendingMachine().stream()
                .filter(p -> p.getAmount() > 0)
                .collect(Collectors.toMap(Item::getName, Item::getCost));
        for (Map.Entry<String, BigDecimal> entry : availableProducts.entrySet()) {
            BigDecimal price = entry.getValue().setScale(2, RoundingMode.HALF_EVEN);
            System.out.print(entry.getKey() + " cost $" + price + "\n");
        }
    }

    /**
     * Method used to search item by name in available products
     * @param name Searched name of product
     * @return Wanted item if found
     * @throws Exception If item is not in vending machine distinct exception is thrown
     */
    public Item searchItem(String name) throws Exception {
        Optional<Item> searched_item = getVendingMachine().stream()
                .filter(a ->a.getAmount() > 0)
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findAny();
        if (searched_item.isEmpty()) {
            throw new Exceptions_VendingMachine.NoItemException(name);
        }
        return searched_item.get();
    }

    /**
     * Method sells product if all requirements are met.
     * @param item Found item
     * @param money Inserted money by user
     * @return Change from purchase
     * @throws Exception If inserted amount is less than item cost distinct exception is thrown
     */
    public BigDecimal sellIfCan(Item item, double money) throws Exception {
        BigDecimal wanted_item_price = item.getCost();
        BigDecimal customer_money = new BigDecimal(money);
        BigDecimal change = new BigDecimal(0);
        if (wanted_item_price.compareTo(customer_money) == 1) {
            throw new Exceptions_VendingMachine.InsuficientFundsException();
        } else {
            change = customer_money.subtract(wanted_item_price);
            item.setAmount(item.getAmount() - 1);
            AuditLogger.addEvent("Sold " + item.getName());
            System.out.println("Sold " + item.getName());
        }
        return change.setScale(2, RoundingMode.HALF_UP);
    }


}
