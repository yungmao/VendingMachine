package org.example;

import java.util.Scanner;

/**
 * Main class used to instantiate Vending Machine object
 */
public class App {

    /**
     * Method instantiate Vending Machine object from inserted input
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        /*
         * Default filename = product_list
         * User can uncomment part of code and input filename in console
         * Files to try:
         * empty - Empty Vending Machine
         * is_not_is - Vending Machine have 2 Available products{1st,3rd} and Unavailable{2nd}
         * one_good - Just one good
         * product_list - First 5 are available last one out of stock
         * */


         /*public static Scanner input = new Scanner(System.in);
         System.out.printf("File name: ");
         String filename = input.nextLine();*/


        String filename = "product_list";
        VendingMachine vm = new VendingMachine(filename);
    }
}
