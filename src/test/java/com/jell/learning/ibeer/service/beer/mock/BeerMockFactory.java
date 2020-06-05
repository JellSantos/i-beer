package com.jell.learning.ibeer.service.beer.mock;

import com.jell.learning.ibeer.domain.beer.Beer;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import org.hamcrest.Factory;

import java.math.BigDecimal;

public final class BeerMockFactory {

    BeerMockFactory() {
        throw new IllegalStateException("Utility class!");
    }

    @Factory
    public static BeerDTO beerDTO() {
        return BeerDTO.builder()
                .name("Brahma")
                .ibu(10)
                .style("IPA")
                .price(BigDecimal.valueOf(10.00))
                .milliliter(10).build();
    }

    @Factory
    public static Beer beer() {
        return Beer.builder()
                .name("Brahma")
                .ibu(10)
                .style("IPA")
                .price(BigDecimal.valueOf(10.00))
                .milliliter(10).build();
    }
}
