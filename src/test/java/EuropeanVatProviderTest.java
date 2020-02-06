import Product.Type;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

class EuropeanVatProviderTest {

    @Test
    void shouldReturnTaxRatesInPoland() {
        //given
        EuropeanVatProvider vatProvider = new EuropeanVatProvider();
        BigDecimal taxRate1 = new BigDecimal("0.05");
        BigDecimal taxRate2 = new BigDecimal("0.08");
        BigDecimal taxRate3 = new BigDecimal("0.23");

        //when
        BigDecimal polishBabyVat = vatProvider.getVatFor("Poland", Type.BABY);
        BigDecimal polishBooksVat = vatProvider.getVatFor("Poland", Type.BOOK);
        BigDecimal polishClothesVat = vatProvider.getVatFor("Poland", Type.CLOTHES);
        BigDecimal polishFoodVat = vatProvider.getVatFor("Poland", Type.FOOD);
        BigDecimal polishGamesVat = vatProvider.getVatFor("Poland", Type.GAMES);
        BigDecimal polishShoesVat = vatProvider.getVatFor("Poland", Type.SHOES);
        //then
        assertAll(
                () -> assertThat(polishBabyVat).isEqualTo(taxRate1),
                () -> assertThat(polishBooksVat).isEqualTo(taxRate1),
                () -> assertThat(polishClothesVat).isEqualTo(taxRate3),
                () -> assertThat(polishFoodVat).isEqualTo(taxRate2),
                () -> assertThat(polishGamesVat).isEqualTo(taxRate3),
                () -> assertThat(polishShoesVat).isEqualTo(taxRate3)
        );
    }

    @Test
    void shouldReturnTaxRatesInGermany() {
        //given
        EuropeanVatProvider vatProvider = new EuropeanVatProvider();
        BigDecimal taxRate1 = new BigDecimal("0.04");
        BigDecimal taxRate2 = new BigDecimal("0.10");
        BigDecimal taxRate3 = new BigDecimal("0.21");

        //when
        BigDecimal germanBabyVat = vatProvider.getVatFor("Germany", Type.BABY);
        BigDecimal germanBooksVat = vatProvider.getVatFor("Germany", Type.BOOK);
        BigDecimal germanClothesVat = vatProvider.getVatFor("Germany", Type.CLOTHES);
        BigDecimal germanFoodVat = vatProvider.getVatFor("Germany", Type.FOOD);
        BigDecimal germanGamesVat = vatProvider.getVatFor("Germany", Type.GAMES);
        BigDecimal germanShoesVat = vatProvider.getVatFor("Germany", Type.SHOES);
        //then
        assertAll(
                () -> assertThat(germanBabyVat).isEqualTo(taxRate1),
                () -> assertThat(germanBooksVat).isEqualTo(taxRate1),
                () -> assertThat(germanClothesVat).isEqualTo(taxRate2),
                () -> assertThat(germanFoodVat).isEqualTo(taxRate1),
                () -> assertThat(germanGamesVat).isEqualTo(taxRate3),
                () -> assertThat(germanShoesVat).isEqualTo(taxRate3)
        );
    }

    @Test
    void shouldReturnTaxRatesInDenmark() {
        //given
        VatProvider vatProvider = Mockito.mock(VatProvider.class);
        BigDecimal taxRate = new BigDecimal("0.08");
        Mockito.when(vatProvider.getVatFor(eq("Denmark"),any(Type.class))).thenReturn(taxRate);
        EuropeanVatProvider europeanVatProvider = new EuropeanVatProvider(vatProvider);
        //when
        BigDecimal danemarkBooksVat = europeanVatProvider.getVatFor("Denmark", Type.BOOK);

        //then
        assertThat(taxRate).isEqualTo(danemarkBooksVat);
    }

    @Test
    void shouldReturnCountryNotSupportedException() {
        //given
        VatProvider vatProvider = Mockito.mock(VatProvider.class);
        Mockito.when(vatProvider.getVatFor(anyString(), any(Type.class))).
                thenThrow(new CountryNotSupportedException("This country is not supported: " + anyString()));
        EuropeanVatProvider europeanVatProvider = new EuropeanVatProvider(vatProvider);

        //then
        CountryNotSupportedException actual =
                assertThrows(CountryNotSupportedException.class,
                        () -> europeanVatProvider.getVatFor("Japan", Type.BOOK));
        assertEquals("This country is not supported: Japan", actual.getMessage());
    }
}