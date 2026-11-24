import java.util.List;

public interface Subscriber {
    void signContract(String ownerName);           // оформить договор
    void openAccountAndNumber(String phoneNumber); // открыть счёт и номер
    void editProfile(String newOwnerName);         // редактировать учётную запись
    String getInfo();                              // вся актуальная информация
    double getBalance();                           // состояние баланса
    int getTrafficLeftMb();                        // остаток трафика
    List<String> getPayments();                    // детализация и платежи
    void changeTariff(String tariff, int trafficMb); // сменить тариф
    void changeOperator(String operator);          // сменить оператора
    void topUp(double amount);                     // пополнить счёт
    void closeAccountAndNumber();                  // закрыть счёт и номер
    boolean isOpen();
}