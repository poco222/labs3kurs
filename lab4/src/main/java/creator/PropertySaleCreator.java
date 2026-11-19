package creator;

import entity.Income;
import entity.PropertySaleIncome;

public class PropertySaleCreator extends IncomeCreator {

    @Override
    public Income create(String[] parts) {
        String name = parts[1];
        double amount = Double.parseDouble(parts[2]);
        double purchasePrice = Double.parseDouble(parts[3]);
        int ownedYears = Integer.parseInt(parts[4]);
        return new PropertySaleIncome(name, amount, purchasePrice, ownedYears);
    }
}