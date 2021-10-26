package org.example;

import java.io.*;
import java.util.ArrayList;

public class FileIO_Handler {


    private static final String FILEPATH = "src/main/resources/product_list.tx";
    private static final String DELIMITER = ",";


    /**
     * Method reads and parse DVDs metadata from input file
     * @return ArrayList of all DVDs parsed from input file
     */
    public static ArrayList<Item> readCSV(){
        ArrayList<Item> allItems = new ArrayList<Item>();
        BufferedReader fileReader = null;
        try
        {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(FILEPATH));

            //Read the file line by line
            while ((line = fileReader.readLine()) != null)
            {
                //Get all tokens available in line
                String[] metadata = line.split(DELIMITER);
                Item item = new Item();
//                allDVDs.add(dvd);
            }
        }
        catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally
        {
            try {
                assert fileReader != null;
                fileReader.close();
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
        return allItems;
    }

    /**
     * Method save ArrayList of DVDs in library to csv file
     * @param
     * @param
     */
    public static void writeCSV(ArrayList<Item> allItems){
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < allItems.size(); i++) {
                Item dvd = allItems.get(i);
                String metadata = dvd.toString();
                bw.write(metadata + "\n");
                bw.flush();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}

