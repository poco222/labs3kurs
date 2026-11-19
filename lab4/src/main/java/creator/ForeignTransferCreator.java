package creator;

import entity.ForeignTransferIncome;
import entity.Income;

public class ForeignTransferCreator extends IncomeCreator {

    @Override
    public Income create(String[] parts) {
        String name = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String country = parts[3];
        return new ForeignTransferIncome(name, amount, country);
    }
}