package com.jell.learning.ibeer.interfaces.incomming.beer.dto;

import com.jell.learning.ibeer.interfaces.incomming.manufacturer.dto.ManufacturerDTO;

import java.math.BigDecimal;


public record BeerDTO(Long id,
                      String name,
                      Integer ibu,
                      String style,
                      BigDecimal price,
                      Integer milliliter,
                      ManufacturerDTO manufacturer) {}
