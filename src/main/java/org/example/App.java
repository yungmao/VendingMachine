package org.example;


import java.util.Scanner;

/**
 * Main class used to instantiate Vending Machine object
 */
public class App {
    public static Scanner input = new Scanner(System.in);
    /**
     * Method instantiate Vending Machine object from inserted input
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        String filename = input.nextLine();
        VendingMachine vm = new VendingMachine(filename);
    }
}
