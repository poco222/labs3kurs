import java.util.ArrayList;
import java.util.List;

public class CorporateSubscriber extends BaseSubscriber implements CorporateAccount {
    private final String company;
    private final List<String> employeeNumbers = new ArrayList<>();

    public CorporateSubscriber(String operator, String tariff, int trafficMb, String company) {
        super(operator, tariff, trafficMb);
        this.company = company;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public void addEmployeeNumber(String number) {
        checkOpen();
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Номер не может быть пустым");
        }
        if (employeeNumbers.contains(number)) {
            throw new IllegalArgumentException("Такой номер уже добавлен");
        }
        employeeNumbers.add(number);
    }

    @Override
    public void removeEmployeeNumber(String number) {
        checkOpen();
        if (!employeeNumbers.remove(number)) {
            throw new IllegalArgumentException("Такого номера нет");
        }
    }

    @Override
    public List<String> getEmployeeNumbers() {
        return new ArrayList<>(employeeNumbers);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", компания: " + company
                + ", номеров сотрудников: " + employeeNumbers.size();
    }
}