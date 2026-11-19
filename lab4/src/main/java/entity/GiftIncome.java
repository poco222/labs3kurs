package entity;

public class GiftIncome extends Income {
    private final boolean fromCloseRelative;

    public GiftIncome(String name, double amount, boolean fromCloseRelative) {
        super(name, amount);
        this.fromCloseRelative = fromCloseRelative;
    }

    public boolean isFromCloseRelative() {
        return fromCloseRelative;
    }

    @Override
    public String toString() {
        return "GiftIncome{name='" + getName() + "', amount=" + getAmount()
                + ", fromCloseRelative=" + fromCloseRelative + "}";
    }
}