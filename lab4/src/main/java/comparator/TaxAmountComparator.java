package comparator;

import entity.TaxPayment;

import java.util.Comparator;

public class TaxAmountComparator implements Comparator<TaxPayment> {

    @Override
    public int compare(TaxPayment first, TaxPayment second) {
        return Double.compare(first.getTaxAmount(), second.getTaxAmount());
    }
}