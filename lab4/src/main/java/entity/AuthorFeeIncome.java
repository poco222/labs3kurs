package entity;

public class AuthorFeeIncome extends Income {
    private final double deductionPercent;

    public AuthorFeeIncome(String name, double amount, double deductionPercent) {
        super(name, amount);
        this.deductionPercent = deductionPercent;
    }

    public double getDeductionPercent() {
        return deductionPercent;
    }

    @Override
    public String toString() {
        return "AuthorFeeIncome{name='" + getName() + "', amount=" + getAmount()
                + ", deductionPercent=" + deductionPercent + "}";
    }
}