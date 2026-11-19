package entity;

public class ChildBenefit extends Income {
    private final int childrenCount;

    public ChildBenefit(String name, double amount, int childrenCount) {
        super(name, amount);
        this.childrenCount = childrenCount;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    @Override
    public String toString() {
        return "ChildBenefit{name='" + getName() + "', amount=" + getAmount()
                + ", childrenCount=" + childrenCount + "}";
    }
}