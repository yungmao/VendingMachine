package org.example;

import java.io.*;
import java.util.ArrayList;

/**
 * Used to read/write text file
 */
public class FileIO_Handler {

    private static final String FILEPATH = "src/main/resources/";
    private static final String DELIMITER = ",";

    /**
     * Method reads and parse Products(name, cost, amount) from input file
     * @return ArrayList of all Products parsed from input file
     */
    public static ArrayList<Item> readCSV(String input_name) {
        ArrayList<Item> allItems = new ArrayList<Item>();
        BufferedReader fileReader = null;
        try {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(FILEPATH + input_name));

            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] metadata = line.split(DELIMITER);
                Item item = new Item(metadata);
                allItems.add(item);
            }
        } catch (Exception e) {
            AuditLogger.addEvent("Error: " + e.getMessage());
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
            } catch (IOException e) {
                AuditLogger.addEvent("Error: " + e.getMessage());
            }
        }
        AuditLogger.addEvent("Loaded data from file " + input_name);
        return allItems;
    }


    /**
     * Method save ArrayList of Products in library to csv file.
     * @param allItems ArrayList of all items in Vending Machine
     * @param output_name filename of output text file
     */
    public static void writeCSV(ArrayList<Item> allItems, String output_name) {
        try {
            FileWriter fw = new FileWriter(FILEPATH + output_name);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < allItems.size(); i++) {
                Item item = allItems.get(i);
                String metadata = item.toString();
                bw.write(metadata);
                bw.flush();
            }
            bw.close();
            AuditLogger.addEvent("Data saved to file " + output_name);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            AuditLogger.addEvent("Error: " + e.getMessage());
        } finally {
            AuditLogger.saveAuditLogger();
        }
    }
}

