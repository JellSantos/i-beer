package com.jell.learning.ibeer.interfaces.incomming.manufacturer.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ManufacturerResponse(@JsonProperty("name") String name) {}
