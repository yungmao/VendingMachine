package org.example;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileIO_HandlerTest {
    @Test
    public void createItemFromGoodFile() {
        ArrayList<Item> vendingMachine = FileIO_Handler.readCSV("one_good");
        Item test_Item = new Item();
        test_Item.setName("pepsi");
        test_Item.setCost(1.7);
        test_Item.setAmount(10);
        assertEquals(vendingMachine.get(0).toString(), test_Item.toString());
    }
}