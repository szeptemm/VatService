package Vat;

import Product.Type;

import java.math.BigDecimal;
import java.util.Objects;

public class Vat {
    String country;
    Type productType;
    BigDecimal amount;

    public Vat(String country, Type productType, BigDecimal amount) {
        this.country = Objects.requireNonNull(country, "Country cannot be null");
        this.productType = Objects.requireNonNull(productType, "Product type cannot be null");
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
