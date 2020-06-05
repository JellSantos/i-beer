package com.jell.learning.ibeer.service.manufacturer.mock;

import com.jell.learning.ibeer.domain.manufacturer.Manufacturer;
import com.jell.learning.ibeer.service.beer.mock.BeerMockFactory;
import com.jell.learning.ibeer.sevice.manufacturer.dto.ManufacturerDTO;
import org.hamcrest.Factory;

import java.util.Collections;

public final class ManufacturerMockFactory {

    ManufacturerMockFactory() {
        throw new IllegalStateException("Utility class!");
    }

    @Factory
    public static ManufacturerDTO manufacturerDTO() {
        return ManufacturerDTO.builder()
                .name("Ambev")
                .birthplace("Brasil").build();
    }

    @Factory
    public static Manufacturer manufacturer() {
        return Manufacturer.builder()
                .name("Ambev")
                .birthplace("Brasil")
                .beers(Collections.singletonList(BeerMockFactory.beer())).build();
    }
}
