package com.jell.learning.ibeer.sevice.manufacturer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDTO {
    private Long id;
    private String name;
    private String birthplace;
}
