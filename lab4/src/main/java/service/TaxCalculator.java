package service;

import entity.AuthorFeeIncome;
import entity.ChildBenefit;
import entity.ForeignTransferIncome;
import entity.GiftIncome;
import entity.Income;
import entity.MaterialAidIncome;
import entity.PropertySaleIncome;
import entity.SalaryIncome;
import entity.TaxPayment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculator {
    private static final Logger LOGGER = LogManager.getLogger(TaxCalculator.class);
    private static final double TAX_RATE = 0.13;
    private static final double AID_LIMIT = 4000;
    private static final int PROPERTY_FREE_YEARS = 5;
    private static final double PERCENT = 100;

    public TaxPayment calculate(Income income) {
        LOGGER.trace("Calculating tax for {}", income);
        double tax = calculateTax(income);
        LOGGER.debug("Tax for '{}' = {}", income.getName(), tax);
        return new TaxPayment(income.getName(), income.getClass().getSimpleName(), tax);
    }

    public List<TaxPayment> calculateAll(List<Income> incomes) {
        List<TaxPayment> payments = new ArrayList<>();
        for (Income income : incomes) {
            payments.add(calculate(income));
        }
        return payments;
    }

    public double getTotal(List<TaxPayment> payments) {
        double total = 0;
        for (TaxPayment payment : payments) {
            total += payment.getTaxAmount();
        }
        return total;
    }

    private double calculateTax(Income income) {
        if (income instanceof SalaryIncome) {
            return income.getAmount() * TAX_RATE;
        }
        if (income instanceof AuthorFeeIncome author) {
            double taxable = author.getAmount() * (1 - author.getDeductionPercent() / PERCENT);
            return taxable * TAX_RATE;
        }
        if (income instanceof PropertySaleIncome property) {
            if (property.getOwnedYears() >= PROPERTY_FREE_YEARS) {
                return 0;
            }
            double profit = property.getAmount() - property.getPurchasePrice();
            return Math.max(profit, 0) * TAX_RATE;
        }
        if (income instanceof GiftIncome gift) {
            return gift.isFromCloseRelative() ? 0 : gift.getAmount() * TAX_RATE;
        }
        if (income instanceof ForeignTransferIncome) {
            return income.getAmount() * TAX_RATE;
        }
        if (income instanceof MaterialAidIncome) {
            return Math.max(income.getAmount() - AID_LIMIT, 0) * TAX_RATE;
        }
        if (income instanceof ChildBenefit child) {
            return -child.getAmount() * child.getChildrenCount() * TAX_RATE;
        }
        throw new IllegalArgumentException("Unknown income type: " + income);
    }
}