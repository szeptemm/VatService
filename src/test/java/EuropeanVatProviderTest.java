import Product.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class EuropeanVatProviderTest {

    @ParameterizedTest
    @EnumSource(Type.class)
    void shouldReturnTaxRatesInDenmark(Type type) {
        //given
        EuropeanVatProvider vatProvider = new EuropeanVatProvider();
        BigDecimal taxRate = new BigDecimal("0.08");
        //then
        assertThat(vatProvider.getVatFor("Denmark", type)).isEqualTo(taxRate);
    }

    @Test
    void shouldReturnCountryNotSupportedException() {
        //given
        VatProvider vatProvider = Mockito.mock(VatProvider.class);
        Mockito.when(vatProvider.getVatFor(anyString(), any(Type.class))).
                thenThrow(new CountryNotSupportedException("This country is not supported: " + anyString()));
        EuropeanVatProvider europeanVatProvider = new EuropeanVatProvider();

        //then
        CountryNotSupportedException actual =
                assertThrows(CountryNotSupportedException.class,
                        () -> europeanVatProvider.getVatFor("Japan", Type.BOOK));
        assertEquals("This country is not supported: Japan", actual.getMessage());
    }

    @Test
    void shouldReturnCountryNotSupportedException2() {
        //given
        EuropeanVatProvider vatProvider = new EuropeanVatProvider();

        //when
        Exception ex = assertThrows(CountryNotSupportedException.class, () -> {
            vatProvider.getVatFor("Japan", Type.BOOK);
        });

        String expectedMsg = "This country is not supported: Japan";
        String actualMsg = ex.getMessage();

        //then
        assertTrue(actualMsg.contains(expectedMsg));
    }
}