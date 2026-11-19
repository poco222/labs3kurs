package creator;

import entity.Income;

public abstract class IncomeCreator {

    public abstract Income create(String[] parts);
}