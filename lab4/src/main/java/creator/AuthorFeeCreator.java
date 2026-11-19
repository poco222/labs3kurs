package creator;

import entity.AuthorFeeIncome;
import entity.Income;

public class AuthorFeeCreator extends IncomeCreator {

    @Override
    public Income create(String[] parts) {
        String name = parts[1];
        double amount = Double.parseDouble(parts[2]);
        double deductionPercent = Double.parseDouble(parts[3]);
        return new AuthorFeeIncome(name, amount, deductionPercent);
    }
}