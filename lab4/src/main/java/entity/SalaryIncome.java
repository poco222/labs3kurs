package entity;

public class SalaryIncome extends Income {
    private final String employer;
    private final boolean mainJob;

    public SalaryIncome(String name, double amount, String employer, boolean mainJob) {
        super(name, amount);
        this.employer = employer;
        this.mainJob = mainJob;
    }

    public String getEmployer() {
        return employer;
    }

    public boolean isMainJob() {
        return mainJob;
    }

    @Override
    public String toString() {
        return "SalaryIncome{name='" + getName() + "', amount=" + getAmount()
                + ", employer='" + employer + "', mainJob=" + mainJob + "}";
    }
}