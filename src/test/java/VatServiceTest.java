import Product.Product;
import Product.Type;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class VatServiceTest {

    @Test
    void shouldCalculateGrossPriceForBook() {
        //given
        VatProvider vatProvider = Mockito.mock(VatProvider.class);
        Mockito.when(vatProvider.getVatFor(anyString(),any(Type.class) )).thenReturn(new BigDecimal("0.05"));
        VatService serviceToTest = new VatService(vatProvider);
        Product product = new Product(UUID.randomUUID(), "My book", new BigDecimal("20.50"), Type.BOOK, "Poland");

        //when
        BigDecimal grossPrice = serviceToTest.getGrossPrice(product);

        //then

        assertThat(grossPrice).isEqualTo(new BigDecimal("21.53"));
    }
}