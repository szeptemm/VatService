import Product.Type;

import java.math.BigDecimal;

public interface VatProvider {
    BigDecimal getVatFor(String country, Type productType);
}
