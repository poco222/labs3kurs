package creator;

import entity.ChildBenefit;
import entity.Income;

public class ChildBenefitCreator extends IncomeCreator {

    @Override
    public Income create(String[] parts) {
        String name = parts[1];
        double amount = Double.parseDouble(parts[2]);
        int childrenCount = Integer.parseInt(parts[3]);
        return new ChildBenefit(name, amount, childrenCount);
    }
}