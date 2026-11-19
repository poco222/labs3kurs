package entity;

public class PropertySaleIncome extends Income {
    private final double purchasePrice;
    private final int ownedYears;

    public PropertySaleIncome(String name, double amount, double purchasePrice, int ownedYears) {
        super(name, amount);
        this.purchasePrice = purchasePrice;
        this.ownedYears = ownedYears;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public int getOwnedYears() {
        return ownedYears;
    }

    @Override
    public String toString() {
        return "PropertySaleIncome{name='" + getName() + "', amount=" + getAmount()
                + ", purchasePrice=" + purchasePrice + ", ownedYears=" + ownedYears + "}";
    }
}