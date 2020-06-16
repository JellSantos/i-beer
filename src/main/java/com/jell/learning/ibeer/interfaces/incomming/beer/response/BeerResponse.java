package com.jell.learning.ibeer.interfaces.incomming.beer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record BeerResponse(@JsonProperty("name") String name,
                           @JsonProperty("ibu") Integer ibu,
                           @JsonProperty("style") String style,
                           @JsonProperty("price") BigDecimal price,
                           @JsonProperty("milliliter") Integer milliliter,
                           @JsonProperty("manufacturer") String manufacturer) {
}
