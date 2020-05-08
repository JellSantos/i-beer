package com.jell.learning.ibeer.sevice.beer.dto;

import com.jell.learning.ibeer.sevice.manufacturer.dto.ManufacturerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {
    private Long id;
    private String name;
    private Integer ibu;
    private String style;
    private BigDecimal price;
    private Integer milliliter;
    private ManufacturerDTO manufacturer;
}
