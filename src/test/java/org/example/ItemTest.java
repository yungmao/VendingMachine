package org.example;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void isItemCreated_GoodInput() {
        String[] metadata = {"pepsi", "2.5", "10"};
        Item item2 = new Item(metadata);
        Item item1 = new Item();
        item1.setName("pepsi");
        item1.setCost(2.5);
        item1.setAmount(10);
        assertEquals(item1.toString(), item2.toString());
    }

    @Test
    public void isItemCreated_EmptyInput() {
        String[] metadata = {};
        Item item1 = new Item(metadata);
        Item item2 = new Item();
        assertEquals(item1.toString(), item2.toString());
    }

}