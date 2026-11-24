import java.util.List;

public interface CorporateAccount extends Subscriber {
    String getCompany();
    void addEmployeeNumber(String number);
    void removeEmployeeNumber(String number);
    List<String> getEmployeeNumbers();
}