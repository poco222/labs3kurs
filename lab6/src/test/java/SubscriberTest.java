import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberTest {

    private BaseSubscriber openedBase() {
        BaseSubscriber s = new BaseSubscriber("МТС", "Старт", 500);
        s.signContract("Анна");
        s.openAccountAndNumber("+79990000000");
        return s;
    }

    private InternetSubscriber openedInternet() {
        InternetSubscriber s = new InternetSubscriber("МТС", "Интернет", 500, 50);
        s.signContract("Анна");
        s.openAccountAndNumber("+79990000001");
        return s;
    }

    private CorporateSubscriber openedCorporate() {
        CorporateSubscriber s = new CorporateSubscriber("МТС", "Бизнес", 1000, "ООО Ромашка");
        s.signContract("Анна");
        s.openAccountAndNumber("+79990000002");
        return s;
    }

    @Test
    void openWithoutContractTest() {
        BaseSubscriber s = new BaseSubscriber("МТС", "Старт", 500);
        assertThrows(IllegalStateException.class, () -> s.openAccountAndNumber("+79990000000"));
    }

    @Test
    void topUpTest() {
        BaseSubscriber s = openedBase();
        s.topUp(100);
        assertEquals(100, s.getBalance(), 0.001);
        assertEquals(1, s.getPayments().size());
    }

    @Test
    void topUpNegativeTest() {
        assertThrows(IllegalArgumentException.class, () -> openedBase().topUp(-10));
    }

    @Test
    void editProfileTest() {
        BaseSubscriber s = openedBase();
        s.editProfile("Мария");
        assertTrue(s.getInfo().contains("Мария"));
    }

    @Test
    void changeTariffTest() {
        BaseSubscriber s = openedBase();
        s.changeTariff("Безлимит", 2000);
        assertEquals(2000, s.getTrafficLeftMb());
        assertTrue(s.getInfo().contains("Безлимит"));
    }

    @Test
    void closeTest() {
        BaseSubscriber s = openedBase();
        s.closeAccountAndNumber();
        assertFalse(s.isOpen());
        assertThrows(IllegalStateException.class, () -> s.topUp(50));
    }

    @Test
    void buyTrafficPackageTest() {
        InternetSubscriber s = openedInternet();
        s.topUp(200);
        s.buyTrafficPackage(1000, 150);
        assertEquals(50, s.getBalance(), 0.001);
        assertEquals(1500, s.getTrafficLeftMb());
    }

    @Test
    void buyTrafficNotEnoughMoneyTest() {
        InternetSubscriber s = openedInternet();
        s.topUp(10);
        assertThrows(IllegalStateException.class, () -> s.buyTrafficPackage(1000, 150));
    }

    @Test
    void speedLimitTest() {
        InternetSubscriber s = openedInternet();
        s.setSpeedLimit(100);
        assertEquals(100, s.getSpeedLimit());
    }

    @Test
    void addEmployeeNumberTest() {
        CorporateSubscriber s = openedCorporate();
        s.addEmployeeNumber("+79991111111");
        assertEquals(1, s.getEmployeeNumbers().size());
        assertTrue(s.getInfo().contains("ООО Ромашка"));
    }

    @Test
    void duplicateEmployeeNumberTest() {
        CorporateSubscriber s = openedCorporate();
        s.addEmployeeNumber("+79991111111");
        assertThrows(IllegalArgumentException.class, () -> s.addEmployeeNumber("+79991111111"));
    }

    @Test
    void removeEmployeeNumberTest() {
        CorporateSubscriber s = openedCorporate();
        s.addEmployeeNumber("+79991111111");
        s.removeEmployeeNumber("+79991111111");
        assertTrue(s.getEmployeeNumbers().isEmpty());
    }
}