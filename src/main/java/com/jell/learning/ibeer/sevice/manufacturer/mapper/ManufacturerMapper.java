package com.jell.learning.ibeer.sevice.manufacturer.mapper;

import com.jell.learning.ibeer.config.mapstruct.BaseEntityMapper;
import com.jell.learning.ibeer.config.mapstruct.BaseMapperConfig;
import com.jell.learning.ibeer.domain.manufacturer.Manufacturer;
import com.jell.learning.ibeer.sevice.manufacturer.dto.ManufacturerDTO;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ManufacturerMapper extends BaseEntityMapper<ManufacturerDTO, Manufacturer> {
}
