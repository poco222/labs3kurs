package creator;

import entity.Income;
import entity.MaterialAidIncome;

public class MaterialAidCreator extends IncomeCreator {

    @Override
    public Income create(String[] parts) {
        String name = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String provider = parts[3];
        return new MaterialAidIncome(name, amount, provider);
    }
}