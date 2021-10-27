package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Item {
    private String name;
    private double cost; //TODO Change to BigDecimal
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    Item() {
    }

    Item(String[] metadata) {
        try {
            setName(metadata[0]);
            double _cost = Double.parseDouble(metadata[1]);
            setCost(_cost);
            int _amount = Integer.parseInt(metadata[2]);
            setAmount(_amount);
        } catch (Exception e) {

        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(",");
        sb.append(this.cost);
        sb.append(",");
        sb.append(this.amount);
        sb.append("\n");
        return sb.toString();
    }
}
