package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Representation of a product in Vending Machine
 */
public class Item {
    private String name;
    private BigDecimal cost;
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setCost(double cost) {
        this.cost = new BigDecimal(cost).setScale(2,RoundingMode.HALF_EVEN);
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

    /**
     * Parsing Product to string
     * @return Item as a String
     */
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
