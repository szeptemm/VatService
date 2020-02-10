package Vat;

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
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class VatRepositoryTest {

    @Test
    void shouldReturnTaxRatesInPoland() throws VatNotFoundException {
        //given
        VatRepository vatRepo = new VatRepository();
        BigDecimal taxRate1 = new BigDecimal("0.05");
        BigDecimal taxRate2 = new BigDecimal("0.08");
        BigDecimal taxRate3 = new BigDecimal("0.23");
        //when
        BigDecimal polishBabyVat = vatRepo.getVatFor("Poland", Type.BABY).getAmount();
        BigDecimal polishBooksVat = vatRepo.getVatFor("Poland", Type.BOOK).getAmount();
        BigDecimal polishClothesVat = vatRepo.getVatFor("Poland", Type.CLOTHES).getAmount();
        BigDecimal polishFoodVat = vatRepo.getVatFor("Poland", Type.FOOD).getAmount();
        BigDecimal polishGamesVat = vatRepo.getVatFor("Poland", Type.GAMES).getAmount();
        BigDecimal polishShoesVat = vatRepo.getVatFor("Poland", Type.SHOES).getAmount();
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
    void shouldThrowExceptionWhenCountryIsIncorrect() {
        //given
        VatRepository repositoryToTest = new VatRepository();

        //when
        Exception exception = assertThrows(VatNotFoundException.class,
                () -> repositoryToTest.getVatFor("Poland111", Type.BOOK));

        //then
        assertThat(exception.getMessage()).isEqualTo("Vat for country Poland111 and product type BOOK was not found");
    }

    @Test
    void shouldCallAddVatValueOneTime() {
        //given
        VatRepository repositoryToTest = Mockito.mock(VatRepository.class);
        //when
        repositoryToTest.addVatValue("Germany", Type.BOOK, new BigDecimal("0.08"));
        //then
        verify(repositoryToTest, times(1)).addVatValue("Germany", Type.BOOK, new BigDecimal("0.08"));
    }

    @Test
    void shouldAddProperValues() {
        //given
        VatRepository repositoryToTest = Mockito.mock(VatRepository.class);
        //then
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            Object arg2 = invocation.getArgument(2);

            assertEquals("Germany", arg0);
            assertEquals(Type.FOOD, arg1);
            assertEquals(new BigDecimal("0.23"), arg2);
            return null;
        }).when(repositoryToTest).addVatValue(anyString(), any(Type.class), any(BigDecimal.class));
        repositoryToTest.addVatValue("Germany", Type.FOOD, new BigDecimal("0.23"));
    }
}