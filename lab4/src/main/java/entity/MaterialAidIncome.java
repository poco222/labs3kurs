package entity;

public class MaterialAidIncome extends Income {
    private final String provider;

    public MaterialAidIncome(String name, double amount, String provider) {
        super(name, amount);
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public String toString() {
        return "MaterialAidIncome{name='" + getName() + "', amount=" + getAmount()
                + ", provider='" + provider + "'}";
    }
}