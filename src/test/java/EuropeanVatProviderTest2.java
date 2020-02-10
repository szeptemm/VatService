import Product.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class EuropeanVatProviderTest2 {

    EuropeanVatProvider vatProvider;

    BigDecimal babyVat;
    BigDecimal booksVat;
    BigDecimal clothesVat;
    BigDecimal foodVat;
    BigDecimal gamesVat;
    BigDecimal shoesVat;

    @BeforeEach
    void setUp(TestInfo info) {
        vatProvider = new EuropeanVatProvider();
        babyVat = vatProvider.getVatFor(info.getDisplayName(), Type.BABY);
        booksVat = vatProvider.getVatFor(info.getDisplayName(), Type.BOOK);
        clothesVat = vatProvider.getVatFor(info.getDisplayName(), Type.CLOTHES);
        foodVat = vatProvider.getVatFor(info.getDisplayName(), Type.FOOD);
        gamesVat = vatProvider.getVatFor(info.getDisplayName(), Type.GAMES);
        shoesVat = vatProvider.getVatFor(info.getDisplayName(), Type.SHOES);
    }


    @DisplayName("Poland")
    @Test
    void shouldReturnTaxRatesInPoland() {
        //given
        BigDecimal taxRate1 = new BigDecimal("0.05");
        BigDecimal taxRate2 = new BigDecimal("0.08");
        BigDecimal taxRate3 = new BigDecimal("0.23");

        //then
        assertAll(
                () -> assertThat(babyVat).isEqualTo(taxRate1),
                () -> assertThat(booksVat).isEqualTo(taxRate1),
                () -> assertThat(clothesVat).isEqualTo(taxRate3),
                () -> assertThat(foodVat).isEqualTo(taxRate2),
                () -> assertThat(gamesVat).isEqualTo(taxRate3),
                () -> assertThat(shoesVat).isEqualTo(taxRate3)
        );
    }

    @DisplayName("Germany")
    @Test
    void shouldReturnTaxRatesInGermany() {
        //given
        BigDecimal taxRate1 = new BigDecimal("0.04");
        BigDecimal taxRate2 = new BigDecimal("0.10");
        BigDecimal taxRate3 = new BigDecimal("0.21");

        //then
        assertAll(
                () -> assertThat(babyVat).isEqualTo(taxRate1),
                () -> assertThat(booksVat).isEqualTo(taxRate1),
                () -> assertThat(clothesVat).isEqualTo(taxRate2),
                () -> assertThat(foodVat).isEqualTo(taxRate1),
                () -> assertThat(gamesVat).isEqualTo(taxRate3),
                () -> assertThat(shoesVat).isEqualTo(taxRate3)
        );
    }
}
