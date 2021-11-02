package org.example;

import java.math.BigDecimal;

/**
 * Super class consisting of distinct Exceptions in Vending Machine App
 */
public class Exceptions_VendingMachine extends Exception {

    /**
     * Exception used to handle case of a wanted item being more expensive than amount inserted by user
     */
    public static class InsuficientFundsException extends Exceptions_VendingMachine {
        InsuficientFundsException(BigDecimal inserted_money) {
            System.out.println("Error: Insuficient funds");
            System.out.println("Transaction canceled $" + inserted_money.toString() +" returned");
            AuditLogger.addEvent("Error: Insuficient funds");
        }
    }

    /**
     * Exception used to handle case when user wants to buy item that is not in the Vending Machine: no amount or no product
     */
    public static class NoItemException extends Exceptions_VendingMachine {
        NoItemException(String item) {
            System.out.println("Error: No " + item + " in Vending Machine");
            AuditLogger.addEvent("Error: No " + item + " in Vending Machine");
        }
    }

    /**
     * Exception used to handle case when user tries to input negative amount of money
     */
    public static class NegativeAmount extends Exceptions_VendingMachine {
        NegativeAmount() {
            AuditLogger.addEvent("Error: Negative Amount Inserted");
            System.out.println("Can't insert negative sum of money");
        }
    }

    /**
     * Exception used to handle case when user inputs non-numerical value as money
     */
    public static class AmountNotDouble extends Exceptions_VendingMachine {
        AmountNotDouble() {
            AuditLogger.addEvent("Error: Invalid type of money input");
            System.out.println("Invalid money input type");
        }
    }
}
