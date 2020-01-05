import Product.Type;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

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