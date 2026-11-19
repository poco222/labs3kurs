package creator;

import entity.GiftIncome;
import entity.Income;

public class GiftCreator extends IncomeCreator {

    @Override
    public Income create(String[] parts) {
        String name = parts[1];
        double amount = Double.parseDouble(parts[2]);
        boolean fromCloseRelative = Boolean.parseBoolean(parts[3]);
        return new GiftIncome(name, amount, fromCloseRelative);
    }
}