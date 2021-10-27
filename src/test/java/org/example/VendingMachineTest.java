package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class VendingMachineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    public void isItemCreated_GoodInput() {
        VendingMachine machine = new VendingMachine("one_good");
        ArrayList<Item> products = machine.getVendingMachine();
        String[] metadata = {"pepsi", "1.7", "10"};
        Item item = new Item(metadata);
        Item item_machine = products.get(0);
        assertEquals(item_machine.toString(), item.toString());
    }

    @Test
    public void isItemCreated_EmptyInput() {
        VendingMachine machine = new VendingMachine("empty");
        ArrayList<Item> products = machine.getVendingMachine();
        assertTrue(products.size() == 0);
    }

    @Test
    public void isAvailableProductDisplayed() {
        VendingMachine machine = new VendingMachine("one_good");
        machine.printAvailableProducts();
        assertEquals("pepsi cost $1.7", outContent.toString().trim());
    }

    @Test
    public void isNotAvailableProductDisplayed() {
        VendingMachine machine = new VendingMachine("empty");
        machine.printAvailableProducts();
        assertEquals("", outContent.toString().trim());
    }

    @Test
    public void isNotAvailableProductInMiddleHidden() {
        VendingMachine machine = new VendingMachine("is_not_is");
        machine.printAvailableProducts();
        assertEquals(outContent.toString().trim(), "pepsi cost $1.7\n" +
                "coke cost $1.8");
    }

    @Test
    public void searchAvailableItem()  {
        VendingMachine machine = new VendingMachine("is_not_is");
        String available_product_name = "coke";
        Item found_item = null;
        try {
            found_item = machine.searchItem(available_product_name);
        } catch (Exception e) {
        }
        assertEquals(available_product_name, found_item.getName());
    }

    @Test(expected = Exception.class)
    public void searchUnavailableItem() throws Exception {
        VendingMachine machine = new VendingMachine("is_not_is");
        String unavailable_product_name = "adaadada";
        Item found_item = machine.searchItem(unavailable_product_name);
    }

}
