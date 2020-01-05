package Product;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    UUID id;
    String name;
    BigDecimal netPrice;
    Type type;
    String country;

    public Product(UUID id, String name, BigDecimal netPrice, Type type, String country) {
        this.id = id;
        this.name = name;
        this.netPrice = netPrice;
        this.type = type;
        this.country = country;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public Type getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }
}

