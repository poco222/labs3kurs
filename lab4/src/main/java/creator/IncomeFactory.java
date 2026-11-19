package creator;

import exception.InvalidDataException;

import java.util.Locale;
import java.util.Map;

public final class IncomeFactory {
    private static final Map<String, IncomeCreator> CREATORS = Map.of(
            "SALARY", new SalaryCreator(),
            "AUTHOR", new AuthorFeeCreator(),
            "PROPERTY", new PropertySaleCreator(),
            "GIFT", new GiftCreator(),
            "FOREIGN", new ForeignTransferCreator(),
            "AID", new MaterialAidCreator(),
            "CHILD", new ChildBenefitCreator());

    private IncomeFactory() {
    }

    public static IncomeCreator getCreator(String type) throws InvalidDataException {
        IncomeCreator creator = CREATORS.get(type.toUpperCase(Locale.ROOT));
        if (creator == null) {
            throw new InvalidDataException("Unknown income type: " + type);
        }
        return creator;
    }
}