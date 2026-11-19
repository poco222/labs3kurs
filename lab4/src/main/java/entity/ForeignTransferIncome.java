package entity;

public class ForeignTransferIncome extends Income {
    private final String country;

    public ForeignTransferIncome(String name, double amount, String country) {
        super(name, amount);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "ForeignTransferIncome{name='" + getName() + "', amount=" + getAmount()
                + ", country='" + country + "'}";
    }
}