package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class represents Coins as values of a dollar with method to display change as the number of coins.
 */
public class Change {

    /**
     * Enum for coins with its values as a fraction of a dollar
     */
    public enum Coins {
        DOLLAR(1), HALF(0.5), QUARTER(0.25),
        DIME(0.1), NICKLE(0.05), PENNY(0.01);
        private final BigDecimal value_cents;

        Coins(double value) {
            this.value_cents = new BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN);
        }

        public BigDecimal getValue() {
            return value_cents.setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    /**
     * Method display change as the number of coins
     * @param change Amount of change to be returned to user
     */
    public static void giveChange(BigDecimal change) {
        if (change.compareTo(new BigDecimal(0.00)) == 0) {
            System.out.printf("No change needed to be returned\n");
        } else {
            System.out.printf("Change:\n");
            BigDecimal amount = new BigDecimal(0);
            BigDecimal rest = change.setScale(2, RoundingMode.HALF_EVEN);
            for (Coins coin : Coins.values()) {
                switch (coin) {
                    case DOLLAR:
                        amount = rest.divide(coin.getValue(), 2, RoundingMode.HALF_EVEN);
                        if (canReturnCoin(amount)) {
                            rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                            System.out.print(amount.intValue() + " dollar coin/s\n");
                        }
                        break;
                    case HALF:
                        amount = rest.divide(coin.getValue(), 2, RoundingMode.HALF_EVEN);
                        if (canReturnCoin(amount)) {
                            rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                            System.out.print(amount.intValue() + " half-dollar coin/s\n");
                        }
                        break;
                    case QUARTER:
                        amount = rest.divide(coin.getValue(), 2, RoundingMode.HALF_EVEN);
                        if (canReturnCoin(amount)) {
                            rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                            System.out.print(amount.intValue() + " quarter/s\n");
                        }
                        break;
                    case DIME:
                        amount = rest.divide(coin.getValue(), 2, RoundingMode.HALF_EVEN);
                        if (canReturnCoin(amount)) {
                            rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                            System.out.print(amount.intValue() + " dime/s\n");
                        }
                        break;
                    case NICKLE:
                        amount = rest.divide(coin.getValue(), 2, RoundingMode.HALF_EVEN);
                        if (canReturnCoin(amount)) {
                            rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                            System.out.print(amount.intValue() + " nickle/s\n");
                        }
                        break;
                    case PENNY:
                        amount = rest.divide(coin.getValue(), 2, RoundingMode.HALF_EVEN);
                        if (canReturnCoin(amount)) {
                            rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                            System.out.print(amount.intValue() + " penny/ies\n");
                        }
                        break;
                }
            }
        }
    }

    /**
     * Method used to decide if coin can be return as a part of change
     * @param amount maximum number of coins that can be returned as a change
     * @return If coin can be part of a change
     */
    public static boolean canReturnCoin(BigDecimal amount) {
        boolean canReturn = false;
        if (amount.compareTo(new BigDecimal(1)) == 0) {
            canReturn = true;
        } else if (amount.compareTo(new BigDecimal(1)) == 1) {
            canReturn = true;
        } else if (amount.compareTo(new BigDecimal(1)) == -1) {
            canReturn = false;
        }
        return canReturn;
    }
}

