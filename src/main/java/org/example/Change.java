package org.example;

public class Change {

    /**
     * Enum for coins with its value in cents
     */
    public enum Coins {
        DOLLAR(100), HALF(50), QUARTER(25), DIME(10), NICKLE(5), PENNY(1);
        private final int value_cents;

        Coins(int value_cents) {
            this.value_cents = value_cents;
        }

        public int getValue() {
            return value_cents;
        }
    }

    public static void giveChange(int change) {
        int amount = 0;
        int rest = change;
        for (Coins coin : Coins.values())
            switch (coin) {
                case DOLLAR:
                    amount = rest/ coin.getValue();
                    if (amount >= 1) {
                        rest = rest % coin.getValue();
                        System.out.print(amount + " dollar coin/s\n");
                    }
                    break;
                case HALF:
                    amount = rest/ coin.getValue();
                    if (amount >= 1) {
                        rest = rest % coin.getValue();
                        System.out.print(amount + " half-dollar coin/s\n");
                    }
                    break;
                case QUARTER:
                    amount = rest/ coin.getValue();
                    if (amount >= 1) {
                        rest = rest % coin.getValue();
                        System.out.print(amount + " quarter/s\n");
                    }
                    break;
                case DIME:
                    amount = rest/ coin.getValue();
                    if (amount >= 1) {
                        rest = rest % coin.getValue();
                        System.out.print(amount + " dime/s\n");
                    }
                    break;
                case NICKLE:
                    amount = rest/ coin.getValue();
                    if (amount >= 1) {
                        rest = rest % coin.getValue();
                        System.out.print(amount + " nickle/s\n");
                    }
                    break;
                case PENNY:
                    amount = rest/ coin.getValue();
                    if (amount >= 1) {
                        rest = rest % coin.getValue();
                        System.out.print(amount + " penny/ies\n");
                    }
                    break;
            }
    }

}
