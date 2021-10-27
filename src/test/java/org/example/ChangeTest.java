package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ChangeTest {
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
    public void giveChange30cents() {
        Change.giveChange(30);
        assertEquals("1 quarter/s\n" + "1 nickle/s", outContent.toString().trim());
    }

    @Test
    public void giveChange150cents() {
        Change.giveChange(150);
        assertEquals("1 dollar coin/s\n" + "1 half-dollar coin/s", outContent.toString().trim());
    }

    @Test
    public void giveNoChange() {
        Change.giveChange(0);
        assertEquals("", outContent.toString().trim());
    }

    @Test
    public void giveChange2cents() {
        Change.giveChange(3);
        assertEquals("3 penny/ies", outContent.toString().trim());
    }

    @Test
    public void giveChange24cents() {
        Change.giveChange(24);
        assertEquals("2 dime/s\n4 penny/ies", outContent.toString().trim());
    }

}