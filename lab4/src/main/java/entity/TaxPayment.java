package entity;

import java.util.Locale;

public class TaxPayment {
    private final String incomeName;
    private final String incomeType;
    private final double taxAmount;

    public TaxPayment(String incomeName, String incomeType, double taxAmount) {
        this.incomeName = incomeName;
        this.incomeType = incomeType;
        this.taxAmount = taxAmount;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s [%s]: %.2f", incomeName, incomeType, taxAmount);
    }
}