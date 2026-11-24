import java.util.ArrayList;
import java.util.List;

public class BaseSubscriber implements Subscriber {
    private String ownerName;
    private String phoneNumber;
    private String operator;
    private String tariff;
    private double balance;
    private int trafficLeftMb;
    private boolean contractSigned;
    private boolean open;
    private final List<String> payments = new ArrayList<>();

    public BaseSubscriber(String operator, String tariff, int trafficMb) {
        this.operator = operator;
        this.tariff = tariff;
        this.trafficLeftMb = trafficMb;
    }

    @Override
    public void signContract(String ownerName) {
        checkName(ownerName);
        this.ownerName = ownerName;
        this.contractSigned = true;
    }

    @Override
    public void openAccountAndNumber(String phoneNumber) {
        if (!contractSigned) {
            throw new IllegalStateException("Сначала оформите договор");
        }
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Номер не может быть пустым");
        }
        this.phoneNumber = phoneNumber;
        this.open = true;
    }

    @Override
    public void editProfile(String newOwnerName) {
        if (!contractSigned) {
            throw new IllegalStateException("Сначала оформите договор");
        }
        checkName(newOwnerName);
        this.ownerName = newOwnerName;
    }

    @Override
    public String getInfo() {
        return "Владелец: " + ownerName + ", номер: " + phoneNumber
                + ", оператор: " + operator + ", тариф: " + tariff
                + ", баланс: " + balance + ", трафик: " + trafficLeftMb + " МБ"
                + ", " + (open ? "активен" : "закрыт");
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public int getTrafficLeftMb() {
        return trafficLeftMb;
    }

    @Override
    public List<String> getPayments() {
        return new ArrayList<>(payments);
    }

    @Override
    public void changeTariff(String tariff, int trafficMb) {
        checkOpen();
        if (trafficMb < 0) {
            throw new IllegalArgumentException("Трафик не может быть отрицательным");
        }
        this.tariff = tariff;
        this.trafficLeftMb = trafficMb;
    }

    @Override
    public void changeOperator(String operator) {
        checkOpen();
        this.operator = operator;
    }

    @Override
    public void topUp(double amount) {
        checkOpen();
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше нуля");
        }
        balance += amount;
        payments.add("Пополнение: " + amount);
    }

    @Override
    public void closeAccountAndNumber() {
        checkOpen();
        open = false;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    // --- служебные методы для наследников ---

    protected void checkOpen() {
        if (!open) {
            throw new IllegalStateException("Счёт и номер не открыты");
        }
    }

    protected void charge(double amount, String description) {
        checkOpen();
        if (amount > balance) {
            throw new IllegalStateException("Недостаточно средств");
        }
        balance -= amount;
        payments.add("Списание: " + description + " " + amount);
    }

    protected void addTraffic(int mb) {
        trafficLeftMb += mb;
    }

    private void checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
    }
}