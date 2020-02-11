import Product.Product;
import Product.Type;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Test
    void shouldCalculateGrossPriceForBooks() {
        //given
        VatProvider vatProvider = Mockito.mock(VatProvider.class);
        Mockito.when(vatProvider.getVatFor(anyString(),any(Type.class) )).thenReturn(new BigDecimal("0.10"));
        VatService serviceToTest = new VatService(vatProvider);
        //when
        Stream<BigDecimal> grossPrices = serviceToTest.getGrossPrices(generateStreamOfProducts());

        BigDecimal sum = grossPrices
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //then
        assertThat(sum).isEqualTo(new BigDecimal("252.45"));
    }

    private Stream<Product> generateStreamOfProducts() {

        Stream<Product> product =
                IntStream.range(1,10)
                .mapToObj(index -> {
                    BigDecimal price = BigDecimal.valueOf(index).add(new BigDecimal("20.50"));
                    return new Product(UUID.randomUUID(), "My book", price, Type.BOOK, "Poland");
                });

        return product;
    }
}