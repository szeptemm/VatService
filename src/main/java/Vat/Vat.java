package Vat;

import Product.Type;

import java.math.BigDecimal;

public class Vat {
    String country;
    Type productType;
    BigDecimal amount;

    public Vat(String country, Type productType, BigDecimal amount) {
        this.country = country;
        this.productType = productType;
        this.amount = amount;
    }
}
