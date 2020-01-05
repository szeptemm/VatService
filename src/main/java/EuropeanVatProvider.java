import Product.Type;

import java.math.BigDecimal;
import java.util.Arrays;

public class EuropeanVatProvider implements VatProvider {

    public BigDecimal getVatFor(String country, Type productType) {
        BigDecimal result;
        switch (country) {
            case "Poland":
                result = getPolishVatFor(productType);
                break;
            case "Germany":
                result = getGermanVatFor(productType);
                break;
            case "Denmark":
                result = new BigDecimal("0.08");
                break;
            default:
                throw new CountryNotSupportedException("This country is not supported: " + country);

        }
        return result;
    }

    private BigDecimal getPolishVatFor(Type productType) {
        BigDecimal result;
        if (Arrays.asList(Type.BABY, Type.BOOK).contains(productType)) {
            result = new BigDecimal("0.05");
        } else if (Type.FOOD.equals(productType)) {
            result = new BigDecimal("0.08");
        } else {
            result = new BigDecimal("0.23");
        }

        return result;
    }

    private BigDecimal getGermanVatFor(Type productType) {
        BigDecimal result;
        if (Arrays.asList(Type.BABY, Type.BOOK, Type.FOOD).contains(productType)) {
            result = new BigDecimal("0.04");
        } else if (Type.CLOTHES.equals(productType)) {
            result = new BigDecimal("0.10");
        } else {
            result = new BigDecimal("0.21");
        }

        return result;
    }
}
