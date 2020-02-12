package Product;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {
    UUID id;
    String name;
    BigDecimal netPrice;
    Type type;
    String country;

    public Product(UUID id, String name, BigDecimal netPrice, Type type, String country) {
        this.id = Objects.requireNonNull(id,"Id cannot be null");
        this.name = Objects.requireNonNull(name,"Name cannot be null");
        this.netPrice = Objects.requireNonNull(netPrice,"Net price cannot be null");
        this.type = Objects.requireNonNull(type,"Type cannot be null");
        this.country = Objects.requireNonNull(country,"Country cannot be null");
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

