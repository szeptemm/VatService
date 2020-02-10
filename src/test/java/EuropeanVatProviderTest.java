import Product.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

class EuropeanVatProviderTest {


    EuropeanVatProvider vatProvider;

    BigDecimal babyVat;
    BigDecimal booksVat;
    BigDecimal clothesVat;
    BigDecimal foodVat;
    BigDecimal gamesVat;
    BigDecimal shoesVat;

//    @BeforeEach
//    void setUp(TestInfo info) {
//        vatProvider = new EuropeanVatProvider();
//        babyVat = vatProvider.getVatFor(info.getDisplayName(), Type.BABY);
//        booksVat = vatProvider.getVatFor(info.getDisplayName(), Type.BOOK);
//        clothesVat = vatProvider.getVatFor(info.getDisplayName(), Type.CLOTHES);
//        foodVat = vatProvider.getVatFor(info.getDisplayName(), Type.FOOD);
//        gamesVat = vatProvider.getVatFor(info.getDisplayName(), Type.GAMES);
//        shoesVat = vatProvider.getVatFor(info.getDisplayName(), Type.SHOES);
//    }
//
//
//    @DisplayName("Poland")
//    @Test
//    void shouldReturnTaxRatesInPoland() {
//        //given
//        BigDecimal taxRate1 = new BigDecimal("0.05");
//        BigDecimal taxRate2 = new BigDecimal("0.08");
//        BigDecimal taxRate3 = new BigDecimal("0.23");
//
//        //then
//        assertAll(
//                () -> assertThat(babyVat).isEqualTo(taxRate1),
//                () -> assertThat(booksVat).isEqualTo(taxRate1),
//                () -> assertThat(clothesVat).isEqualTo(taxRate3),
//                () -> assertThat(foodVat).isEqualTo(taxRate2),
//                () -> assertThat(gamesVat).isEqualTo(taxRate3),
//                () -> assertThat(shoesVat).isEqualTo(taxRate3)
//        );
//    }
//
//    @DisplayName("Germany")
//    @Test
//    void shouldReturnTaxRatesInGermany() {
//        //given
//        BigDecimal taxRate1 = new BigDecimal("0.04");
//        BigDecimal taxRate2 = new BigDecimal("0.10");
//        BigDecimal taxRate3 = new BigDecimal("0.21");
//
//        //then
//        assertAll(
//                () -> assertThat(babyVat).isEqualTo(taxRate1),
//                () -> assertThat(booksVat).isEqualTo(taxRate1),
//                () -> assertThat(clothesVat).isEqualTo(taxRate2),
//                () -> assertThat(foodVat).isEqualTo(taxRate1),
//                () -> assertThat(gamesVat).isEqualTo(taxRate3),
//                () -> assertThat(shoesVat).isEqualTo(taxRate3)
//        );
//    }

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

    @ParameterizedTest
    @EnumSource(Type.class)
    void shouldReturnTaxRatesInDenmark2(Type type) {
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
        EuropeanVatProvider europeanVatProvider = new EuropeanVatProvider(vatProvider);

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