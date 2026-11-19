package creator;

import entity.AuthorFeeIncome;
import entity.ChildBenefit;
import entity.ForeignTransferIncome;
import entity.GiftIncome;
import entity.Income;
import entity.MaterialAidIncome;
import entity.PropertySaleIncome;
import entity.SalaryIncome;
import exception.InvalidDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeFactoryTest {
    private static final double DELTA = 0.0001;

    private Income create(String line) throws InvalidDataException {
        String[] parts = line.split(";");
        return IncomeFactory.getCreator(parts[0]).create(parts);
    }

    @Test
    void createsSalary() throws InvalidDataException {
        Income income = create("SALARY;Job;1000;Yandex;true");
        SalaryIncome salary = assertInstanceOf(SalaryIncome.class, income);
        assertEquals("Job", salary.getName());
        assertEquals(1000, salary.getAmount(), DELTA);
        assertEquals("Yandex", salary.getEmployer());
        assertTrue(salary.isMainJob());
    }

    @Test
    void createsAuthorFee() throws InvalidDataException {
        AuthorFeeIncome income = assertInstanceOf(AuthorFeeIncome.class, create("AUTHOR;Book;5000;20"));
        assertEquals(20, income.getDeductionPercent(), DELTA);
    }

    @Test
    void createsPropertySale() throws InvalidDataException {
        PropertySaleIncome income =
                assertInstanceOf(PropertySaleIncome.class, create("PROPERTY;Flat;5000;3000;2"));
        assertEquals(3000, income.getPurchasePrice(), DELTA);
        assertEquals(2, income.getOwnedYears());
    }

    @Test
    void createsGift() throws InvalidDataException {
        GiftIncome income = assertInstanceOf(GiftIncome.class, create("GIFT;Money;100;true"));
        assertTrue(income.isFromCloseRelative());
    }

    @Test
    void createsForeignTransfer() throws InvalidDataException {
        ForeignTransferIncome income =
                assertInstanceOf(ForeignTransferIncome.class, create("FOREIGN;Transfer;100;Germany"));
        assertEquals("Germany", income.getCountry());
    }

    @Test
    void createsMaterialAid() throws InvalidDataException {
        MaterialAidIncome income = assertInstanceOf(MaterialAidIncome.class, create("AID;Aid;100;Boss"));
        assertEquals("Boss", income.getProvider());
    }

    @Test
    void createsChildBenefit() throws InvalidDataException {
        ChildBenefit income = assertInstanceOf(ChildBenefit.class, create("CHILD;Child;16800;2"));
        assertEquals(2, income.getChildrenCount());
    }

    @Test
    void typeIsCaseInsensitive() throws InvalidDataException {
        assertInstanceOf(GiftIncome.class, create("gift;Money;100;false"));
    }

    @Test
    void unknownTypeThrows() {
        assertThrows(InvalidDataException.class, () -> IncomeFactory.getCreator("BONUS"));
    }
}