package com.jell.learning.ibeer.interfaces.incomming.manufacturer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDTO {
    private Long id;
    private String name;
    private String birthplace;
}
