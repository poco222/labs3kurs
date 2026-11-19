package creator;

import entity.Income;
import entity.SalaryIncome;

public class SalaryCreator extends IncomeCreator {

    @Override
    public Income create(String[] parts) {
        String name = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String employer = parts[3];
        boolean mainJob = Boolean.parseBoolean(parts[4]);
        return new SalaryIncome(name, amount, employer, mainJob);
    }
}