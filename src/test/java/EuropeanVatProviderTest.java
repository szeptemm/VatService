import Product.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EuropeanVatProviderTest {

    @Test
    void shouldReturn5PercentageForBooksInPoland() {
        //given
        EuropeanVatProvider vatProvider = new EuropeanVatProvider();

        //when
        BigDecimal polishBooksVat = vatProvider.getVatFor("Poland", Type.BOOK);

        //then
        assertThat(polishBooksVat).isEqualTo(new BigDecimal("0.05"));
    }
}