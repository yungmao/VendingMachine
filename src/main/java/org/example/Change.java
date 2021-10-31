package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

public class Change {

    /**
     * Enum for coins with its value in cents
     */
    public enum Coins {
        DOLLAR(1), HALF(0.5), QUARTER(0.25),
        DIME(0.1), NICKLE(0.05), PENNY(0.01);
        private final BigDecimal value_cents;

        Coins(double value) {
            this.value_cents = new BigDecimal(value);
        }

        public BigDecimal getValue() {
            return value_cents.setScale(2,RoundingMode.HALF_EVEN);
        }
    }

    public static void giveChange(BigDecimal change) {
        BigDecimal amount = new BigDecimal(0);
        BigDecimal rest = change.setScale(2,RoundingMode.HALF_EVEN);
        for (Coins coin : Coins.values()) {
            switch (coin) {
                case DOLLAR:
                    amount = rest.divide(coin.getValue(),2, RoundingMode.HALF_EVEN);
                    if (canReturnCoin(amount)) {
                        rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                        System.out.print(amount.intValue() + " dollar coin/s\n");
                    }
                    break;
                case HALF:
                    amount = rest.divide(coin.getValue(),2, RoundingMode.HALF_EVEN);
                    if (canReturnCoin(amount)) {
                        rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                        System.out.print(amount.intValue() + " half-dollar coin/s\n");
                    }
                    break;
                case QUARTER:
                    amount = rest.divide(coin.getValue(),2, RoundingMode.HALF_EVEN);
                    if (canReturnCoin(amount)) {
                        rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                        System.out.print(amount.intValue() + " quarter/s\n");
                    }
                    break;
                case DIME:
                    amount = rest.divide(coin.getValue(),2, RoundingMode.HALF_EVEN);
                    if (canReturnCoin(amount)) {
                        rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                        System.out.print(amount.intValue() + " dime/s\n");
                    }
                    break;
                case NICKLE:
                    amount =rest.divide(coin.getValue(),2, RoundingMode.HALF_EVEN);
                    if (canReturnCoin(amount)) {
                        rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                        System.out.print(amount.intValue() + " nickle/s\n");
                    }
                    break;
                case PENNY:
                    amount = rest.divide(coin.getValue(),2, RoundingMode.HALF_EVEN);
                    if (canReturnCoin(amount)) {
                        rest = rest.remainder(coin.getValue()).setScale(2, RoundingMode.HALF_EVEN);
                        System.out.print(amount.intValue() + " penny/ies\n");
                    }
                    break;
            }
        }
    }

    public  static  boolean canReturnCoin(BigDecimal amount){
        boolean canReturn = false;
        if(amount.compareTo(new BigDecimal(1))==0){
            canReturn=true;
        }
        else if (amount.compareTo(new BigDecimal(1))==1){
            canReturn=true;
        }
        else if (amount.compareTo(new BigDecimal(1))==-1){
            canReturn=false;
        }
        return canReturn;
    }
}

